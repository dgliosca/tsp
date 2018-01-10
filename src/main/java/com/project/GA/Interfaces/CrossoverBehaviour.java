package com.project.GA.Interfaces;

import com.project.GA.Chromosome;

public interface CrossoverBehaviour {

    public Chromosome[] crossover(Chromosome[] chromosomes) throws Exception;

}
