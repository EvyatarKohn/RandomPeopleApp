package com.evyatar.motorolaassignment.model.usersmodel


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Timezone(
    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("offset")
    @Expose
    val offset: String
)