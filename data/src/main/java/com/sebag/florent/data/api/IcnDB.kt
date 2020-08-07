package com.sebag.florent.data.api

import com.sebag.florent.data.entities.ApiResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IcnDB {

    @GET("jokes/random")
    fun randomJoke(): Single<ApiResponse>

}