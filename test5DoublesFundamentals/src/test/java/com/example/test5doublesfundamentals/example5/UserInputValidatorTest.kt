package com.example.test5doublesfundamentals.example5

import org.hamcrest.core.Is.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

/**
 * Created by Thomy.
 */
class UserInputValidatorTest {


    var SUT = UserInputValidator()

    @Before
    fun setUp() {
        SUT = UserInputValidator()
    }

    @Test
    @Throws(Exception::class)
    fun isValidFullName_validFullName_trueReturned() {
        val result: Boolean = SUT.isValidFullName("validFullName")
        assertThat(result, `is`(true))

    }

    @Test
    @Throws(Exception::class)
    fun isValidFullName_invalidFullName_falseReturned() {
        val result = SUT.isValidFullName("")
        assertThat(result, `is`(false))

    }

    @Test
    @Throws(Exception::class)
    fun `is ValidUsername validUsername trueReturned`() {
        val result = SUT.isValidUsername("validUsername")
        assertThat(result, `is`(true))
    }


    @Test
    @Throws(Exception::class)
    fun `isValidUsername invalidUsername falseReturned`() {
        val result = SUT.isValidUsername("")
        assertThat(result, `is`(false))

    }

}