package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.util.List;

import questions.Question;
import view.GUI;
import view.QuizGUI;

/**
 * Listener for 'Next Question' button on quiz, removes current GUI and
 * replaces it with new GUI with new (random) Question loaded.
 * 
 * @author AidanJoseph
 *
 */
public class NextQuestionListener implements ActionListener {
	
	private QuizGUI quizMode;
	private List<Question> quiz;
	private GUI prevGUI;
	
	public NextQuestionListener(QuizGUI currentQuestion){
		quizMode = currentQuestion;
		quiz = currentQuestion.getGivenQuiz();
		prevGUI = quizMode.getPreviousGUI();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {	
		if (quiz == null){
			quizMode.removeElement();
		}
		else {
		
			if (quizMode.getQuestion().getQuestionID().equals(quiz.get(0).getQuestionID())){
				quizMode.removeElement();
				quizMode = new QuizGUI(quiz, quiz.get(1), prevGUI, 2);
				quizMode.initGUI();
			}
			else if (quizMode.getQuestion().getQuestionID().equals(quiz.get(1).getQuestionID())){
				quizMode.removeElement();
				quizMode = new QuizGUI(quiz, quiz.get(2), prevGUI, 3);
				quizMode.initGUI();
			}
			else if (quizMode.getQuestion().getQuestionID().equals(quiz.get(2).getQuestionID())){
				quizMode.removeElement();
				quizMode = new QuizGUI(quiz, quiz.get(3), prevGUI, 4);
				quizMode.initGUI();
			}
			else if (quizMode.getQuestion().getQuestionID().equals(quiz.get(3).getQuestionID())){
				quizMode.removeElement();
				quizMode = new QuizGUI(quiz, quiz.get(4), prevGUI, 5);
				quizMode.initGUI();
			}
			else if (quizMode.getQuestion().getQuestionID().equals(quiz.get(4).getQuestionID())){
				JOptionPane.showMessageDialog(null, "Quiz Complete!");
				quizMode.removeElement();
				prevGUI.initGUI();
			}
			
		}
	}

//	@Override
//	public void mouseClicked(MouseEvent me) {
//		if (me.getClickCount() == 4){
//			((JButton)me.getSource()).setEnabled(false);
//		}
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

}

