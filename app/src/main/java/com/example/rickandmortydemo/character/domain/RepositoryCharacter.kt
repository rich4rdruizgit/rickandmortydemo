package com.example.rickandmortydemo.character.domain

import com.example.projectarchitecture.common.Result
import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.rickandmortydemo.character.data.repository.ResponseCharacterData

interface RepositoryCharacter {
    suspend fun characterFunction():Result<ResponseCharacter, Exception>
    suspend fun characterGetByID(id:Int):Result<ResponseCharacterData, Exception>
}
