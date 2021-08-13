package com.example.rickandmortydemo.character.ui.getbyid.di

import com.example.rickandmortydemo.character.ui.getbyid.SingleCharacterActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SingleCharacterModule::class])
interface SingleCharacterComponent {
    fun inject(singleCharacterActivity: SingleCharacterActivity)
}