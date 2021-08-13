package com.example.rickandmortydemo.character.ui.getall.di

import com.example.rickandmortydemo.character.ui.getall.CharacterActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CharacterModule::class])
interface CharacterComponent {
    fun inject(characterActivity: CharacterActivity)
}