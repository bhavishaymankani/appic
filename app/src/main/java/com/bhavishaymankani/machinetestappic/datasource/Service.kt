package com.bhavishaymankani.machinetestappic.datasource

import com.bhavishaymankani.machinetestappic.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {

    val api = Retrofit.Builder().run {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
        build().create(Api::class.java)
    }
}