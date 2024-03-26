package com.example.friendlyhr.data

import com.example.friendlyhr.data.model.PositionResponse
import com.example.friendlyhr.network.ApiService
import io.ktor.client.call.body
import javax.inject.Inject

interface FriendlyRepository {
    suspend fun fetchData(): Result<PositionResponse>
}

class FriendlyRepositoryImpl @Inject constructor(private val apiService: ApiService) : FriendlyRepository {
    override suspend fun fetchData(): Result<PositionResponse> {
        return try {
            Result.success(apiService.getPosition().body())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
