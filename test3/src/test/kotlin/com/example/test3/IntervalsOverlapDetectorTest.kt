package com.example.test3

import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by Thomy.
 */
class IntervalsOverlapDetectorTest {


    lateinit var SUT: IntervalsOverlapDetector

    @Before
    fun setUp() {
        SUT = IntervalsOverlapDetector()
    }

    //interval1 is before interval2
    @Test
    fun isOverlap_interval1BeforeInterval2_falseReturned() {

        val interval1 = Interval(-1, 5)
        val interval2 = Interval(8, 12)

        val result: Boolean = SUT.isOverlap(interval1, interval2)

        assertThat(result, CoreMatchers.`is`(false))

    }


    //interval1 overlaps interval2 on start
    @Test
    fun isOverlap_interval1OverlapsInterval2OnStart_trueReturned() {

        val interval1 = Interval(-1, 5)
        val interval2 = Interval(3, 12)

        val result: Boolean = SUT.isOverlap(interval1, interval2)
        assertThat(result, CoreMatchers.`is`(true))

    }

    //interval1 is contained within interval2

    @Test
    fun isOverlap_interval1ContainsInterval2_falseReturned() {

        val interval1 = Interval(-1, 5)
        val interval2 = Interval(-4, 12)

        val result: Boolean = SUT.isOverlap(interval1, interval2)
        assertThat(result, CoreMatchers.`is`(true))

    }

    //interval1 contains interval2
    @Test
    fun isOverlap_interval1ContainsInterval2_trueReturned() {

        val interval1 = Interval(-1, 5)
        val interval2 = Interval(0, 3)

        val result: Boolean = SUT.isOverlap(interval1, interval2)
        assertThat(result, CoreMatchers.`is`(true))

    }

    //interval1 overlaps interval2 on end
    @Test
    fun isOverlap_interval1OverlapsInterval2OnEnd_trueReturned() {

        val interval1 = Interval(-1, 5)
        val interval2 = Interval(-4, 4)

        val result: Boolean = SUT.isOverlap(interval1, interval2)
        assertThat(result, CoreMatchers.`is`(true))

    }

    //insterval1 is after interval2
    @Test
    fun isOverlap_interval1AfterInterval2_falseReturned() {

        val interval1 = Interval(-1, 5)
        val interval2 = Interval(-10, -3)

        val result: Boolean = SUT.isOverlap(interval1, interval2)
        assertThat(result, CoreMatchers.`is`(false))

    }


}