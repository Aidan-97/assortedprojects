package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Listener that allows user to view help window regarding using the Quiz.
 * Just opens JOptionPane with detailed help message String.
 * 
 * @author AidanJoseph
 *
 */
public class ABHelpListener implements ActionListener {
	
	String message;
	
	public ABHelpListener(){
		message = "To view the breakdown of a specific recursive algorithm,\n"
				+ "click one of the three options (Quicksort, Fibonacci Numbers \n"
				+ "or Odd or Even Numbers) from the 'Algorithm Breakdown' menu.\n\n"
				+ "To move one step forward through the breakdown of the chosen\n"
				+ "algorithm, click the 'Next Step' button.\n\n"
				+ "To move one step backward through the breakdown of the chosen\n"
				+ "algorithm, click the 'Previous Step' button.\n\n"
				+ "To instantly see the complete breakdown of the chosen algorithm\n"
				+ "plus the final result of the algorithm, press the 'Final Result' button.\n\n"
				+ "For the Quicksort & Odd or Even Numbers breakdowns, you can view\n"
				+ "the algorithm running on different inputs by alternating between\n"
				+ "the two radio buttons underneath the panel labelled 'Data'.";
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, message);
	}

}
