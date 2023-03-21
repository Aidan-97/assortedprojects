package main;

public class TieRopes {
	
	public static void main(String[] args){
		int[] test1Array = {1, 2, 3, 4, 1, 1, 3};
		int test1Int = 4;
		
		int[] test2Array = {5, 2, 3, 4, 2, 2, 4};
		int test2Int = 5;
		
		System.out.println("Max no. of ropes of length >= " + test1Int + " possible from test1Array is: " + ropes(test1Int, test1Array));
		System.out.println("Max no. of ropes of length >= " + test2Int + " possible from test2Array is: " + ropes(test2Int, test2Array));
	}
	
	public static int ropes(int K, int[] A){
		int ropes = 0;
		int length = 0;
		
		for (int rope : A){
			length += rope;
			
			if (length >= K){
				ropes++;
				length = 0;
			}
		}
		
		return ropes;
	}

}
