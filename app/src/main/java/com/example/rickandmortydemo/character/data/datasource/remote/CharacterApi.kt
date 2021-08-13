package com.example.rickandmortydemo.character.data.datasource.remote

import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.projectarchitecture.common.Result
import com.example.rickandmortydemo.character.data.repository.ResponseCharacterData
import com.example.rickandmortydemo.character.domain.entity.Character

interface CharacterApi {
    suspend fun characterFunction():Result<ResponseCharacter, Exception>
    suspend fun characterFnGetById(id:Int):Result<ResponseCharacterData, Exception>
}