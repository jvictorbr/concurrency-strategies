package com.jv.test.concurrency.strategies.singlethread;

import com.jv.test.concurrency.business.LongAndComplexBusinessLogic;
import com.jv.test.concurrency.strategies.AbstractExecutionStrategy;


public class SingleThreadStrategy extends AbstractExecutionStrategy {
	
	@Override
	public void execute(int[] input, LongAndComplexBusinessLogic logic) {		
		
		System.out.println(logic.execute(input));
		
	}

}
