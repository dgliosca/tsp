package com.project.GA;

import com.project.GA.Chromosome;

import java.util.Comparator;

public class ComparatorFitness implements Comparator<Chromosome> {

    /**
        This methods return -1 if the first chromosome fitness is less than
        the second chromosome fitness. It return +1 if if the first chromosome
        fitness is greater than the second chromosome fitness and 0 if the
        fitness value is equal for both chromosomes
     @param chromosome1 first chromosome to compare
     @param chromosome2 second chromosome to compare
     @return -1 if the first is less than the second. +1 if the first is bigger
                than the second. 0 if they are equals fitness value.
     */
    @Override
    public int compare(Chromosome chromosome1, Chromosome chromosome2) {
        if (chromosome1.getFitness() < chromosome2.getFitness()) {
            return -1;
        } else if (chromosome1.getFitness() > chromosome2.getFitness()) {
            return +1;
        } else {
            return 0;
        }
    }
}
