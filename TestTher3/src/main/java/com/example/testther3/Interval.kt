package com.example.testther3


import java.lang.IllegalArgumentException
import kotlin.properties.Delegates

/**
 * Created by Thomy.
 */
class Interval( start: Int, end: Int) {




    var mStart: Int by Delegates.notNull()
    var mEnd: Int by Delegates.notNull()

    init {

        if (start >= end){
            throw IllegalArgumentException("invalid interval range")
        }

        mStart = start
        mEnd = end

    }



    fun getStart():Int{
        return mStart
    }

    fun getEnd():Int{
        return mEnd
    }

}