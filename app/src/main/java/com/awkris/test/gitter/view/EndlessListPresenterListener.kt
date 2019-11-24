package com.awkris.test.gitter.view

interface EndlessListPresenterListener {
    fun setEmptyStateVisibility(isVisible: Boolean)
    fun setLoadingMoreVisibility(isVisible: Boolean)

    fun onMoreItemsLoaded(offset: Int, count: Int)

    fun onItemsRemoved(offset: Int, count: Int)

    fun onLoadMoreError(throwable: Throwable, errorMessage: String)
    fun onLoadMoreCompleted()
    fun onStartLoadingMore()
}