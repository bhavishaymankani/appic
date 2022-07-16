package com.bhavishaymankani.machinetestappic.utils

import com.bhavishaymankani.machinetestappic.datasource.Service

object DataRepo {
    suspend fun getData() = Service.api.getData()
}