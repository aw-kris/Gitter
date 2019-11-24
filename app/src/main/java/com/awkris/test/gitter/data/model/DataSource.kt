package com.awkris.test.gitter.data.model

interface DataSource<out T> {
    fun get(position: Int): T
    fun size(): Int
    fun loadMore()
}