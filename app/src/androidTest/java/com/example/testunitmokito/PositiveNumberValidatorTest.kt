package com.example.testunitmokito

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by Thomy.
 */
class PositiveNumberValidatorTest{


   lateinit var SUT: PositiveNumberValidator

    @Before
    fun setup() {
        SUT = PositiveNumberValidator()
    }


    @Test
    fun test1() {
        val result: Boolean = SUT.isPositive(-1)
        assertThat(   result, `is`(false))
    }

    @Test
    fun test2(){
        val result: Boolean = SUT.isPositive(0)
        assertThat(result, `is`(false))
    }

    @Test
    fun test3(){
        val result: Boolean = SUT.isPositive(1)
        assertThat(result, `is`(true))
    }

}