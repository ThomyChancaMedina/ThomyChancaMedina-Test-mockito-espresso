package com.example.test5doublesfundamentals.Users

import org.jetbrains.annotations.Nullable

/**
 * Created by Thomy.
 */
interface UsersCache {
    fun cacheUser(user: User)

    @Nullable
    fun getUser(userId: String): User

}