package main;

import java.util.*;

public class SmallestAbsentPositiveInteger {
	
	public static void main(String[] args){
		int[] test1 = {-1};
		int[] test2 = {1, 2, 3};
		int[] test3 = {1, 2, 1, 4, 6, 2, 3};
		
		System.out.println(findSmallestPosInt(test1));
		System.out.println();
		System.out.println(findSmallestPosInt(test2));
		System.out.println();
		System.out.println(findSmallestPosInt(test3));
	} 
	
	public static int findSmallestPosInt(int[] A){
		
        HashSet<Integer> hashSet = new HashSet<Integer>();
        int smallInt = 1;

        for (int i = 0 ; i < A.length; i++) {
            hashSet.add(A[i]);                     
        }

        while (hashSet.contains(smallInt)) {
             smallInt++;
        }

        return smallInt;
		
	}
}
