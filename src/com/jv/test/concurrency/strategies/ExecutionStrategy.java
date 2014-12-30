package com.jv.test.concurrency.strategies;

import com.jv.test.concurrency.business.LongAndComplexBusinessLogic;

public interface ExecutionStrategy {
	
	void execute(int[] input, LongAndComplexBusinessLogic logic);

}
