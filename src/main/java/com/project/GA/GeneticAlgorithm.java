package com.project.GA;

import java.util.Arrays;

public class GeneticAlgorithm {

    public static final double DEFAULT_CROSSOVER_RATE = 0.90;
    public static final double DEFAULT_MUTATION_RATE = 0.09;
    public static final double DEFAULT_GENERATION_NUM = 1000;
    public static final int DEFAULT_ELITE = 5;
    public static final int DEFAULT_TOURNAMENT_SIZE = 7;
    // THIS VALUE MUST BE EVEN
    public static final int POPULATION_SIZE = 1000;

    public static int THREAD_MUTATION_NUM = 2;
    public static int THREAD_CROSSOVER_NUM = 2;
    public static int THREAD_FITNESS_NUM = 2;
    public static int THREAD_SELECTION_NUM = 2;

    /**
        This method implements the Canonical Genetic Algorithm
        @param population it is a population that we want to evolve
     */
    public static void runAlgorithm(Population population) throws Exception {

        for (int i = 0; i < DEFAULT_GENERATION_NUM; i++) {

            Chromosome[] c0 = population.performFitnessEvaluation();
            population.setChromosomes(c0);
            Chromosome[] eliteSol = population.getMinFitValue(DEFAULT_ELITE);
            Chromosome[] c1 = population.performSelection();
            population.setChromosomes(c1);
            Chromosome[] c2 = population.performCrossover();
            population.setChromosomes(c2);
            Chromosome[] c3 = population.performMutation();
            population.setChromosomes(c3);
            Chromosome[] c4 = population.performFitnessEvaluation();
            population.setChromosomes(c4);
            Chromosome[] c5 = replacement(population.getChromosomes(), eliteSol);
            population.setChromosomes(c5);
        }
    }

    /**
        This method copies the best N-chromosome from the initial population
        in order to keep the best individuals
        @param p1 a list of chromosomes
        @param eliteSol the chromosomes that will replace the worst chromosomes
                        in p1
        @return a list of chromosomes
     */
    public static Chromosome[] replacement(Chromosome[] p1 , Chromosome[] eliteSol) {
        Chromosome[] newChr = new Chromosome[p1.length];
        Arrays.sort(p1, new ComparatorFitness());
        int i;
        for (i = 0; i < DEFAULT_ELITE; i++) {
            newChr[i] = eliteSol[i].copy();
        }
        for(; i < p1.length; i++) {
            newChr[i] = p1[i].copy();
        }
        return newChr;
    }

}
