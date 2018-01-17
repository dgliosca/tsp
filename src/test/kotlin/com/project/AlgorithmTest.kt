package com.project

import com.project.GA.GeneticAlgUtils
import com.project.GA.GeneticAlgorithm
import com.project.Main.PROPERTIES_FILE
import com.project.TSP.City
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*
import kotlin.math.roundToInt

class AlgorithmTest {

    @Test
    fun `tsp result is a repeatable known solution`() {
        GeneticAlgUtils.random = Random(2342432)
        GeneticAlgorithm.THREAD_CROSSOVER_NUM = 1
        GeneticAlgorithm.THREAD_FITNESS_NUM = 1
        GeneticAlgorithm.THREAD_MUTATION_NUM = 1
        GeneticAlgorithm.THREAD_SELECTION_NUM = 1
        val outContent = ByteArrayOutputStream()
        val appOutput = PrintStream(outContent)
        System.setOut(appOutput)

        val tspResult = Main.runAlgorithm(PROPERTIES_FILE)

        assertThat(tspResult.totalDistance.roundToInt(), equalTo(3867))

        val expectedCityNames = listOf("Rome", "Zurich", "Prague", "Berlin", "Amsterdam", "Bruxelles", "London", "Paris", "Lisbon", "Madrid")
        assertThat(tspResult.cities.map { it.name }, equalTo(expectedCityNames))
    }

}