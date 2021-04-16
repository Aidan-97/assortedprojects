package main;

import java.util.*;
import java.io.*;

public class Driver {
	
	static Scanner scan = new Scanner(System.in);
	static String inputString;
	static HashMap<String, String> gameMap = new HashMap<>();
	static List<String> newTerms = new ArrayList<>();
	static List<String> newCategories = new ArrayList<>();
	static String fragmentedTerm;
	
	public static void main(String[] args) {
		createGameLibrary();
		int lives = 6;
		List<String> lettersAttempted = new ArrayList<>();

		Random random = new Random();
		int r = random.nextInt(newTerms.size());
		String attemptedTerm = newTerms.get(r);
		fragmentedTerm = getFragmentedTerm(attemptedTerm);
	
		System.out.println("HANGMAN\n");
		System.out.println("Enter letters to make guesses");
		System.out.println("Your category is " + gameMap.get(attemptedTerm) + "\n");
		printIllustration(lives);
		System.out.println();
		System.out.println(fragmentedTerm);	
		
		while (lives > 0) {
			inputString = scan.next();
			lettersAttempted.add(inputString);
			
			int count = 0;
			for (String s : lettersAttempted){
				if (s.equals(inputString))
					count++;
			}
			
			if (count > 1){
				System.out.println("You have already guessed this letter");
			}
			
			else if (inputString.length() > 1){
				System.out.println("Invalid entry, enter just one letter at a time");
			}
			
			else if (attemptedTerm.toLowerCase().contains(inputString.subSequence(0, 1))){
				String[] fragArray = fragmentedTerm.split("");
				String[] termArray = attemptedTerm.toLowerCase().split("");
				
				List<String> fragList = Arrays.asList(fragArray);
				List<String> termList = Arrays.asList(termArray);
				
				StringBuilder sb = new StringBuilder(fragList.size());
				for (int i=0; i < fragList.size(); i++){
					String replaced = fragList.get(i);
					if (!(replaced.equals(inputString)) && termList.get(i).equals(inputString)){
						replaced = inputString.toUpperCase();
					}
					sb.append(replaced);
					fragmentedTerm = sb.toString();
				}
				System.out.println("\n" + fragmentedTerm +"\n");
				
				if (fragmentedTerm.equals(attemptedTerm)){
					System.out.println("Congratulations! Game completed");
					System.exit(0);
				}
			}
			
			else if (lives > 1) {
				lives--;
				System.out.println("Incorrect! Try another letter\n");
				printIllustration(lives);
				System.out.println("\n" + fragmentedTerm);
			}
			
			else{
				System.out.println("Incorrect! Game over! Correct answer was " + attemptedTerm + "\n");
				printIllustration(0);
				System.exit(0);
			}
		}
	}
	
	public static String getFragmentedTerm(String s){
		String[] charArray = s.split("");
		
        StringBuilder sb = new StringBuilder(s.length());
        for (String c : charArray) {
			if ( (!(c.equals("-"))) && (!(c.equals(" "))) )
				c = "-";
            sb.append(c);
        }
        return sb.toString();
	}
	
	public static void printIllustration(int i){
		if (i==6){
			System.out.println("----------|\n|         |\n|\n|\n|\n|\n|---------");
		}
		else if (i==5){
			System.out.println("----------|\n|         |\n|         O\n|\n|\n|\n|---------");
		}
		else if (i==4){
			System.out.println("----------|\n|         |\n|         O\n|         |\n|\n|\n|---------");
		}
		else if (i==3){
			System.out.println("----------|\n|         |\n|         O\n|        \\|\n|\n|\n|---------");
		}
		else if (i==2){
			System.out.println("----------|\n|         |\n|         O\n|        \\|/\n|\n|\n|---------");
		}
		else if (i==1){
			System.out.println("----------|\n|         |\n|         O\n|        \\|/\n|        /\n|\n|---------");
		}
		else if (i==0){
			System.out.println("----------|\n|         |\n|         O\n|        \\|/\n|        / \\\n|\n|---------");
		}
	}
	
	public static void createGameLibrary(){
		List<String> terms = new ArrayList<>();
		List<String> categories = new ArrayList<>();
		
		try{
			FileReader fr = new FileReader("games");
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			Scanner scan = null;
			String term = "";
			String category = "";
			while (line != null){
				scan = new Scanner(line);
				term = scan.next();
				category = scan.next();
				terms.add(term);
				categories.add(category);
				line = reader.readLine();
			}
			reader.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find the file");
		}
		catch (IOException e){
			System.out.println("Problem reading file");
		}
		
		for (String s : terms){
			newTerms.add(s.replace('_', ' '));
		}
		
		for (String s : categories){
			newCategories.add(s.replace('_', ' '));
		}
		
		for (int i=0; i < newTerms.size(); i++){
			gameMap.put(newTerms.get(i), newCategories.get(i));
		}
	}

}