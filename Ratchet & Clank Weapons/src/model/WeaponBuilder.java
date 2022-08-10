package model;

import java.util.*;
import java.io.*;

public class WeaponBuilder implements IWeaponBuilder {
	
	static Scanner scan = new Scanner(System.in);
	static List<String> weapons = new ArrayList<>();
	static List<List<String>> weaponDetails = new ArrayList<>();
	static HashMap<String, List<String>> weaponMap = new HashMap<>();
	static HashMap<String, String> pathMap = new HashMap<>();
	
	public void buildWeaponMap(){
		List<String> tempWeapons = new ArrayList<>();
		
		List<String> tempWeaponDetails = new ArrayList<>();
		
		try{
			FileReader fr = new FileReader("weapons");
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			Scanner scan = null;
			
			String weapon = " ";
			
			// List<String> details = new ArrayList<>();
			
			String console = " ";
			String game = " ";
			String planet = " ";
			String range = " ";
			String rof = " ";
			String cost = " ";
			
			while (line != null){
				scan = new Scanner(line);
				weapon = scan.next();
				console = scan.next();
				game = scan.next();
				planet = scan.next();
				range = scan.next();
				rof = scan.next();
				cost = scan.next();
				
				tempWeapons.add(weapon);
				
				tempWeaponDetails.add(console);
				tempWeaponDetails.add(game.replace('_', ' '));
				tempWeaponDetails.add(planet.replace('_', ' '));
				tempWeaponDetails.add(range.replace('_', ' '));
				tempWeaponDetails.add(rof.replace('_', ' '));
				tempWeaponDetails.add(cost);
				
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
		
		for (String s : tempWeapons){
			weapons.add(s.replace('_', ' '));
		}
		
		List<String> w = new ArrayList<>();
		
		for (int i=0; i < tempWeaponDetails.size(); i+=6){
			w = tempWeaponDetails.subList(i, i+6);
			weaponDetails.add(w);
		}
		
		for (int i=0; i < weapons.size(); i++){
			weaponMap.put(weapons.get(i), weaponDetails.get(i));
		}
		
	}
	
	public void buildPathMap(){
		try{
			FileReader fr = new FileReader("paths");
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			Scanner scan = null;
			
			String weapon = " ";
			String path = " ";
			
			while (line != null){
				scan = new Scanner(line);
				
				weapon = scan.next();
				path = scan.next();

				pathMap.put(weapon.replace('_', ' '), path);
				
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
	}
	
	public List<String> getWeapons(){
		return weapons;
	}
	
	public HashMap<String, List<String>> getWeaponMap(){
		return weaponMap;
	}
	
	public HashMap<String, String> getPathMap(){
		return pathMap;
	}

}
