package com.example.test5doublesfundamentals.example5

class UserInputValidator {
    fun isValidFullName(fullName: String?): Boolean {
        return FullNameValidator.isValidFullName(fullName!!)
    }

    fun isValidUsername(username: String?): Boolean {
        return ServerUsernameValidator.isValidUsername(
            username
        )
    }
}