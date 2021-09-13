package com.evyatar.motorolaassignment.model.usersmodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersData(
    @SerializedName("info")
    @Expose
    val info: Info,

    @SerializedName("results")
    @Expose
    val results: List<Result>
)