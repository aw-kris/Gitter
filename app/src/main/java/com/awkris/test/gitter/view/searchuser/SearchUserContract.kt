package com.awkris.test.gitter.view.searchuser

import com.awkris.test.gitter.data.model.User

class SearchUserContract {
    interface View {
        fun updateData(sourceDataList: List<User>)
        fun showProgressBar()
        fun hideProgressBar()
        fun setEmptyStateList(visibility: Boolean, keyword: String? = null, isError: Boolean? = null)
        fun showSnackBar(message: String?)
    }

    interface Presenter {
        fun searchUser(keyword:String)
        fun loadMoreUser()
        fun isNextPageAvailable(): Boolean
    }
}