package main;

import java.util.*;

public class SecondHighestInArray {
	
	public static void main(String[] args){
		int[] testA = {2, 6, 7, 2, 9, 3};
		System.out.println("RESULT A: " + secondHighest(testA));
		
		int [] testB = {-1, 0, 1, -1, -2, 0, -3};
		System.out.println("RESULT B: " + secondHighest(testB));
		
		int[] testC = {1, 2, 2, 2, 2, 2};
		System.out.println("RESULT C: " + secondHighest(testC));
	}
	
	public static int secondHighest(int[] A){
		int second = 0;
		
		HashSet<Integer> hashSet = new HashSet<Integer>();
		List<Integer> list = new ArrayList<>();
		
		for (int i : A){
			hashSet.add(i);
		}
		
		for (int j : hashSet){
			list.add(j);
		}
		
		Collections.sort(list);
		
		second = list.get(list.size()-2);
		
		return second;
	}

}
