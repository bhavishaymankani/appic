package com.bhavishaymankani.machinetestappic.datasource

import com.bhavishaymankani.machinetestappic.datasource.model.Data
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("data.json")
    suspend fun getData() : Response<Data>
}