package main;

import java.util.*;

public class BinaryGap {
	
	public static void main(String[] args){
		int test1 = 9;
		int test2 = 15;
		int test3 = 20;
		int test4 = 32;
		int test5 = 1041;
		
		System.out.println("Longest binary gap of " + test1 + " is: " + findLongestBinaryGap(test1));
		System.out.println("Longest binary gap of " + test2 + " is: " + findLongestBinaryGap(test2));
		System.out.println("Longest binary gap of " + test3 + " is: " + findLongestBinaryGap(test3));
		System.out.println("Longest binary gap of " + test4 + " is: " + findLongestBinaryGap(test4));
		System.out.println("Longest binary gap of " + test5 + " is: " + findLongestBinaryGap(test5));
	}
	
	public static int findLongestBinaryGap(int i){
		
		int longestBinaryGap = 0;
		String binaryString = Integer.toBinaryString(i);
		String[] binaryValuesArray = binaryString.split("");
		List<String> binaryValuesList = Arrays.asList(binaryValuesArray);
		
		int count = 0;
		for (int j=0; j < binaryValuesList.size(); j++){
			
				if (binaryValuesList.get(j).equals("0")){
					count++;
				}

				if (binaryValuesList.get(j).equals("1")){
					if (count > longestBinaryGap){
						longestBinaryGap = count;
					}
					count = 0;
				}
			
		}
		
		return longestBinaryGap;
	}

}