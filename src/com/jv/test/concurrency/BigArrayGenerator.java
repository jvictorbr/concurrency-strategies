package com.jv.test.concurrency;

import java.util.Arrays;
import java.util.Random;

public class BigArrayGenerator {
	
	public int[] generate(int count) { 		
		
		int[] array = new int[count];
		for (int i = 0; i < count; i++) { 
			array[i] = generateRandom();			
		}
		
		return array;		
	}
	
	
	private int generateRandom() { 
		return Math.abs(new Random().nextInt(Integer.MAX_VALUE));
	}
	
	public int[][] splitArray(int[] array, int number) { 
			
		if (array.length % number != 0) { 
			throw new RuntimeException("Please adjust number of threads to allow equal input split.");
		}
		
		int subArraySize = array.length / number;
		int[][] arrays = new int[number][subArraySize];
						
		
		int start = 0;
		int end = array.length / number;		
		
		for (int i = 0; i < number; i++) { 
			
			arrays[i] = Arrays.copyOfRange(array, start, end);
			start = end;
			end += subArraySize;		
			
		}	
		
		return arrays;
		
	}
	
	public static void main(String[] args) { 
		
		BigArrayGenerator generator = new BigArrayGenerator();
		
		int[] array = generator.generate(100_000_000);
	
		System.out.println(array);
		System.out.println(array.length);
		
		int[][] subArrays = generator.splitArray(array, 4);
		
		for (int i = 0; i < subArrays.length; i++) { 
			
			System.out.println(subArrays[i].length);
			
		}
		
		
		
		
	}


}
