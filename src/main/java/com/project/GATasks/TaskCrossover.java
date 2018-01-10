package com.project.GATasks;


import com.project.GA.Chromosome;
import com.project.GA.Interfaces.GeneInter;
import com.project.GA.GeneticAlgUtils;
import com.project.GA.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TaskCrossover implements Callable<Chromosome[]> {
    Chromosome[] chromosomes;

    public TaskCrossover(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes;
    }

    public Chromosome[] call() throws Exception {
        Chromosome[] retChr = new Chromosome[chromosomes.length];
        ArrayList<Chromosome[]> arrChr = new ArrayList<Chromosome[]>();
        for (int i = 0; i < chromosomes.length; i += 2) {
            arrChr.add(crossover(chromosomes[i], chromosomes[i + 1]));
        }
        int z = 0;
        for (int i = 0; i < arrChr.size(); i++) {
            Chromosome[] c = arrChr.get(i);
            for (int k = 0; k < c.length; k++) {
                retChr[z] = c[k];
                z++;
            }
        }
        return retChr;
    }
    /*
        This method implements the crossover behaviour
        @param mom it is a chromosome
        @param dad it is a chromosome
        @return it is an array of size 2 with the result of the crossover
     */
    public Chromosome[] crossover(Chromosome mom, Chromosome dad) throws Exception {
        Chromosome[] retChromosomes = new Chromosome[2];
        double rnd = GeneticAlgUtils.randomDouble(0, 1);
        if (rnd < GeneticAlgorithm.DEFAULT_CROSSOVER_RATE) {

            int crossoverPoint = GeneticAlgUtils.getRandomInt(1, mom.getNumberOfGenes() - 1);

            Chromosome child1 = new Chromosome(mom.getNumberOfGenes());
            Chromosome child2 = new Chromosome(mom.getNumberOfGenes());

            child1.setGenes(mom.getGenes(0, crossoverPoint), 0, crossoverPoint);
            child2.setGenes(dad.getGenes(0, crossoverPoint), 0, crossoverPoint);

            GeneInter[] dadList = getEndPart(dad.getAllGenes(), child1.getGenes(0, crossoverPoint));
            GeneInter[] momList = getEndPart(mom.getAllGenes(), child2.getGenes(0, crossoverPoint));

            child1.setGenes(dadList, crossoverPoint , child1.getNumberOfGenes());
            child2.setGenes(momList, crossoverPoint , child2.getNumberOfGenes());

            retChromosomes[0] = child1;
            retChromosomes[1] = child2;
        } else {
            retChromosomes[0] = mom.copy();
            retChromosomes[1] = dad.copy();
        }
        return retChromosomes;
    }

    /*
        This method is useful to avoid to duplicate genes in the children
    */
    private GeneInter[] getEndPart(GeneInter[] parent, GeneInter[] child) {
        ArrayList<GeneInter> temp = new ArrayList<GeneInter>();
        for (GeneInter g : parent) {
            boolean in = false;
            for (GeneInter gChild : child) {
                if (g.equals(gChild)) {
                    in = true;
                    break;
                }
            }
            if (!in)
                temp.add(g);
        }
        GeneInter[] endPart = new GeneInter[temp.size()];
        for (int z = 0; z < temp.size(); z++) {
            endPart[z] = temp.get(z);
        }
        return endPart;
    }
}
