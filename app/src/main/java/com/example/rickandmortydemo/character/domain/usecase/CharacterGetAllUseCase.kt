package com.example.rickandmortydemo.character.domain.usecase

import com.example.projectarchitecture.common.Failure
import com.example.projectarchitecture.common.Success
import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.rickandmortydemo.character.domain.RepositoryCharacter
import com.example.rickandmortydemo.common.ExampleException
import com.example.projectarchitecture.common.Result
import com.example.rickandmortydemo.character.data.datasource.remote.ErrorCharacterResponse


import java.lang.Exception

class CharacterGetAllUseCase(private val repositoryCharacter: RepositoryCharacter) {
    suspend operator fun invoke():Result<ResponseCharacter, Exception>{
        return when (val result =  repositoryCharacter.characterFunction()){
            is Success -> {
                if(result.value.info!=null){
                    Success(result.value)
                }else Failure(ExampleException("invalid code"))
            }

            is Failure -> {
                if(result.reason is ErrorCharacterResponse) Failure (ExampleException("invalid code")) else (Failure(result.reason))
            }
        }
    }
}
