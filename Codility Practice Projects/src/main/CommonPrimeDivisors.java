package main;

import java.util.*;

public class CommonPrimeDivisors {
	
	public static void main(String[] args){
		int[] testA1 = {15, 10, 3, 75};
		int[] testA2 = {75, 30, 5, 15};
		
		System.out.println("No. of array positions with CPDs between testA1 and testA2 is: " + commonPrimeDivisors(testA1, testA2));
	}
	
	public static int commonPrimeDivisors(int[] A, int[] B){
		int cpd = 0;
		
		for (int i=0; i < A.length; i++){
			if (primeDivisors(A[i]).size() == primeDivisors(B[i]).size()){
				for (int j=0; j < primeDivisors(A[i]).size(); j++){
					if (primeDivisors(A[i]).get(j) == primeDivisors(B[j]).get(j)){
						cpd++;
					}
				}
			}
		}
		
		return cpd;
	}
	
	public static List<Integer> primeDivisors(int k){
		List<Integer> pd = new ArrayList<>();
		
		for (int i=2; i<k; i++){
			if (k%i == 0 && isPrime(i)){
				pd.add(i);
			}
		}
		
		return pd;
	}
	
	public static boolean isPrime(int k){
		for (int i=2; i<k; i++){
			if (k%i == 0)
				return false;
		}
		
		return true;
	}

}