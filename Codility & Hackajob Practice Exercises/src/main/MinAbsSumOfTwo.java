package main;

import java.util.*;

public class MinAbsSumOfTwo {
	
	public static void main(String[] args){
		int[] testA = {1, 4, -3};
		int[] testB = {-8, 4, 5, -10, 3};
		
		System.out.println("SOL: " + solution(testA));
		System.out.println("========");
		System.out.println("SOL: " + solution(testB));
	}
	
	public static int solution(int[]A){
		int sol = 0;
		
		List<Integer> absSums = new ArrayList<>();
		
		for (int i=0; i < A.length; i++){
			
			if (A[i]*2 < 0){
				absSums.add(-(A[i]*2));
			} else {
				absSums.add(A[i]*2);
			}
			
			for (int j=i+1; j < A.length; j++){

				if (A[i] + A[j] < 0){
					absSums.add(-(A[i] + A[j]));
				} else {
					absSums.add(A[i] + A[j]);
				}
				
			}
		}
		
		Collections.sort(absSums);
		
		for (int k : absSums){
			System.out.println(k);
		}
		
		sol = absSums.get(0);
		
		return sol;
	}

}
