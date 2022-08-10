package model;

import java.util.*;
// import java.io.*;

public interface IWeaponBuilder {
	
	void buildWeaponMap();
	
	void buildPathMap();
	
	List<String> getWeapons();
	
	HashMap<String, List<String>> getWeaponMap();
	
	HashMap<String, String> getPathMap();

}
