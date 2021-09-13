package com.evyatar.motorolaassignment.netwoek

import com.evyatar.motorolaassignment.model.usersmodel.UsersData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("api")
    suspend fun getUserData(@Query("results") results: String): Response<UsersData>
}