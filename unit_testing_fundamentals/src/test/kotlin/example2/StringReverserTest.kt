package example2

import com.developer.unitTestingFundamentals.example2.StringReverser
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
class StringReverserTest{
    var SUT=StringReverser()

    @Before
    fun setUp(){
        SUT= StringReverser()
    }

    @Test
    fun `reverse emptyString empty StringReturned`(){
        val result= SUT.reverse("")
        assertThat(result,`is`(""))
    }

    @Test
    fun `reverse singleCharacter same StringReturned`(){
        val result=SUT.reverse("a")
        assertThat(result,`is`("a"))
    }

}