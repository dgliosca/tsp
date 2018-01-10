package com.project.TSP;


import com.project.GA.Interfaces.GeneInter;

public class TSPGene implements GeneInter {

    private int value = 0;

    /**
        Class constructor that takes an integer number as value of the gene
        @param value the value of the gene
     */
    public TSPGene(int value) {
        this.value = value;
    }

    /**
        @return the value of the gene
     */
    public int getValue() {
        return value;
    }

    /**
        This method create a copy of the gene
        @return a copy of the gene
     */
    public GeneInter copy() {
        return new TSPGene(this.value);
    }

    @Override
    public boolean equals(GeneInter o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSPGene tspGene = (TSPGene) o;

        if (value != tspGene.value) return false;

        return true;
    }

}
