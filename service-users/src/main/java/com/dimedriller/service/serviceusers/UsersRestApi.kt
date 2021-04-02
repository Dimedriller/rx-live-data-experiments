package com.dimedriller.service.serviceusers

import com.dimedriller.service.serviceusers.json.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UsersRestApi {
    @GET("/api/")
    fun getUsers(@Query("page") page: Int = 1,
            @Query("results") results: Int = 15)
            : Single<UsersResponse>

}