package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Listener that allows user to view help window regarding using Algorithm Breakdown
 * section of system. Just loads JOptionPane with detailed help message String.
 * 
 * @author AidanJoseph
 *
 */
public class QuizHelpListener implements ActionListener {
	
	private String message;
	
	public QuizHelpListener() {
		message = "To start a Quiz, click the 'Start Quiz' option from the\n"
				+ "'Quiz' menu or press Ctrl+Q.\n\n"
				+ "For multiple choice questions, click the button that contains\n"
				+ "the text you wish to submit as your answer.\n\n"
				+ "For user entry questions, type in to the text box what you\n"
				+ "wish to submit as your answer and then press enter. (Please\n"
				+ "check whether or not the question asks you to type your answer\n"
				+ "in a specific format).\n\n"
				+ "To quit the Quiz, either select the 'Quit' option from the\n"
				+ "'Quiz' menu (only visible during a quiz) or select an option\n"
				+ "to view another section of the system during the quiz \n"
				+ "(i.e. a certain Note or Algorithm Breakdown).";
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, message);
	}

}
