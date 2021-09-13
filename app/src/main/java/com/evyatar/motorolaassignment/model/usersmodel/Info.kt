package com.evyatar.motorolaassignment.model.usersmodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val results: Int,

    @SerializedName("seed")
    @Expose
    val seed: String,

    @SerializedName("version")
    @Expose
    val version: String
)