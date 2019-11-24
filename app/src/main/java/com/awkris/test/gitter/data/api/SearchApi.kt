package com.awkris.test.gitter.data.api

import com.awkris.test.gitter.data.api.utils.UrlConstants
import com.awkris.test.gitter.data.model.response.SearchUserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchApi {
    @GET(UrlConstants.SEARCH_USER)
    fun searchUser(@Query("q") keyword: String): Single<Response<SearchUserResponse>>

    @GET(UrlConstants.SEARCH_USER)
    fun loadMoreUser(@QueryMap query: Map<String, String>): Single<Response<SearchUserResponse>>
}