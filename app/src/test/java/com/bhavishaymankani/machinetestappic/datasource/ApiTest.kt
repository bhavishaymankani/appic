package com.bhavishaymankani.machinetestappic.datasource

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class ApiTest {

    private lateinit var api: Api

    @Before
    fun setUp() {
        api = Service.api
    }

    @Test
    fun checkData() = runBlocking {
        val response = api.getData()
        assertNotNull(response.body())
    }
}