package com.example.rickandmortydemo.character.ui.getbyid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortydemo.character.domain.usecase.CharacterGetById
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class SingleCharacterViewModelFactory(private val characterGetById: CharacterGetById,
                                      private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
                                      ) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(characterGetById::class.java, CoroutineDispatcher::class.java).newInstance(characterGetById,ioDispatcher)
    }
}