package com.example.test5doublesfundamentals.networking

/**
 * Created by Thomy.
 */
interface UserProfileHttpEndpointSync {

    @Throws(NetworkErrorException::class)
    fun getUserProfile(userId: String): EndpointResult

    enum class EndpointResultSatus {
        SUCCESS, AUTH_ERROR, SERVER_ERROR, GENERAL_ERROR
    }

    class EndpointResult {
        var mStatus: EndpointResultSatus
        var mUserId: String
        var mFullName: String
        var mImageUrl: String

        constructor(
            satus: EndpointResultSatus,
            userId: String,
            fullName: String,
            imageUrl: String
        ) {
            mStatus = satus
            mUserId = userId
            mFullName = fullName
            mImageUrl = imageUrl
        }


        fun getStatus(): EndpointResultSatus {
            return mStatus
        }

        fun getUserId(): String {
            return mUserId
        }

        fun getFullName(): String {
            return mFullName
        }

        fun getImageUrl(): String {
            return mImageUrl
        }

    }

}