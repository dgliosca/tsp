package com.project.GATasks;

import com.project.GA.Chromosome;
import com.project.GA.GeneticAlgUtils;
import com.project.GA.GeneticAlgorithm;

import java.util.concurrent.Callable;

public class TaskSelection implements Callable<Chromosome[]> {
    private Chromosome[] chromosomes;
    private int tournamentSize;

    /**
        The class constructor that specifies the tournament size and
        the list of chromosomes to use
     */
    public TaskSelection(int tournamentSize, Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
        this.tournamentSize = tournamentSize;
    }

    /**
        This method implements the tournament selection
        @return a list of selected chromosomes for breeding
     */
    public Chromosome[] call() throws Exception {
        Chromosome[] retChromosome = new Chromosome[chromosomes.length];
        for (int k = 0; k < chromosomes.length; k++) {
            Chromosome[] tournament = new Chromosome[GeneticAlgorithm.DEFAULT_TOURNAMENT_SIZE];
            double[] tournamentFitness = new double[GeneticAlgorithm.DEFAULT_TOURNAMENT_SIZE];

            for (int i = 0; i < GeneticAlgorithm.DEFAULT_TOURNAMENT_SIZE; i++) {
                int index = GeneticAlgUtils.getRandomInt(0, chromosomes.length - 1);
                tournament[i] = chromosomes[index].copy();
                tournamentFitness[i] = chromosomes[index].getFitness();
            }

            Chromosome bestIndividual = tournament[0];
            double bestFitness = tournamentFitness[0];
            for(int i = 1; i < GeneticAlgorithm.DEFAULT_TOURNAMENT_SIZE; i++)
                if(tournamentFitness[i] < bestFitness) {
                    bestIndividual = tournament[i].copy();
                    bestFitness = tournamentFitness[i];
                }
            retChromosome[k] = bestIndividual;
        }
        return retChromosome;
    }
}
