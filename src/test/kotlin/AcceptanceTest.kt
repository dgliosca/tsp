import com.project.GA.GeneticAlgUtils
import com.project.GA.GeneticAlgorithm
import com.project.Main
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*

class AcceptanceTest {

    @Test
    fun test() {
        GeneticAlgUtils.random = Random(2342432)
        GeneticAlgorithm.THREAD_CROSSOVER_NUM = 1
        GeneticAlgorithm.THREAD_FITNESS_NUM = 1
        GeneticAlgorithm.THREAD_MUTATION_NUM = 1
        GeneticAlgorithm.THREAD_SELECTION_NUM = 1
        var outContent = ByteArrayOutputStream()
        val appOutput = PrintStream(outContent)
        System.setOut(appOutput)

        Main.main(arrayOf())

        val actualResult = outContent.toString()
        val distance = actualResult.split(" ").dropLast(1).last().toInt()
        val observedDistance = 3867

        assertThat(distance, equalTo(observedDistance))
    }
}