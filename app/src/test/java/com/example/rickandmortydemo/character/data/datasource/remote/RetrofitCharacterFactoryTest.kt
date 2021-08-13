package com.example.rickandmortydemo.character.data.datasource.remote

import com.example.rickandmortydemo.RickMortyApplication
import com.example.rickandmortydemo.common.Environments
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RetrofitCharacterFactoryTest : TestCase(){

    @Before
    fun setup(){
        RickMortyApplication.init(
            Environments()
        )
    }

    @Test
    fun `create retrofit api`(){
        assertNotNull(RetrofitCharacterFactory.characterApiRetrofit(RickMortyApplication.environments.url))
    }
}