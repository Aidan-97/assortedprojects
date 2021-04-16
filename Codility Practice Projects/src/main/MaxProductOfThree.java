package main;

public class MaxProductOfThree {

	public static void main(String[] args){
		int[] test1 = {-3, 1, 2, -2, 5, 6};
		int[] test2 = {1, 2, 3, -4, -5, -6};
		int[] test3 = {10, 11, 12, 13, 14, 15};
		int[] test4 = {-1, 0, 0, -1, -1, 0, 1};
		
		System.out.println("Max product of three elements of test1 is: " + maxProduct(test1));
		System.out.println("Max product of three elements of test2 is: " + maxProduct(test2));
		System.out.println("Max product of three elements of test3 is: " + maxProduct(test3));
		System.out.println("Max product of three elements of test4 is: " + maxProduct(test4));
	}
	
	public static int maxProduct(int[] A){
		int maxProduct = A[0]*A[1]*A[2];
		
		for (int i=0; i < A.length; i++){
			for (int j=i+1; j < A.length; j++){
				for (int k=j+1; k < A.length; k++){
					if (A[i]*A[j]*A[k] > maxProduct){
						maxProduct = A[i]*A[j]*A[k];
					}
				}
			}
		}
		
		return maxProduct;
	}
	
}
