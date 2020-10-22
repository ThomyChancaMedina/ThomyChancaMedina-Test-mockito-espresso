package com.example.test5doublesfundamentals.Users

/**
 * Created by Thomy.
 */
class User {

    var mUserId:String
    var mFullName:String
    var mImageUrl:String

    constructor(userId:String,
                fullName:String,
                imageUrl:String){
        mUserId = userId
        mFullName = fullName
        mImageUrl = imageUrl

    }

    fun getUserId():String{
        return mUserId
    }
    fun getFullName():String{
        return mFullName
    }
    fun getImagenUrl():String{
        return mImageUrl
    }

}