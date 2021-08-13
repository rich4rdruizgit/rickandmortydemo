package com.example.rickandmortydemo.character.ui.getall.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectarchitecture.common.Failure
import com.example.projectarchitecture.common.Success
import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.rickandmortydemo.character.domain.usecase.CharacterGetAllUseCase
import com.example.rickandmortydemo.character.ui.getall.adapter.CharacterCard
import kotlinx.coroutines.*

class CharacterViewModel(private val _characterGetAllUseCase : CharacterGetAllUseCase, private val _dispatcher:CoroutineDispatcher):
    ViewModel() {
    private val _model =  MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
    get(){
        if(_model.value == null) UiModel.Loading(true)
        return _model
    }

    private var _job: Job? = null

    fun getAllCharacters(){
        _job?.cancel()
        _model.value = UiModel.Loading(true)
        _job = viewModelScope.launch (_dispatcher){
            when (val result = _characterGetAllUseCase()){
                is Success ->{
                    withContext(Dispatchers.Main){
                        if(result.value.results!= null){

                            _model.value = UiModel.CharacterRecyclerCard(transforData(result.value))
                        }else{
                            _model.value = UiModel.CharacterRecyclerCard(mutableListOf())
                        }
                    }
                }
                is Failure -> {
                    withContext(Dispatchers.Main){
                        _model.value = UiModel.CharacterRecyclerCard(mutableListOf())
                    }
                }

            }

            withContext(Dispatchers.Main){
                _job?.cancel()
                _model.value = UiModel.Loading(false)
            }
        }
    }

    private fun transforData(value: ResponseCharacter): MutableList<CharacterCard> {
        val newList: MutableList<CharacterCard> = mutableListOf()

        val characters = value.results

        if(characters != null){
            for (i in 0 until characters.size)
                newList.add(CharacterCard(characters[i].id,characters[i].name,characters[i].image!! ))
        }

        return newList

    }

    override fun onCleared() {
        super.onCleared()
        _job?.cancel()
    }


    sealed class UiModel{
        data class Loading(val visible:Boolean): UiModel()
        data class CharacterRecyclerCard(val list:MutableList<CharacterCard>):UiModel()
    }
}