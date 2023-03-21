package main;

import java.util.*;

public class OddOccurenceInArray {
	
	public static void main(String[] args){
		int[] test1 = {1, 2, 3, 4, 3, 2, 1};
		int[] test2 = {22, 21, 22, 35, 21, 56, 56};
		int[] test3 = {0, 0, 250, 250, 500, 500, 750, 750, 1000};
		
		System.out.println("Odd ocurrence in test1 is: " + findOddOcurrence(test1));
		System.out.println("Odd ocurrence in test2 is: " + findOddOcurrence(test2));
		System.out.println("Odd ocurrence in test3 is: " + findOddOcurrence(test3));
	}
	
	public static int findOddOcurrence(int[] inputArray){
		int oddOcurrence = 0;
		List<Integer> hasDuplicates = new ArrayList<>();
		
		for (int i=0; i < inputArray.length; i++){
			for (int j=i+1; j < inputArray.length; j++){
				if (i != j && inputArray[i] == inputArray[j]){
					hasDuplicates.add(inputArray[i]);
				}
			}
			if (!hasDuplicates.contains(inputArray[i])){
				oddOcurrence = inputArray[i];
			}
		}
		
		return oddOcurrence;
	}

}
