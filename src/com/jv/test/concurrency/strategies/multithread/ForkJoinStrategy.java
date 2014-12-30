package com.jv.test.concurrency.strategies.multithread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import com.jv.test.concurrency.BigArrayGenerator;
import com.jv.test.concurrency.business.LongAndComplexBusinessLogic;
import com.jv.test.concurrency.strategies.AbstractExecutionStrategy;

public class ForkJoinStrategy extends AbstractExecutionStrategy {

	@Override
	public void execute(int[] input, LongAndComplexBusinessLogic logic) {
		
		
		final BigArrayGenerator generator = new BigArrayGenerator();
		final int coreCount = Runtime.getRuntime().availableProcessors();		
		final int[][] arrays = generator.splitArray(input, coreCount);
		
		final Set<RecursiveTask<Long>> tasks = new HashSet<RecursiveTask<Long>>();
		
		long sum = 0;
		
		ForkJoinPool fjp = new ForkJoinPool();
		
		RecursiveTask<Long> task = new RecursiveTask<Long>() {
			private static final long serialVersionUID = -3132295869653987223L;

			@Override
			protected Long compute() {

				for (int i = 0; i < arrays.length; i++) {
					
					final int[] subArray = arrays[i];
					RecursiveTask<Long> taskToFork = new WorkerThread(subArray, new LongAndComplexBusinessLogic());
					taskToFork.fork();
					tasks.add(taskToFork);
				}
				
				long sum = 0;
				for (RecursiveTask<Long> task : tasks) { 
					sum += task.join();
				}
				
				return sum;
			}
		};
		
		sum  = fjp.invoke(task);
		
		System.out.println(sum);
		
	}

}
