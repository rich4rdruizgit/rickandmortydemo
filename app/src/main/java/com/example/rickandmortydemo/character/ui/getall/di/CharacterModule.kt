package com.example.rickandmortydemo.character.ui.getall.di

import android.content.Context
import com.example.rickandmortydemo.RickMortyApplication
import com.example.rickandmortydemo.character.data.datasource.remote.CharacterApiImpl
import com.example.rickandmortydemo.character.data.datasource.remote.RetrofitCharacterFactory
import com.example.rickandmortydemo.character.data.repository.RepositoryCharacterImpl
import com.example.rickandmortydemo.character.domain.usecase.CharacterGetAllUseCase
import com.example.rickandmortydemo.character.ui.getall.viewmodel.CharacterViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CharacterModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideCharacterViewModelFactory() =  CharacterViewModelFactory(CharacterGetAllUseCase(provideRepositoryCharacterImpl()))

    @Provides
    @Singleton
    fun provideRepositoryCharacterImpl() = RepositoryCharacterImpl(CharacterApiImpl(RetrofitCharacterFactory.characterApiRetrofit(RickMortyApplication.environments.url)),RickMortyApplication.environments)
}
