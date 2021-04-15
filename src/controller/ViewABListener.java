package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.GUI;
import view.ABGUI;
import model.*;
import questions.Question;

/**
 * Listener that allows user to view any algorithm breakdown from anywhere else on
 * the system, with warning appearing if relevant option is chosen during quiz.
 * 
 * @author AidanJoseph
 *
 */
public class ViewABListener implements ActionListener {
	
	private JFrame frame;
	private String algID;
	private Question question;
	
	public ViewABListener(JFrame frame, String algID, Question question){
		this.frame = frame;
		this.algID = algID;
		this.question = question;
	}
	
	public int showConfirmMessage(String message) {
		JOptionPane jop = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		return jop.showConfirmDialog(null, message);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (algID.equals("Quicksort")){
			
			if (question == null){
				frame.setVisible(false);
				frame.dispose();
				Algorithm algorithm = new QuicksortAlgorithm();
				GUI newAB = new ABGUI(algorithm);
				newAB.initGUI();
			}
			
			else {
				int result = showConfirmMessage("Are you sure you want to quit the quiz? Progress won't be saved");
				if (result == 0){
					frame.setVisible(false);
					frame.dispose();
					Algorithm algorithm = new QuicksortAlgorithm();
					GUI newAB = new ABGUI(algorithm);
					newAB.initGUI();
				}
			}
		}
		
		else if (algID.equals("Fibonacci Number Calculator")){
			
			if (question == null){
				frame.setVisible(false);
				frame.dispose();
				Algorithm algorithm = new FibonacciAlgorithm();
				GUI newAB = new ABGUI(algorithm);
				newAB.initGUI();
			}
			
			else {
				int result = showConfirmMessage("Are you sure you want to quit the quiz? Progress won't be saved");
				if (result == 0){
					frame.setVisible(false);
					frame.dispose();
					Algorithm algorithm = new FibonacciAlgorithm();
					GUI newAB = new ABGUI(algorithm);
					newAB.initGUI();
				}
			}
		}
		
		else if (algID.equals("Odd or Even")){
			
			if (question == null){
				frame.setVisible(false);
				frame.dispose();
				 Algorithm algorithm = new OddEvenAlgorithm();
				 GUI newAB = new ABGUI(algorithm);
				 newAB.initGUI();
			}
			
			else {
				int result = showConfirmMessage("Are you sure you want to quit the quiz? Progress won't be saved");
				if (result == 0){
					frame.setVisible(false);
					frame.dispose();
					 Algorithm algorithm = new OddEvenAlgorithm();
					 GUI newAB = new ABGUI(algorithm);
					 newAB.initGUI();
				}
			}
		}
	}
}
