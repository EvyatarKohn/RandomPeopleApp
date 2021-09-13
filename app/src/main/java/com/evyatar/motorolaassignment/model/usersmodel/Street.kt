package com.evyatar.motorolaassignment.model.usersmodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Street(
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("number")
    @Expose
    val number: Int
)