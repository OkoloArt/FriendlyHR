package com.example.friendlyhr.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class ApiService @Inject constructor(private val client: HttpClient) {

    companion object {
        private const val END_POINT = "https://7077ee10d3b5400b8237578c6c43efd9.api.mockbin.io/"
    }

    suspend fun getPosition() = client.get(END_POINT)
}