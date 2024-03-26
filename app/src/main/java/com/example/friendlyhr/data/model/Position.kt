package com.example.friendlyhr.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PositionResponse(
    @SerialName("positions")
    val positions: List<Position> ,
)

@Serializable
data class Position(
    @SerialName("companyDescription")
    val companyDescription: String ,
    @SerialName("companyName")
    val companyName: String ,
    @SerialName("id")
    val id: Int ,
    @SerialName("name")
    val name: String ,
    @SerialName("positionDescription")
    val positionDescription: String ,
    @SerialName("positionImageUrl")
    val positionImageUrl: String ,
    @SerialName("salaryRange")
    val salaryRange: String ,
    @SerialName("technologies")
    val technologies: List<String> ,
) : java.io.Serializable

