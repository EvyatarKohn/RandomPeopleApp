package com.evyatar.motorolaassignment.model.usersmodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("cell")
    @Expose
    val cell: String,

    @SerializedName("dob")
    @Expose
    val dob: Dob,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("gender")
    @Expose
    val gender: String,

    @SerializedName("id")
    @Expose
    val id: Id,

    @SerializedName("location")
    @Expose
    val location: Location,

    @SerializedName("login")
    @Expose
    val login: Login,

    @SerializedName("name")
    @Expose
    val name: Name,

    @SerializedName("nat")
    @Expose
    val nat: String,

    @SerializedName("phone")
    @Expose
    val phone: String,

    @SerializedName("picture")
    @Expose
    val picture: Picture,

    @SerializedName("registered")
    @Expose
    val registered: Registered
)