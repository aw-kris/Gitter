package com.awkris.test.gitter.view.searchuser

import com.awkris.test.gitter.data.datastore.SearchDataStore
import com.awkris.test.gitter.data.model.DataSource
import com.awkris.test.gitter.data.model.PaginatedList
import com.awkris.test.gitter.data.model.User
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchUserPresenter @Inject constructor(private val dataStore: SearchDataStore) :
    DataSource<User>, SearchUserContract.Presenter {
    private lateinit var view: SearchUserContract.View

    private var userList = mutableListOf<User>()
    private var curKeyword: String? = null
    private var nextPage: Int = 0

    fun setView(view: SearchUserContract.View) {
        this.view = view
    }

    override fun get(position: Int) = userList[position]

    override fun size() = userList.size

    override fun loadMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchUser(keyword: String) {
        view.showProgressBar()
        dataStore.searchUser(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<PaginatedList<User>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onSuccess(item: PaginatedList<User>) {
                        if (item.list.isEmpty()) {
                            userList.clear()
                            nextPage = 0
                            curKeyword = null
                            view.setEmptyStateList(true, keyword)
                        } else {
                            userList.addAll(item.list)
                            if (item.nextPage) nextPage = 2
                            curKeyword = keyword
                            view.apply {
                                setEmptyStateList(false)
                                updateData(userList)
                            }
                        }
                        view.hideProgressBar()
                    }

                    override fun onError(error: Throwable) {
                        userList.clear()
                        nextPage = 0
                        curKeyword = null
                        view.updateData(userList)
                        view.setEmptyStateList(true, error.message, true)
                        view.hideProgressBar()
                    }
                }
            )
    }

    override fun loadMoreUser() {
        val map = mutableMapOf(
            Pair("q", curKeyword!!),
            Pair("page", nextPage.toString())
        )
        view.showProgressBar()
        dataStore.loadMoreUser(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<PaginatedList<User>> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onSuccess(item: PaginatedList<User>) {
                        userList.addAll(item.list)
                        if (item.nextPage) nextPage += 1 else nextPage = 0
                        view.apply {
                            setEmptyStateList(false)
                            updateData(userList)
                            hideProgressBar()
                        }
                    }

                    override fun onError(error: Throwable) {
                        view.hideProgressBar()
                        view.showSnackBar(error.message)
                    }
                }
            )
    }

    override fun isNextPageAvailable(): Boolean {
        return nextPage != 0
    }
}