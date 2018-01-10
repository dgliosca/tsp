package com.project.GA.Interfaces;

import com.project.GA.Chromosome;

public interface FitnessEvaluator {

    public Chromosome[] evaluateFitness(Chromosome[] chromosomes) throws Exception;
}
