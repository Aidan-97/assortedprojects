package main;

public class FloodDepth {
	
	public static void main(String[] args){
		int[] test1 = {1, 3, 2, 1, 2, 1, 5, 3, 3, 4, 2};
		int[] test2 = {5, 8};
		
		System.out.println("Max flood depth possible from test1 is: " + maxDepth(test1));
		System.out.println("Max flood depth possible from test2 is: " + maxDepth(test2));
	}
	
	public static int maxDepth(int[] A){
		int maxDepth = 0;
		int currentDepth = 0;
		int maxHeight = 0;
		int minHeight = 0;
		
		for (int block : A){
			if (block > maxHeight){
				currentDepth = maxHeight - minHeight;
				maxHeight = block;
				minHeight = block;
			}
			else if (block < minHeight){
				minHeight = block;
			}
			else{
				currentDepth = block - minHeight;
			}
			
			if (currentDepth > maxDepth){
				maxDepth = currentDepth;
			}
		}
		
		return maxDepth;
	}

}
