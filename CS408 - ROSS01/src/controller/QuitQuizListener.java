package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.GUI;

public class QuitQuizListener implements ActionListener {
	
	private JFrame frame;
	private GUI prevGUI;
	
	public QuitQuizListener(JFrame frame, GUI prevGUI) {
		this.frame = frame;
		this.prevGUI = prevGUI;
	}
	
	public int showConfirmMessage(String message){
		JOptionPane jop = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		return jop.showConfirmDialog(null, message);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int result = showConfirmMessage("Are you sure you want to quit the quiz? Progress won't be saved");
		if (result == 0){
			frame.setVisible(false);
			frame.dispose();
			prevGUI.initGUI();
		}
	}

}
