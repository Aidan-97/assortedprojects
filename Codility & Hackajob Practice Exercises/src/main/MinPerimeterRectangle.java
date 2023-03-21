package main;

import java.util.*;

public class MinPerimeterRectangle {
	
	public static void main(String[] args){
		int test1 = 30;
		int test2 = 100;
		
		System.out.println("Minimum perimeter of rectangle with area " + test1 + " is: " + minimumPerimeter(test1));
		System.out.println("Minimum perimeter of rectangle with area " + test2 + " is: " + minimumPerimeter(test2));
	}
	
	public static int minimumPerimeter(int N){
		int minPer = 0;
		int length = 0;
		int breadth = 0;
		List<Integer> measurements = new ArrayList<>();
		
		for (int i=1; i < N; i++){
			if (N % i == 0){
				length = i;
				breadth = N/i;
				measurements.add(length);
				measurements.add(breadth);
			}
		}		
		minPer =2*measurements.get(0) + 2*measurements.get(1);
		
		for (int j=0; j < measurements.size(); j+=2){
			if (2*measurements.get(j) + 2*measurements.get(j+1) < minPer){
				minPer = 2*measurements.get(j) + 2*measurements.get(j+1);
			}
		}
		
		return minPer;
	}

}
