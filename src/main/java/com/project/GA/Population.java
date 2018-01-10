package com.project.GA;


import com.project.GA.Interfaces.CrossoverBehaviour;
import com.project.GA.Interfaces.FitnessEvaluator;
import com.project.GA.Interfaces.MutationBehaviour;
import com.project.GA.Interfaces.SelectionBehaviour;

public abstract class Population {

    private SelectionBehaviour selectionBehaviour;
    private CrossoverBehaviour crossoverBehaviour;
    private MutationBehaviour mutationBehaviour;
    private FitnessEvaluator fitnessEvaluator;

    private Chromosome[] chromosomes;

    public Chromosome[] performSelection() throws Exception {
        return selectionBehaviour.selection(chromosomes);
    }

    public Chromosome[] performCrossover() throws Exception {
        return crossoverBehaviour.crossover(chromosomes);
    }

    public Chromosome[] performMutation() throws Exception {
        return mutationBehaviour.mutate(chromosomes);
    }

    public Chromosome[] performFitnessEvaluation() throws Exception {
        return fitnessEvaluator.evaluateFitness(chromosomes);
    }

    public void setChromosomes(Chromosome[] chr) {
        this.chromosomes = chr;
    }

    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public void setChromosome(Chromosome c, int position) {
        this.chromosomes[position] = c;
    }

    public Chromosome getChromosome(int position) {
        return this.chromosomes[position];
    }

    public SelectionBehaviour getSelectionBehaviour() {
        return selectionBehaviour;
    }

    public void setSelectionBehaviour(SelectionBehaviour selectionBehaviour) {
        this.selectionBehaviour = selectionBehaviour;
    }

    public CrossoverBehaviour getCrossoverBehaviour() {
        return crossoverBehaviour;
    }

    public void setCrossoverBehaviour(CrossoverBehaviour crossoverBehaviour) {
        this.crossoverBehaviour = crossoverBehaviour;
    }

    public MutationBehaviour getMutationBehaviour() {
        return mutationBehaviour;
    }

    public void setMutationBehaviour(MutationBehaviour mutationBehaviour) {
        this.mutationBehaviour = mutationBehaviour;
    }

    public FitnessEvaluator getFitnessEvaluator() {
        return fitnessEvaluator;
    }

    public void setFitnessEvaluator(FitnessEvaluator fitnessEvaluator) {
        this.fitnessEvaluator = fitnessEvaluator;
    }

    public abstract Chromosome[] getMaxFitValue(int num);

    public abstract Chromosome[] getMinFitValue(int num);

    public abstract Chromosome bestSolution();

    public abstract void printPopulation();
}
