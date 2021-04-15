package view;

import javax.swing.JFrame;

public interface GUI {
	
	void initGUI();
	
	void initDisplay();
	
	/**
	 * For QuizGUI only
	 */
	void initNoteQuestionGUI();
	
	void initButtons();
	
	void initMenuBar();
	
	void removeElement();
	
	void closeSection();
	
	void openSection();
	
	boolean isOpen();
	
	JFrame getMode();

}
