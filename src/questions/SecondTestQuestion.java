package questions;

import java.util.List;
import java.util.ArrayList;

/**
 *	Just exists to test QuizGUI display for questions with 2, not 4, possible answers
 *	and to test handling of selecting answer buttons works in a general case
 *
 *	@author AidanJoseph
 */
public class SecondTestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer, incAnswer2, incAnswer3, corAnswer, displayText, incExpl;
	
	public SecondTestQuestion(){
		answers = new ArrayList<>();
		
		question = "Consider the algorithm, Alg1, below. What will Alg1(2, 4) return?";
		
		incAnswer = "0";
		incAnswer2 = "2";
		incAnswer3 = "4";
		corAnswer = "6";
		
		answers.add(incAnswer);
		answers.add(incAnswer2);
		answers.add(incAnswer3);
		answers.add(corAnswer);
		
		displayText = "Alg1(0, z) = z\n\nAlg1(x, y) = Alg1(x-1, y+1)";
		
		incExpl = "Correct answer is 6, try writing out the full call trace of the algorithm";
	}

	@Override
	public String getQuestionText() {
		return question;
	}

	@Override
	public List<String> getAnswers() {
		return answers;
	}

	@Override
	public String getCorrectAnswer() {
		return corAnswer;
	}

	@Override
	public String getTextFromDisplay() {
		return displayText;
	}

	@Override
	public boolean isMultipleChoice() {
		return true;
	}

	@Override
	public String getQuestionID() {
		return "secondID";
	}

	@Override
	public String getCorrectExplanation() {
		return "";
	}

	@Override
	public String getIncorrectExplanation() {
		return incExpl;
	}

}
