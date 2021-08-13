package com.example.rickandmortydemo.character.ui.getall.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortydemo.character.domain.usecase.CharacterGetAllUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CharacterViewModelFactory(private val characterGetAllUseCase : CharacterGetAllUseCase,
                                private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):ViewModelProvider.Factory
                                 {
                                     override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                                         return modelClass.getConstructor(characterGetAllUseCase::class.java, CoroutineDispatcher::class.java).newInstance(characterGetAllUseCase,ioDispatcher)
                                     }
                                 }