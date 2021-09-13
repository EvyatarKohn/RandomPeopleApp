package com.evyatar.motorolaassignment.model.usersmodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("md5")
    @Expose
    val md5: String,

    @SerializedName("password")
    @Expose
    val password: String,

    @SerializedName("salt")
    @Expose
    val salt: String,

    @SerializedName("sha1")
    @Expose
    val sha1: String,

    @SerializedName("sha256")
    @Expose
    val sha256: String,

    @SerializedName("username")
    @Expose
    val username: String,

    @SerializedName("uuid")
    @Expose
    val uuid: String
)