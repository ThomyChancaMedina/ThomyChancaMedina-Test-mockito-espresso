package com.example.testsecon

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`

/**
 * Created by Thomy.
 */
class StringReverserTest {

    lateinit var SUT: StringReverser

    @Before
    fun setUp() {
        SUT = StringReverser()
    }


    @Test
    fun reverse_emptyString_emptyStringReturned() {
        var result = SUT.reverse("")
        assertThat(result, `is`(""))

    }

    @Test
    fun reverse_singleCharacter_sameSringReturned() {
        val result: String = SUT.reverse("a")

        assertThat(result, `is`("a"))

    }

    @Test
    fun reverse_longString_reversedStringReturned() {

        val result: String = SUT.reverse("Vasilliy Zukanov")
        assertThat(result, `is`("vonakuZ yilisaV"))

    }

}