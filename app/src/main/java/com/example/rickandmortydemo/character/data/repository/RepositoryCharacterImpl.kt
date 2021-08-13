package com.example.rickandmortydemo.character.data.repository

import com.example.projectarchitecture.common.Result
import com.example.rickandmortydemo.character.data.datasource.remote.CharacterApi
import com.example.rickandmortydemo.character.domain.RepositoryCharacter
import com.example.rickandmortydemo.common.Environments

class RepositoryCharacterImpl(private val _remote:CharacterApi, private val _environments: Environments): RepositoryCharacter {
    override suspend fun characterFunction(): Result<ResponseCharacter, Exception> {
        return _remote.characterFunction()
    }

    override suspend fun characterGetByID(id: Int): Result<ResponseCharacterData, Exception> {
        return _remote.characterFnGetById(id)
    }


}