import com.project.Main
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class AcceptanceTest {


    @Test
    fun test() {
        var outContent = ByteArrayOutputStream()
        val appOutput = PrintStream(outContent)
        System.setOut(appOutput)

        Main.main(arrayOf())

        val actualResult = outContent.toString()
        val distance = actualResult.split(" ").dropLast(1).last().toInt()
        val observedDistance = 3867
        val tolerance = .05
        val delta = (observedDistance * tolerance).toInt()
        val acceptableDistanceRangeMiles = (observedDistance - delta..observedDistance + delta)

        System.out.println("Calculated distance: " + distance)
        assertTrue(distance in acceptableDistanceRangeMiles)
    }
}