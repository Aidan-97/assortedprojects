package main;

public class RomanNumerals {
	
	public static void main(String[] args){
		System.out.println("Roman numeral for 12 is: " + convertToRomanNumerals(12));
		System.out.println("Roman numeral for 6 is: " + convertToRomanNumerals(6));
		System.out.println("Roman numeral for 909 is: " + convertToRomanNumerals(909));
		System.out.println("Roman numeral for 2001 is: " + convertToRomanNumerals(2001));
		System.out.println("Roman numeral for 3999 is: " + convertToRomanNumerals(3999));
	}
	
	public static String convertToRomanNumerals(int n){
		String res = "";
		int repeat;
		int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		
		for (int i=0; i < numbers.length; i++){
			repeat = n / numbers[i];			
			for (int j=1; j <= repeat; j++){
				res = res + symbols[i];
			}
			n = n % numbers[i];
		}
		
		return res;
	}
}
