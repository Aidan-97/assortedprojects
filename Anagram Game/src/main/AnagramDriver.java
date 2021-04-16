package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AnagramDriver {
	
	static Scanner scan = new Scanner(System.in);
	static String inputString;
	static List<String> unscrambled, scrambled;
	
	public static void main(String[] args){
		startGame();
	}
	
	public static void startGame(){
		int question = 0;
		int score = 0;
		scrambled = getGameList();
		
		System.out.println("################################\n# Welcome to the Anagram Game! #\n################################");
		System.out.println("Attempt to solve the following 10 anagrams\nReminder: The first letter is always in the correct position");
		
		while (question < scrambled.size()){
			System.out.println("Question " + (question+1) + "/10: " + scrambled.get(question));
			inputString = scan.next();
			
			if (inputString.equals(unscrambled.get(question))){
				score += 10;
				System.out.println("Correct! - Score = " + score + "/100");
				question++;
			}
			else {
				System.out.println("Incorrect! Correct answer was: " + unscrambled.get(question) + " - Score = " + score + "/100");
				question++;
			}
		}
	}
	
	public static List<String> getGameList(){
		List<String> gameList = new ArrayList<>();
		unscrambled = generateDictionary();
		Collections.shuffle(unscrambled);
		
		for (String s : unscrambled){
			s = getScrambled(s);
			gameList.add(s);
		}
		
		gameList = gameList.subList(0, 10);
		
		return gameList;
	}
	
	public static String getScrambled(String s){
		String[] scram = s.split("");
		List<String> letters = Arrays.asList(scram);
		Collections.shuffle(letters.subList(1,  letters.size()));
		StringBuilder sb = new StringBuilder(s.length());
		for (String c : letters){
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static List<String> generateDictionary(){
		List<String> dictionary = new ArrayList<>();
		
		try{
			FileReader fr = new FileReader("words");
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			Scanner scan = null;
			String word = " ";
			while (line != null){
				scan = new Scanner(line);
				word = scan.next();
				dictionary.add(word);
				line = reader.readLine();
			}
			reader.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't find file");
		}
		catch (IOException e){
			System.out.println("Problem reading file");
		}
		
		return dictionary;
	}

}
