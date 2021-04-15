package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.CopModNote;
import model.Note;
import model.FibIteNote;
import model.RecGenNote;
import questions.Question;
import view.GUI;
import view.FibIteNoteGUI;
import view.RecGenNoteGUI;
import view.CopModNoteGUI;

public class ViewNoteListener implements ActionListener {
	
	private JFrame frame;
	private String noteID;
	private Question question;
	
	public ViewNoteListener(JFrame frame, String noteID, Question question){
		this.frame = frame;
		this.noteID = noteID;
		this.question = question;
	}
	
	public int showConfirmMessage(String message) {
		JOptionPane jop = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		return jop.showConfirmDialog(null, message);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (noteID.equals("FibIte")){
			if (question == null){
				frame.setVisible(false);
				frame.dispose();
				Note note = new FibIteNote();
				GUI newAB = new FibIteNoteGUI(note);
				newAB.initGUI();
			}
			
			else {
				int result = showConfirmMessage("Are you sure you want to quit the quiz? Progress won't be saved");
				if (result == 0){
					frame.setVisible(false);
					frame.dispose();
					Note note = new FibIteNote();
					GUI newAB = new FibIteNoteGUI(note);
					newAB.initGUI();
				}
			}
		}
		
		else if (noteID.equals("RecGen")){
			if (question == null){
				frame.setVisible(false);
				frame.dispose();
				Note note = new RecGenNote();
				GUI newAB = new RecGenNoteGUI(note);
				newAB.initGUI();
			}
			
			else {
				int result = showConfirmMessage("Are you sure you want to quit the quiz? Progress won't be saved");
				if (result == 0){
					frame.setVisible(false);
					frame.dispose();
					Note note = new RecGenNote();
					GUI newAB = new RecGenNoteGUI(note);
					newAB.initGUI();
				}
			}
		}
		
		else if (noteID.equals("CopMod")){
			if (question == null){
				frame.setVisible(false);
				frame.dispose();
				Note note = new CopModNote();
				GUI newAB = new CopModNoteGUI(note);
				newAB.initGUI();
			}
			
			else {
				int result = showConfirmMessage("Are you sure you want to quit the quiz? Progress won't be saved");
				if (result == 0){
					frame.setVisible(false);
					frame.dispose();
					Note note = new CopModNote();
					GUI newAB = new CopModNoteGUI(note);
					newAB.initGUI();
				}
			}
		}
		
	}
}
