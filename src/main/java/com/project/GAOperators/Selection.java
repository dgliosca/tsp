package com.project.GAOperators;

import com.project.GA.Chromosome;
import com.project.GA.GeneticAlgorithm;
import com.project.GA.Interfaces.SelectionBehaviour;
import com.project.GATasks.TaskSelection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Selection implements SelectionBehaviour {

    private final ExecutorService executor = Executors.newFixedThreadPool(GeneticAlgorithm.THREAD_SELECTION_NUM);

    /**
        @param chromosomes it is an array of chromosomes on which we want to
                            apply the selection
        @return an array of new chromosomes
     */
    public Chromosome[] selection(Chromosome[] chromosomes) throws Exception {
        Chromosome[] retChromosomes = new Chromosome[chromosomes.length];
        int sizePart = chromosomes.length / GeneticAlgorithm.THREAD_MUTATION_NUM;
        int numOfPartWithNChr = chromosomes.length / sizePart;
        int sizeLastPart = chromosomes.length % sizePart;


        int index = 0;
        List<TaskSelection> taskList = new ArrayList<TaskSelection>();

        for (int i = 0; i < numOfPartWithNChr; i++) {
            Chromosome[] part = new Chromosome[sizePart];
            System.arraycopy(chromosomes, index, part, 0, sizePart);
            TaskSelection task = new TaskSelection(GeneticAlgorithm.DEFAULT_TOURNAMENT_SIZE, part);
            taskList.add(task);
            index += sizePart;
        }

        if (sizeLastPart != 0) {
            Chromosome[] lastPart = new Chromosome[sizeLastPart];
            System.arraycopy(chromosomes, index, lastPart, 0, sizeLastPart);
            TaskSelection task = new TaskSelection(GeneticAlgorithm.DEFAULT_TOURNAMENT_SIZE, lastPart);
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