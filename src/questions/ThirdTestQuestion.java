package questions;

import java.util.List;
import java.util.ArrayList;

/**
 *	First test/attempt of user entry style of question. QuizGUI should alter accordingly and answer
 *	entry should be handled/listened to differently
 *
 *	@author AidanJoseph
 */

public class ThirdTestQuestion implements Question {
	
	private List<String> answers;
	private String question, corAnswer, displayText, incExpl;
	
	public ThirdTestQuestion(){
		answers = new ArrayList<>();
		
		question = "What does Alg2(6) return?";
		
		corAnswer = "36";
		
		answers.add(corAnswer);
		
		displayText = "public int Alg2(int x){\n        "
				+ "int y = x*2 - 1;\n        "
				+ "if (x<2)\n           "
				+ "return y;\n        "
				+ "else\n           "
				+ "return y + Alg2(x-1);";
		
		incExpl = "Correct answer is 36. Check the values of the parameter and\nlocal variable at each call of Alg2";
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
		return false;
	}

	@Override
	public String getQuestionID() {
		return "thirdID";
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
