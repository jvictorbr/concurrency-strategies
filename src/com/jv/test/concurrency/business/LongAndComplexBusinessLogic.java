package com.jv.test.concurrency.business;




public class LongAndComplexBusinessLogic {
	
	public long execute(int[] array) {
		
		long sum = 0L;
		
		for (int elem : array) {
			sum += elem;
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
						
		}


		return sum;
	}

}
