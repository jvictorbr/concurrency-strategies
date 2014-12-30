package com.jv.test.concurrency.strategies.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveTask;

import com.jv.test.concurrency.business.LongAndComplexBusinessLogic;

public class WorkerThread extends RecursiveTask<Long> implements Runnable, Callable<Long> {

	private static final long serialVersionUID = -7324734302802917851L;
	
	private final LongAndComplexBusinessLogic logic;
	private final int[] input;
	private long sum = 0;
	private boolean finished = false;
	
	public WorkerThread(int[] input, LongAndComplexBusinessLogic logic) { 
		this.logic = logic;
		this.input = input;
	}

	@Override
	public void run() {
		
		final long init = System.currentTimeMillis();

		sum = logic.execute(input);
		finished = true;
		
		final long end = System.currentTimeMillis();
		
		System.out.println(String.format("Worker Thread hash: %s took %s ms to process", this.hashCode(), (end-init)));
		
	}

	public long getSum() {
		return sum;
	}

	public boolean isFinished() {
		return finished;
	}

	@Override
	public Long call() throws Exception {
		
		run();
		
		return sum;
		
	}

	@Override
	protected Long compute() {
		
		run();
		
		return sum;
	}

}
