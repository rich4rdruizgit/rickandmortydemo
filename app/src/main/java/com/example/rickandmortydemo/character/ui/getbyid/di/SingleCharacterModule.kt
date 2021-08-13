package com.example.rickandmortydemo.character.ui.getbyid.di

import android.content.Context
import com.example.rickandmortydemo.RickMortyApplication
import com.example.rickandmortydemo.character.data.datasource.remote.CharacterApiImpl
import com.example.rickandmortydemo.character.data.datasource.remote.RetrofitCharacterFactory
import com.example.rickandmortydemo.character.data.repository.RepositoryCharacterImpl
import com.example.rickandmortydemo.character.domain.usecase.CharacterGetById
import com.example.rickandmortydemo.character.ui.getbyid.viewmodel.SingleCharacterViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SingleCharacterModule(private val context:Context)
{
    @Provides
    @Singleton
    fun provideSingleCharacterViewModelFactory() = SingleCharacterViewModelFactory(CharacterGetById(provideRepositorySingleCharacterImpl()))

    @Provides
    @Singleton
    fun provideRepositorySingleCharacterImpl() = RepositoryCharacterImpl(CharacterApiImpl(RetrofitCharacterFactory.characterApiRetrofit(RickMortyApplication.environments.url)),RickMortyApplication.environments)
}