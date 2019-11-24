package com.awkris.test.gitter.data.datastore

import android.util.Log
import com.awkris.test.gitter.data.api.SearchApi
import com.awkris.test.gitter.data.api.utils.HeaderConstants
import com.awkris.test.gitter.data.mapper.transform
import com.awkris.test.gitter.data.model.PaginatedList
import com.awkris.test.gitter.data.model.Pagination
import com.awkris.test.gitter.data.model.User
import io.reactivex.Single
import javax.inject.Inject

class SearchDataStore @Inject constructor(private val searchApi: SearchApi) {
    fun searchUser(keyword: String): Single<PaginatedList<User>> {
        return searchApi.searchUser(keyword).map {
            val pagination = Pagination.Builder(it.headers().get(HeaderConstants.HEADER_LINK)).build()
            PaginatedList(
                it.body()!!.items.map(::transform),
                !pagination.next.isNullOrEmpty()
            )
        }
    }

    fun loadMoreUser(query: Map<String, String>): Single<PaginatedList<User>> {
        return searchApi.loadMoreUser(query).map {
            val pagination = Pagination.Builder(it.headers().get(HeaderConstants.HEADER_LINK)).build()
//            Log.d(this.javaClass.simpleName, "next page: ${pagination.next}")
            PaginatedList(
                it.body()!!.items.map(::transform),
                !pagination.next.isNullOrEmpty()
            )
        }
    }
}