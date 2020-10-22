package com.example.testsecon

/**
 * Created by Thomy.
 */
class StringReverser {

    fun reverse(string: String): String {

        val sb:StringBuilder= StringBuilder()

        for (i in string.length - 1 downTo 0) {
            sb.append(string[i])
        }

        return sb.toString()

    }

}