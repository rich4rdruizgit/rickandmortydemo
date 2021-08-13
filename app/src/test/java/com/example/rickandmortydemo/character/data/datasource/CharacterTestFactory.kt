package com.example.rickandmortydemo.character.data.datasource

import com.example.rickandmortydemo.character.data.repository.ResponseCharacter
import com.example.rickandmortydemo.character.data.repository.ResponseCharacterData
import com.example.rickandmortydemo.character.data.repository.ResponseCharacterInfo
import okhttp3.ResponseBody
import retrofit2.Response

class CharacterTestFactory {
    
    companion object{

        fun createSuccessResponse(code: Int): Response<ResponseCharacter?>? =
            Response.success(code, createResponseCharacter())

        fun createResponseCharacter(): ResponseCharacter =
            ResponseCharacter(
                ResponseCharacterInfo(1,1,"",""),
                mutableListOf(ResponseCharacterData(10,"prueba", "ok",
                "human","m",".jpg","url"))
            )

        fun createSuccessNullBodyResponse(code: Int): Response<ResponseCharacter?>? {
            val response: ResponseCharacter? = null
            return Response.success(code, response)
        }

        fun createFailureResponse(code: Int): Response<ResponseCharacter?>? {
            val response: ResponseBody = ResponseBody.create(null,"")
            return Response.error(code, response)
        }


    }
}