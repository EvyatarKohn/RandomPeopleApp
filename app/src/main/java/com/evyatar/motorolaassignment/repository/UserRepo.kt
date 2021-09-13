package com.evyatar.motorolaassignment.repository

import com.evyatar.motorolaassignment.netwoek.MainApi
import javax.inject.Inject

class UserRepo @Inject constructor(private val mainApi: MainApi) {

    suspend fun getUserData() = mainApi.getUserData("10")
}