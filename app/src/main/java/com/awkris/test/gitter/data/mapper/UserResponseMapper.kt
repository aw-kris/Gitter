package com.awkris.test.gitter.data.mapper

import com.awkris.test.gitter.data.model.User
import com.awkris.test.gitter.data.model.response.UserResponse

fun transform(response: UserResponse): User {
    return with(response) {
        User(login, avatarUrl, htmlUrl)
    }
}