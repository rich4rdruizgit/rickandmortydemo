package com.example.rickandmortydemo.character.data.datasource.remote

import com.example.projectarchitecture.common.Failure
import com.example.projectarchitecture.common.Success
import com.example.rickandmortydemo.character.data.datasource.CharacterTestFactory
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterApiImplTest : TestCase(){

    @Mock
    private lateinit var _retrofit: RetrofitCharacterApi

    @InjectMocks
    private lateinit var _characterApiImpl: CharacterApiImpl

    @Test
    fun `when success with http 200 response`(){
        runBlocking {
            val response = CharacterTestFactory.createSuccessResponse(200)

            Mockito.`when`(_retrofit.characterFunction()).thenReturn(response)

            when (val result = _characterApiImpl.characterFunction()){
                is Success -> assertNotNull(result.value.results)
                else -> assert(false)
            }

        }
    }

    @Test
    fun `when success with http 200 response and null body to parse`(){
        runBlocking {
            val response = CharacterTestFactory.createSuccessNullBodyResponse(200)

            Mockito.`when`(_retrofit.characterFunction()).thenReturn(response)

            when (val result = _characterApiImpl.characterFunction()){

                is Failure -> assert(result.reason is ErrorCharacterResponse)
                else -> assert(false)

            }

        }
    }


    @Test
    fun `when response bad request 400`(){
        runBlocking {
            val response = CharacterTestFactory.createFailureResponse(400)

            Mockito.`when`(_retrofit.characterFunction()).thenReturn(response)

            when (val result = _characterApiImpl.characterFunction()){

                is Failure -> assert(result.reason is ErrorCharacterResponse)
                else -> assert(false)

            }

        }
    }

    @Test
    fun `when response unauthorized 401`(){
        runBlocking {
            val response = CharacterTestFactory.createFailureResponse(401)

            Mockito.`when`(_retrofit.characterFunction()).thenReturn(response)

            when (val result = _characterApiImpl.characterFunction()){

                is Failure -> assert(result.reason is ErrorCharacterResponse)
                else -> assert(false)

            }

        }
    }

    @Test
    fun `when response uncontrolled`(){
        runBlocking {
            val response = CharacterTestFactory.createFailureResponse(600)

            Mockito.`when`(_retrofit.characterFunction()).thenReturn(response)

            when (val result = _characterApiImpl.characterFunction()){

                is Failure -> assert(result.reason is ErrorCharacterResponse)
                else -> assert(false)

            }

        }
    }



}