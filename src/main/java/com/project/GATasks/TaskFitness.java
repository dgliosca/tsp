package com.project.GATasks;


import com.project.GA.Chromosome;
import com.project.TSP.City;
import com.project.TSP.TSPGene;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class TaskFitness implements Callable<Chromosome[]> {
    Chromosome[] chromosomes;
    HashMap<Integer, City> citiesList;

    public TaskFitness(Chromosome[] chromosomes, HashMap<Integer, City> citiesList) {
        this.chromosomes = chromosomes;
        this.citiesList = citiesList;
    }

    @Override
    public Chromosome[] call() {
        for (int i = 0; i < chromosomes.length; i++) {
            double dist = 0;
            for (int k = 0; k < chromosomes[i].getNumberOfGenes() - 1; k++) {
                TSPGene city1 = (TSPGene) chromosomes[i].getGene(k);
                TSPGene city2 = (TSPGene) chromosomes[i].getGene(k + 1);
                int indCity1 = city1.getValue();
                int indCity2 = city2.getValue();
                dist +=  City.getDistance(citiesList.get(indCity1), citiesList.get(indCity2));
            }
            TSPGene cityFirst = (TSPGene) chromosomes[i].getGene(0);
            TSPGene cityLast = (TSPGene) chromosomes[i].getGene(chromosomes[i].getNumberOfGenes() - 1);
            dist += City.getDistance(citiesList.get(cityFirst.getValue()), citiesList.get(cityLast.getValue()));
            chromosomes[i].setFitness(dist);
        }

        return chromosomes;
    }
}
