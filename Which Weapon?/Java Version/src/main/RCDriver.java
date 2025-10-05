package main;

import java.io.IOException;

import view.*;
import model.*;

public class RCDriver {
	
	public static void main(String[] args) throws IOException{
		
//		IWeaponBuilder wb = new WeaponBuilder();
//		wb.buildWeaponMap();
		
//		for (String w : wb.getWeapons()){
//			System.out.println(w);
//			for (int i=0; i<wb.getWeaponMap().get(w).size(); i++){
//				System.out.println(wb.getWeaponMap().get(w).get(i));
//			}
//			System.out.println("\n========\n");
//		}
		
		IGUI game = new GUI();
		game.initGUI();
		
	}
	
}

