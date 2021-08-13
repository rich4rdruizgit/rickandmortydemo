package com.example.rickandmortydemo.character.data.repository

import com.example.projectarchitecture.common.Success
import com.example.rickandmortydemo.character.data.datasource.CharacterTestFactory
import com.example.rickandmortydemo.character.data.datasource.remote.CharacterApi
import com.example.rickandmortydemo.common.Environments
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Inject

@RunWith(MockitoJUnitRunner::class)
class RepositoryCharacterImplTest : TestCase(){

     @Mock
     private lateinit var _remote : CharacterApi

     private lateinit var _environments:  Environments

     private lateinit var _repositoryCharacterImpl: RepositoryCharacterImpl

     @Before
     fun setUpInject(){
         _environments = Environments()
         _repositoryCharacterImpl = RepositoryCharacterImpl(_remote, _environments)
     }

    @Test
    fun `when success response`(){
        runBlocking {

            Mockito.`when`(_remote.characterFunction())
                .thenReturn(Success(CharacterTestFactory.createResponseCharacter()))

            when (val result = _repositoryCharacterImpl.characterFunction()){
                is Success -> assertNotNull(result.value.results)
                else -> assert(false)
            }

        }
    }


}