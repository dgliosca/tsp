package com.project.GA;

import com.project.GA.Interfaces.GeneInter;

public class Chromosome {

    private int numberOfGenes;
    private double fitness = 0;
    private GeneInter[] genes;

    /**
     *  Class constructor specifying number of genes to create
     *  @param numberOfGenes number of gene in the chromosome
    */
    public Chromosome(int numberOfGenes) {
        genes = new GeneInter[numberOfGenes];
        this.numberOfGenes = numberOfGenes;
    }

    /**
     *  Returns the value of the specified gene
     *  @param position the position of the gene that we want as a result
     *  @return the gene at the specified position
     */
    public GeneInter getGene(int position) {
        return genes[position];
    }

    /**
     *  Sets a new value for the requested gene
     *  @param i the i-th gene that we want to set
     *  @param value is the new value of the gene
     */
    public void setGene(int i, GeneInter value) {
        genes[i] = value;
    }

    /**
     * Returns all the genes of the chromosome
     *  @return genes it is an array containing all the genes of the chromosome
     */
    public GeneInter[] getAllGenes() {
        return genes;
    }

    /**
     *   Returns all the genes between begin index to end index
     *   @param beginIndex it is the starting index to get the genes
     *   @param endIndex it is the ending index until to get the genes
     *   @return it is an array of genes containing all the genes between
     *           beginIndex and endIndex
     */
    public GeneInter[] getGenes(int beginIndex, int endIndex) {
        GeneInter[] gApp = new GeneInter[endIndex - beginIndex + 1];
        for (int i = beginIndex; i < endIndex; i++) {
            gApp[i] = this.genes[i].copy();
        }
        return gApp;
    }

    /**
     *   Sets the gene taken from the srcGenes in the specified indexes
     *   @param srcGenes it is the array containing the genes to set in the
     *           chromosome
     *   @param beginIndex it is the starting index in the chromosome's genes
     *                       to set new genes
     *   @param endIndex it is the ending index in the chromosome's genes until
     *                   to set the new genes
     */
    public void setGenes(GeneInter[] srcGenes, int beginIndex, int endIndex) {
        int k = 0;
        for (int i = beginIndex; i < endIndex; i++) {
            this.genes[i] = srcGenes[k].copy();
            k++;
        }
    }

    /** Returns the number of the genes of the chromosome
        @return how many genes there are in the chromosome
     */
    public int getNumberOfGenes() {
        return numberOfGenes;
    }

    /**
     *   Returns the fitness value of the chromosome
     *   @return chromosome fitness value
     */
    public double getFitness() {
        return fitness;
    }

    /**
     *   Sets a new fitness value
     *   @param fitness chromosome fitness value
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     *   Creates a copy of the chromosome
     *   @return a copy of the chromosome that we want to copy
     */
    public Chromosome copy() {
        Chromosome newChr = new Chromosome(this.numberOfGenes);
        for (int i = 0; i < this.numberOfGenes; i++) {
            newChr.setGenes(this.getAllGenes(), 0, this.getNumberOfGenes());
        }
        newChr.fitness = this.fitness;
        return newChr;
    }
}
