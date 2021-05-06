package com.sebag.florent.data.entities

import com.google.gson.annotations.SerializedName
import com.sebag.florent.domain.models.JokeModel

data class ApiResponse(

    @SerializedName("type")
    val type: String,

    @SerializedName("value")
    val joke: JokeModel
)