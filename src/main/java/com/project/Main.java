package com.project;

import com.project.GA.Chromosome;
import com.project.GA.Interfaces.GeneInter;
import com.project.GA.GeneticAlgorithm;
import com.project.TSP.City;
import com.project.TSP.TSPGene;
import com.project.TSP.TSPPopulation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    private static final String PROPERTIES_FILE = "TSP.properties";

    public static void main(String[] args) throws Exception {
        System.out.println("TSP Genetic Algorithm");
        FileInputStream file = null;
        Properties props = null;
        try {
            props = new Properties();
            ClassLoader classLoader = Main.class.getClassLoader();
            InputStream resourceAsStream = classLoader.getResourceAsStream(PROPERTIES_FILE);
            if (resourceAsStream == null) {
                throw new IllegalStateException("The file " + PROPERTIES_FILE + " is not there");
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
        for (GeneInter g : bestChromosome.getAllGenes()) {
            City city = tspPopulation.getCity(((TSPGene) g).getValue());
            System.out.print(city.getName() + " ");
        }

        System.out.println("\n\nThe shortest tour distance is: " + Math.round(bestChromosome.getFitness()) + " miles");
    }

}
