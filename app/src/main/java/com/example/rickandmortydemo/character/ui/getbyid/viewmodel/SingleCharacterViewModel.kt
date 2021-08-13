package com.example.rickandmortydemo.character.ui.getbyid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectarchitecture.common.Failure
import com.example.projectarchitecture.common.Success
import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.rickandmortydemo.character.data.repository.ResponseCharacterData
import com.example.rickandmortydemo.character.domain.usecase.CharacterGetById
import com.example.rickandmortydemo.character.ui.getall.adapter.CharacterCard
import com.example.rickandmortydemo.character.ui.getall.viewmodel.CharacterViewModel
import com.example.rickandmortydemo.character.ui.getbyid.adapter.SingleCharacterData
import kotlinx.coroutines.*

class SingleCharacterViewModel(private val _characterGetById : CharacterGetById,
private val _dispatcher:CoroutineDispatcher): ViewModel() {

    private val _model = MutableLiveData<UiModel>()

    val model:LiveData<UiModel>
    get(){
        if(_model.value == null) UiModel.Loading(true)
        return _model
    }

    private var _job: Job? = null

    fun getCharacterById(id:Int){
        _job?.cancel()
        _model.value = UiModel.Loading(true)
        _job = viewModelScope.launch (_dispatcher){
            when (val result = _characterGetById(id)){
                is Success -> {
                    _model.postValue(UiModel.SetCharacterData(result.value))
                }

                is Failure -> {
                    UiModel.Message("Error")
                }
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        _job?.cancel()
    }


    sealed class UiModel{
        data class Loading(val visible:Boolean): UiModel()
        data class SetCharacterData(val info:ResponseCharacterData):UiModel()
        data class Message(val message:String):UiModel()
    }
}