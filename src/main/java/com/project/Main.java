package com.project;

import com.project.GA.Chromosome;
import com.project.GA.Interfaces.GeneInter;
import com.project.GA.GeneticAlgorithm;
import com.project.TSP.City;
import com.project.TSP.TSPGene;
import com.project.TSP.TSPPopulation;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Main {

    public static final String PROPERTIES_FILE = "TSP.properties";

    public static void main(String[] args) throws Exception {
        runAlgorithm(PROPERTIES_FILE);
    }

    static TSPCalculationResult runAlgorithm(String propertiesFile) throws Exception {
        System.out.println("TSP Genetic Algorithm");
        FileInputStream file = null;
        Properties props = null;
        try {
            props = new Properties();
            ClassLoader classLoader = Main.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream(propertiesFile);
            if (resourceAsStream == null) {
                throw new IllegalStateException("The file " + propertiesFile + " is not there");
            }
            props.load(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        } finally {
            try {
                file.close();
            } catch (Exception e) {}
        }
        TSPPopulation tspPopulation = new TSPPopulation(GeneticAlgorithm.POPULATION_SIZE, props);
        double start = System.currentTimeMillis();
        GeneticAlgorithm.runAlgorithm(tspPopulation);
        tspPopulation.closeExecutor();
        double end = System.currentTimeMillis();
        double execTime = end - start;
        double execTimeSeconds = execTime / 1000;
        System.out.println("\nExecution time: " + execTimeSeconds + " sec");
        System.out.println("\nThe optimal tour is: ");
        Chromosome bestChromosome = tspPopulation.bestSolution();
        ArrayList<City> cities = new ArrayList<>();
        for (GeneInter g : bestChromosome.getAllGenes()) {
            City city = tspPopulation.getCity(((TSPGene) g).getValue());
            cities.add(city);
            System.out.print(city.getName() + " ");
        }
        double fitness = bestChromosome.getFitness();
        System.out.println("\n\nThe shortest tour distance is: " + Math.round(fitness) + " miles");

        return new TSPCalculationResult(fitness, cities);
    }
}
