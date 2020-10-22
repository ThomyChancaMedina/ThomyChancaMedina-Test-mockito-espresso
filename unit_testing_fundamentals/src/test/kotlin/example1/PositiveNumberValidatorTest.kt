package example1

import com.developer.unitTestingFundamentals.example1.PositiveNumberValidator
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PositiveNumberValidatorTest{

     var SUT=PositiveNumberValidator()

    @Before
    fun setup(){
        SUT= PositiveNumberValidator()
    }

    /**
     * si es positivo dara error en el test
     */
    @Test
    fun test1(){
        val result= SUT.isPositive(-1)
        Assert.assertThat(result,`is`(false))
    }

    @Test
    fun test2(){
        val result=SUT.isPositive(0)
        Assert.assertThat(result,`is`(false))
    }

    @Test
    fun test3(){
        val result=SUT.isPositive(1)
        Assert.assertThat(result,`is`(true))
    }

}