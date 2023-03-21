package main;

import java.util.*;

public class CyclicRotation {
	
	public static void main(String[] args){
		int[] test1 = {3, 8, 9, 7, 6};
		int[] test2 = {0, 0, 0};
		int[] test3 = {1, 2, 3, 4};
		int[] test4 = {10, 20, 30, 40, 50, 60, 70};
		int[] test5 = {6, 7, 8, 9};
		
		System.out.println("Rotated array is: " + Arrays.toString(rotatedArray(test1, 3)));
		System.out.println("Rotated array is: " + Arrays.toString(rotatedArray(test2, 1)));
		System.out.println("Rotated array is: " + Arrays.toString(rotatedArray(test3, 4)));
		System.out.println("Rotated array is: " + Arrays.toString(rotatedArray(test4, 4)));
		System.out.println("Rotated array is: " + Arrays.toString(rotatedArray(test5, 11)));
	}
	
	public static int[] rotatedArray(int[] A, int K){
		int[] rotArr = new int[A.length];
		
		if (K > A.length){
			K = K % A.length;
		}
		
		for (int i=0; i < A.length; i++){
			if ((i + K) < A.length){
				rotArr[i+K] = A[i];
			}
			else if ((i + K) >= A.length){
				rotArr[(i+K) - A.length] = A[i];
			}
		}
		
		return rotArr;
	}

}