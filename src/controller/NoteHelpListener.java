package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class NoteHelpListener implements ActionListener {
	
	String message;
	
	public NoteHelpListener() {
		message = "To view notes and other helpful pointers relating to a\n"
				+ "certain sub-topic of Recursion, select one of the options\n"
				+ "from the 'Notes' menu.\n\n"
				+ "Some of the info in these notes can only be seen by clicking\n"
				+ "on certain panels with appropriate instruction/prompt.\n\n"
				+ "To attempt one question related to the Note you are viewing\n"
				+ "click the 'See relevant quiz question' button.";
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, message);
	}

}
