package com.project.GATasks;

import com.project.GA.Chromosome;
import com.project.GA.Interfaces.GeneInter;
import com.project.GA.GeneticAlgUtils;
import com.project.GA.GeneticAlgorithm;

import java.util.concurrent.Callable;

public class TaskMutation implements Callable<Chromosome[]> {
    Chromosome[] chromosomes;

    public TaskMutation(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Chromosome[] call() throws Exception {
        for (int i = 0; i < chromosomes.length; i++) {
            Chromosome chromosome = chromosomes[i];
            double rnd = GeneticAlgUtils.randomDouble(0, 1);
            if (rnd < GeneticAlgorithm.DEFAULT_MUTATION_RATE) {
                int size = chromosome.getNumberOfGenes();

                int r1 = GeneticAlgUtils.getRandomInt(0, size - 1);
                int r2 = GeneticAlgUtils.getRandomInt(0, size - 1);

                GeneInter g1 = chromosome.getGene(r1).copy();
                GeneInter g2 = chromosome.getGene(r2).copy();

                chromosome.setGene(r1, g2);
                chromosome.setGene(r2, g1);
            }
        }
        return chromosomes;
    }
}
