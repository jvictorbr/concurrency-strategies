package com.jv.test.concurrency.strategies;

import com.jv.test.concurrency.business.LongAndComplexBusinessLogic;

public abstract class AbstractExecutionStrategy implements ExecutionStrategy {
	
	public void executeWithTimings(int[] input, LongAndComplexBusinessLogic logic) {
		
		System.out.println("Starting strategy: " + this.getClass().getName());

		final long start = System.currentTimeMillis();
		
		execute(input, logic);
		
		final long end = System.currentTimeMillis();
		
		System.out.println(String.format("Spent %s ms", (end-start)));
		
	}
	
	

}
