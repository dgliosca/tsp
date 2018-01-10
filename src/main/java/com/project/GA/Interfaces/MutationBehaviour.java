package com.project.GA.Interfaces;

import com.project.GA.Chromosome;

public interface MutationBehaviour {

    Chromosome[] mutate(Chromosome[] chromosomes) throws Exception;

}
