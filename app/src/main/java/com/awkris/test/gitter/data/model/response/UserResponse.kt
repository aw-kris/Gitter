package com.awkris.test.gitter.data.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login : String,
    @SerializedName("id")
    val id : Int,
    @SerializedName("node_id")
    val nodeId : String,
    @SerializedName("avatar_url")
    val avatarUrl : String,
    @SerializedName("gravatar_id")
    val gravatarId : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("html_url")
    val htmlUrl : String,
    @SerializedName("followers_url")
    val followersUrl : String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl : String,
    @SerializedName("organizations_url")
    val organizationsUrl : String,
    @SerializedName("repos_url")
    val reposUrl : String,
    @SerializedName("received_events_url")
    val receivedEventsUrl : String,
    @SerializedName("type")
    val type : String,
    @SerializedName("score")
    val score : Double
)