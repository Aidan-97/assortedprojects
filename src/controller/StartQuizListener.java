package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import java.util.*;

import questions.*;
import view.GUI;
import view.QuizGUI;

/**
 * Listener that allows user to start a quiz from anywhere else on the system.
 * Closes current GUI and opens new QuizGUI with first of 5 randomly selected 
 * questions loaded.
 * 
 * @author AidanJoseph
 *
 */
public class StartQuizListener implements ActionListener {
	
	private JFrame frame;
	private GUI prevGUI;
	
	public StartQuizListener(JFrame frame, GUI prevGUI){
		this.frame = frame;
		this.prevGUI = prevGUI;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.setVisible(false);
		frame.dispose();
		Quiz quiz = new QuizImpl();
		List<Question> temp = quiz.getQuiz();
		GUI newQuiz = new QuizGUI(temp, temp.get(0), prevGUI, 1);
		newQuiz.initGUI();
	}
	

}
