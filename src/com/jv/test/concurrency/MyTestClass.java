package com.jv.test.concurrency;

import com.jv.test.concurrency.business.LongAndComplexBusinessLogic;
import com.jv.test.concurrency.strategies.AbstractExecutionStrategy;
import com.jv.test.concurrency.strategies.multithread.ExecutorsStrategy;
import com.jv.test.concurrency.strategies.multithread.ForkJoinStrategy;
import com.jv.test.concurrency.strategies.multithread.MultiThreadStrategy;
import com.jv.test.concurrency.strategies.singlethread.SingleThreadStrategy;

public class MyTestClass {
	
	public static void main(String[] args) { 
		
		int[] input = new BigArrayGenerator().generate(500_000);
		
		AbstractExecutionStrategy executionStrategy = new SingleThreadStrategy();
		executionStrategy.executeWithTimings(input, new LongAndComplexBusinessLogic());	
		
		executionStrategy = new MultiThreadStrategy();
		executionStrategy.executeWithTimings(input, new LongAndComplexBusinessLogic());
		
		executionStrategy = new ExecutorsStrategy();
		executionStrategy.executeWithTimings(input, new LongAndComplexBusinessLogic());
		
		executionStrategy = new ForkJoinStrategy();
		executionStrategy.executeWithTimings(input, new LongAndComplexBusinessLogic());
		
	}
	

}
