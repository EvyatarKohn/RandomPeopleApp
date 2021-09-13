package com.evyatar.motorolaassignment.model.usersmodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dob(
    @SerializedName("age")
    @Expose
    val age: Int,

    @SerializedName("date")
    @Expose
    val date: String
)