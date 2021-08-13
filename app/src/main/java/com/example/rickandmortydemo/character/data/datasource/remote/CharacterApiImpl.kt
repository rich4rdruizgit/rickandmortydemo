package com.example.rickandmortydemo.character.data.datasource.remote

import com.example.projectarchitecture.common.Failure
import com.example.projectarchitecture.common.HttpCode
import com.example.projectarchitecture.common.Result
import com.example.projectarchitecture.common.Success
import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.rickandmortydemo.character.data.repository.ResponseCharacterData
import retrofit2.Response

private const val TIME_OUT_NETWORK = "Time out Network"
private const val ERROR_TO_PARSE = "Error to Parse element"
private const val REQUEST_INVALID = "Request invalid"
private const val UNAUTHORIZED_REQUEST = "UnAuthorized request"
private const val ERROR_UNKNOW = "Un know error"

class CharacterApiImpl(private val _retrofitCharacterApi: RetrofitCharacterApi):CharacterApi {

    override suspend fun characterFunction(): Result<ResponseCharacter, Exception> {

        val result = _retrofitCharacterApi.characterFunction()

        return when (result.code()){

            HttpCode.OK.code -> {
                result.body()?.let {
                    return Success(it)
                } ?: kotlin.run {
                    return Failure(ErrorCharacterResponse(ERROR_TO_PARSE))
                }
            }

            else -> validateHttpCode(result.code())
        }
    }

    override suspend fun characterFnGetById(id: Int): Result<ResponseCharacterData, Exception> {

        val result = _retrofitCharacterApi.characterFnGetById(id)

        return when (result.code()){

            HttpCode.OK.code-> {
                result.body()?.let{
                    return Success(it)
                }?: kotlin.run{
                    return Failure(ErrorCharacterResponse(ERROR_TO_PARSE))
                }
            }
            else -> validateHttpCode(result.code())
        }
    }

    private fun validateHttpCode(result: Int): Result<Nothing,Exception> {
        return when (result){
            HttpCode.TIME_OUT.code -> Failure(ErrorCharacterResponse(TIME_OUT_NETWORK))
            HttpCode.BAD_REQUEST.code -> Failure(ErrorCharacterResponse(REQUEST_INVALID))
            HttpCode.UNAUTHORIZED.code -> Failure(ErrorCharacterResponse(UNAUTHORIZED_REQUEST))
            else -> Failure(ErrorCharacterResponse(ERROR_UNKNOW))
        }
    }


}