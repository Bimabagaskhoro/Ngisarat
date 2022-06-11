package com.bimabagaskhoro.myapplicatio.data.source.remote.network

import com.bimabagaskhoro.myapplicatio.data.source.remote.response.ResponseSuccess
import retrofit2.http.*

interface ApiService {
    @GET("getAllAlphabet.php")
    suspend fun getAllAlphabet(): ResponseSuccess

    @GET("getAlphabetById.php")
    suspend fun getAlphabetById(
        @Query("id_alphabet") id: Int,
    ): ResponseSuccess
}