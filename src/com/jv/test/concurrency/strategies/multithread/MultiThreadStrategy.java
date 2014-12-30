package com.jv.test.concurrency.strategies.multithread;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.jv.test.concurrency.BigArrayGenerator;
import com.jv.test.concurrency.business.LongAndComplexBusinessLogic;
import com.jv.test.concurrency.strategies.AbstractExecutionStrategy;

public class MultiThreadStrategy extends AbstractExecutionStrategy {

	@Override
	public void execute(int[] input, LongAndComplexBusinessLogic logic) {

		
		final BigArrayGenerator generator = new BigArrayGenerator();
		final int coreCount = Runtime.getRuntime().availableProcessors();		
		final int[][] arrays = generator.splitArray(input, coreCount);
		
		long sum = 0;
		
		System.out.println(String.format("Core count: %s Number of sub arrays: %s Per sub array: %s", coreCount, arrays.length, arrays[0].length));
		
		Set<WorkerThread> workers = new HashSet<WorkerThread>();
		
		for (int i = 0; i < arrays.length; i++) { 
			
			WorkerThread worker = new WorkerThread(arrays[i], new LongAndComplexBusinessLogic());
			workers.add(worker);
			Thread thread = new Thread(worker);
			thread.start();	
			
		}
		
		while (!workers.isEmpty()) { 
			for (Iterator<WorkerThread> workersIt = workers.iterator(); workersIt.hasNext(); ) {
				WorkerThread worker = workersIt.next();
				if (worker.isFinished()) {				
					sum += worker.getSum();
					workersIt.remove();
				}
			}
		}
		
		System.out.println(sum);
		
	}
	

}
