package view;

import java.io.IOException;

import javax.swing.JFrame;

public interface IGUI {
	
	void initGUI() throws IOException;
	
	void initPanels() throws IOException;
	
	void initInputs();
	
	void initLabels();
	
	void initMenuBar();
	
	void refresh();
	
	void close();
	
	boolean isOpen();
	
	JFrame getFrame();

}
