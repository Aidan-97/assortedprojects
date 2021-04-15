package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import questions.Question;

import view.GUI;
import view.QuizGUI;

public class ViewNoteQuestionListener implements ActionListener {
	
	private Question question;
	
	public ViewNoteQuestionListener(Question question) {
		this.question = question;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		GUI noteQuestion = new QuizGUI(null, question, null, 0);
		noteQuestion.initNoteQuestionGUI();
	}

}
