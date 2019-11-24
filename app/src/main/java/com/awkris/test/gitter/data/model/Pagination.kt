package com.awkris.test.gitter.data.model

import android.util.Log

class Pagination private constructor(
    val first: String?,
    val last: String?,
    val next: String?,
    val prev: String?
) {

    class Builder(response: String?) {
        private var first: String? = null
        private var last: String? = null
        private var next: String? = null
        private var prev: String? = null

        init {
            if (response != null) {
                val links = response.split(DELIM_LINKS)
//                Log.d(this.javaClass.simpleName, "number of link: ${links.size}")
                for (link in links) {
                    val segments = link.split(DELIM_LINK_PARAM)
//                    if (segments.size < 2) continue

                    var linkPart = segments[0]
//                    if (!linkPart.startsWith("<") || !linkPart.endsWith(">")) continue

                    linkPart = linkPart.substring(1, linkPart.length - 1)

                        val rel = segments[1].trim().split("=")
//                        if (rel.size < 2 || META_REL != rel[0])
//                            continue

                        var relValue = rel[1]
                        if (relValue.startsWith("\"") && relValue.endsWith("\"")) {
                            relValue = relValue.substring(1, relValue.length - 1)
                        }

                        when (relValue) {
                            META_FIRST -> {
//                                Log.d(this.javaClass.simpleName, "$META_FIRST page: $linkPart")
                                first = linkPart
                            }
                            META_LAST -> {
//                                Log.d(this.javaClass.simpleName, "$META_LAST page: $linkPart")
                                last = linkPart
                            }
                            META_NEXT -> {
//                                Log.d(this.javaClass.simpleName, "$META_NEXT page: $linkPart")
                                next = linkPart
                            }
                            META_PREV -> {
//                                Log.d(this.javaClass.simpleName, "$META_PREV page: $linkPart")
                                prev = linkPart
                            }
                        }
                }
            }
        }

        fun build() = Pagination(this.first, this.last, this.next, this.prev)
    }

    companion object {
        private const val DELIM_LINKS = ","
        private const val DELIM_LINK_PARAM = ";"

        private const val META_REL = "rel"
        private const val META_LAST = "last"
        private const val META_NEXT = "next"
        private const val META_FIRST = "first"
        private const val META_PREV = "prev"
    }
}