package com.awkris.test.gitter.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("a")
    val login : String,
    @SerializedName("b")
    val avatarUrl : String,
    @SerializedName("c")
    val htmlUrl : String
)