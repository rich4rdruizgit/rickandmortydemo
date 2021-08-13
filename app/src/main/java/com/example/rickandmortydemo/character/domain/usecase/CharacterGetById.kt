package com.example.rickandmortydemo.character.domain.usecase

import com.example.projectarchitecture.common.Failure
import com.example.projectarchitecture.common.Result
import com.example.projectarchitecture.common.Success
import com.example.rickandmortydemo.character.data.datasource.remote.ErrorCharacterResponse
import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.rickandmortydemo.character.data.repository.ResponseCharacterData
import com.example.rickandmortydemo.character.domain.RepositoryCharacter
import com.example.rickandmortydemo.common.ExampleException
import java.lang.Exception


class CharacterGetById(private val repositoryCharacter: RepositoryCharacter) {
    suspend operator fun invoke(id:Int): Result<ResponseCharacterData, Exception> {
        return when (val result =  repositoryCharacter.characterGetByID(id)){
            is Success -> {
                Success(result.value)
            }

            is Failure -> {
                if(result.reason is ErrorCharacterResponse) Failure (ExampleException("invalid code")) else (Failure(result.reason))
            }
        }
    }
}
