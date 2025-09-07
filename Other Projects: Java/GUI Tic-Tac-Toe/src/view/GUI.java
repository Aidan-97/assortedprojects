package view;

import javax.swing.JFrame;

public interface GUI {
	
	void initGUI();
	
	void initDisplay();
	
	void initMenuBar();
	
	void clear();
	
	void close();
	
	boolean isOpen();
	
	JFrame getFrame();

}
