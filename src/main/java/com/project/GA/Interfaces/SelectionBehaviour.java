package com.project.GA.Interfaces;

import com.project.GA.Chromosome;

public interface SelectionBehaviour {

    public Chromosome[] selection(Chromosome[] chromosomes) throws Exception;

}
