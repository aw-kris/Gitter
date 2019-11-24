package com.awkris.test.gitter.data.model

class PaginatedList<out T>(
    val list: List<T>,
    val nextPage: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PaginatedList<*>

        if (list != other.list || nextPage != other.nextPage) return false
        return true
    }

    override fun hashCode(): Int {
        var result = 31 + list.hashCode()
        result = 31 * result + (if (nextPage) 1 else 0)
        return result
    }

    override fun toString(): String {
        return "PaginatedList(list='$list', nextPage='$nextPage')"
    }
}