package com.project.GAOperators;


import com.project.GA.Chromosome;
import com.project.GA.Interfaces.CrossoverBehaviour;
import com.project.GA.GeneticAlgorithm;
import com.project.GATasks.TaskCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Crossover implements CrossoverBehaviour {

    private final ExecutorService executor = Executors.newFixedThreadPool(GeneticAlgorithm.THREAD_CROSSOVER_NUM);

    /**
        @param chromosomes it is an array of chromosomes on which we want to
                            crossover
        @return an array of new chromosomes
     */
    public Chromosome[] crossover(Chromosome[] chromosomes) throws Exception {

        Chromosome[] retChromosomes = new Chromosome[chromosomes.length];
        // Number of chromosomes elaborated by each thread. If this number is
        // odd, we have to add 1 to k.
        int sizePart = chromosomes.length / GeneticAlgorithm.THREAD_CROSSOVER_NUM;
        if ((sizePart % 2) != 0)
            sizePart++;
        int numOfPartWithNChr = chromosomes.length / sizePart;
        int sizeLastPart = chromosomes.length % sizePart;


        int index = 0;
        List<TaskCrossover> taskList = new ArrayList<TaskCrossover>();

        for (int i = 0; i < numOfPartWithNChr; i++) {
            Chromosome[] part = new Chromosome[sizePart];
            System.arraycopy(chromosomes, index, part, 0, sizePart);
            TaskCrossover task = new TaskCrossover(part);
            taskList.add(task);
            index += sizePart;
        }

        if (sizeLastPart != 0) {
            Chromosome[] lastPart = new Chromosome[sizeLastPart];
            System.arraycopy(chromosomes, index, lastPart, 0, sizeLastPart);
            TaskCrossover task = new TaskCrossover(lastPart);
            taskList.add(task);
        }

        List<Future<Chromosome[]>> resultList = null;
        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int z = 0;
        for (int p = 0; p < resultList.size(); p++){
            Future<Chromosome[]> future = resultList.get(p);
            try {
                Chromosome[] result = future.get();
                for (int m = 0; m < result.length; m++) {
                    retChromosomes[z] = result[m];
                    z++;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return retChromosomes;
    }

    /**
        This method shutdowns the ExecutorService
     */
    public void closeExecutor() {
        executor.shutdown();
    }
}
