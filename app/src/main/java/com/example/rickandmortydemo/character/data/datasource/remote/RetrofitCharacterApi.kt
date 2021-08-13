package com.example.rickandmortydemo.character.data.datasource.remote

import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.rickandmortydemo.character.data.repository.ResponseCharacterData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitCharacterApi {

    @GET("character")
    suspend fun characterFunction(): Response<ResponseCharacter?>

    @GET("character/{id}")
    suspend fun characterFnGetById(@Path("id")id:Int):Response<ResponseCharacterData?>

}