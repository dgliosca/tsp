package com.project.TSP;


import com.project.GA.*;
import com.project.GAOperators.Crossover;
import com.project.GAOperators.Fitness;
import com.project.GAOperators.Mutation;
import com.project.GAOperators.Selection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

public class TSPPopulation extends Population {

    private int size;
    private int numbOfGenes;
    private static HashMap<Integer, City> citiesList = new HashMap<Integer, City>();

    /**
        Class constructor specifying size of the population
        @param size the number of individuals in the population
     */
    public TSPPopulation(int size, Properties props) {
        setSelectionBehaviour(new Selection());
        setCrossoverBehaviour(new Crossover());
        setMutationBehaviour(new Mutation());
        setFitnessEvaluator(new Fitness(citiesList));
        setChromosomes(new Chromosome[size]);
        this.size = size;
        init(props);
        initializePop();
    }

    /**
        This method elaborates data to create the cities list and to define
        the number of genes for each chromosome
        @param props data to create cities and number of genes for each chromosome
     */
    private void init(Properties props) {

        numbOfGenes = Integer.valueOf(props.getProperty("cities.number")).intValue();
        for (int i = 0; i < numbOfGenes; i++) {
            String name = props.getProperty("city" + i + ".name");
            int number = i;
            double coordX = Double.valueOf(props.getProperty("city"+ i + ".coordX"));
            double coordY = Double.valueOf(props.getProperty("city"+ i + ".coordY"));
            City city = new City(name, number, coordX, coordY);
            citiesList.put(i, city);
        }
    }

    /**
        This method initialize a new population with random value of the genes
     */
    private void initializePop() {
        for (int i = 0; i < size; i++) {
            setChromosome(new Chromosome(numbOfGenes), i);
            int[] valueGene = GeneticAlgUtils.getRndSeqInt(0, numbOfGenes - 1);
            TSPGene[] tspGene = new TSPGene[numbOfGenes];
            for (int k = 0; k < numbOfGenes; k++) {
                tspGene[k] = new TSPGene(valueGene[k]);
            }
            getChromosome(i).setGenes(tspGene, 0, tspGene.length);
        }
    }


    /**
        This method find a defined number of chromosome with the higher fitness
        value
        @param elite the number of chromosome we want to have with higher fitness
        @return a list of chromosome with the higher fitness value
     */
    public Chromosome[] getMaxFitValue(int elite) {
        Chromosome[] best = new Chromosome[elite];
        Arrays.sort(getChromosomes(), new ComparatorFitness());
        for (int i = 0; i < elite; i++) {
            best[i] = getChromosome(i).copy();
        }
        return best;
    }

    /**
       This method find a defined number of chromosome with the lower fitness
       value
       @param elite the number of chromosome we want to have with lower fitness
       @return a list of chromosome with the lower fitness value
    */
    public Chromosome[] getMinFitValue(int elite) {
        Chromosome[] best = new Chromosome[elite];
        Arrays.sort(getChromosomes(), new ComparatorFitness());
        int z = 0;
        int length = getChromosomes().length;
        for (int i = length - 1; i > length - 1 - elite; i--) {
            best[z] = getChromosome(i).copy();
            z++;
        }
        return best;
    }

    /**
        This method return the best solution for the problem
        @return the best chromosome
     */
    @Override
    public Chromosome bestSolution() {
        Chromosome cMin = this.getChromosome(0);
        for (int i = 0; i < numbOfGenes; i++) {
            if (this.getChromosome(i).getFitness() < cMin.getFitness())  {
                cMin = this.getChromosome(i);
            }
        }
        return cMin;
    }

    /**
        This method prints the value of the gene of each chromosomes of the population
        with the fitness value
     */
    @Override
    public void printPopulation() {
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < numbOfGenes; k++) {
                TSPGene g = (TSPGene)getChromosome(i).getGene(k);
                System.out.print(g.getValue() + " ");
            }
            System.out.println("Fitness: " + getChromosome(i).getFitness());
            System.out.println();
        }
    }

    /** This method returns a city with a defined ID
        @param value the number of the city that we want
        @return the city
     */
    public City getCity(int value) {
        return citiesList.get(value);
    }

    /**
        @return the list of the cities
     */
    public static HashMap<Integer, City> getCitiesList() {
        return citiesList;
    }

    /**
        This method shutdowns all the Executor service
     */
    public void closeExecutor() {
        ((Crossover) getCrossoverBehaviour()).closeExecutor();
        ((Mutation) getMutationBehaviour()).closeExecutor();
        ((Selection) getSelectionBehaviour()).closeExecutor();
        ((Fitness) getFitnessEvaluator()).closeExecutor();
    }
}
