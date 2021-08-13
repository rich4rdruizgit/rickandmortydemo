package com.example.rickandmortydemo.character.data.repository

import com.squareup.moshi.Json

data class ResponseCharacter(
    @field:Json(name = "info") val info:ResponseCharacterInfo?,
    @field:Json(name = "results") val results:MutableList<ResponseCharacterData>?

)

data class ResponseCharacterInfo(@field:Json(name = "count") val count:Int,
                @field:Json(name = "pages") val page:Int,
                @field:Json(name = "next") val next:String?,
                @field:Json(name = "prev") val prev:String?,
                )

data class ResponseCharacterData(
    @field:Json(name = "id") val id:Int,
    @field:Json(name = "name") val name:String,
    @field:Json(name = "status") val status:String,
    @field:Json(name = "type") val type:String?,
    @field:Json(name = "gender") val gender:String,
    @field:Json(name = "image") val image:String?,
    @field:Json(name = "url") val url:String?,

)

