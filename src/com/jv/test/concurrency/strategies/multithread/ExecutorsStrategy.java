package com.jv.test.concurrency.strategies.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.jv.test.concurrency.BigArrayGenerator;
import com.jv.test.concurrency.business.LongAndComplexBusinessLogic;
import com.jv.test.concurrency.strategies.AbstractExecutionStrategy;

public class ExecutorsStrategy extends AbstractExecutionStrategy {

	@Override
	public void execute(int[] input, LongAndComplexBusinessLogic logic) {

		final int coreCount = Runtime.getRuntime().availableProcessors();
		final BigArrayGenerator generator = new BigArrayGenerator();
		
		final int[][] arrays = generator.splitArray(input, coreCount);
		
		ExecutorService executorService = Executors.newFixedThreadPool(coreCount);		
		List<Future<Long>> futures = new ArrayList<Future<Long>>();
		
		long sum = 0;
				
		for (int i = 0; i < arrays.length; i++) { 
			
			int[] array = arrays[i];
			
			Callable<Long> worker = new WorkerThread(array, new LongAndComplexBusinessLogic());
			Future<Long> future = executorService.submit(worker);
			futures.add(future);
											
		}
		
		for (Future<Long> future : futures) { 
			
			try {
				sum += future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		}
		
		System.out.println(sum);
		
		executorService.shutdown();
		
	}

}
