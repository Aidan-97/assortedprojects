package questions;

import java.util.List;
import java.util.ArrayList;

public class EleventhTestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer1, incAnswer2, incAnswer3, corAnswer, displayText, corExpl, incExpl;

	public EleventhTestQuestion() {
		answers = new ArrayList<>();
		
		question = "What type of calculation does the function below perform?";
		
		displayText = "int fun(int x, int y)\n      "
				+ "if (y == 0)\n          "
				+ "return 0\n      "
				+ "return x + fun(x, y-1)";
		
		incAnswer1 = "x + y";
		incAnswer2 = "x + x*y";
		incAnswer3 = "x^y";
		corAnswer = "x*y";
		
		answers.add(incAnswer1);
		answers.add(incAnswer2);
		answers.add(incAnswer3);
		answers.add(corAnswer);
		
		incExpl = "The function adds x to itself y times which is x*y";
		corExpl = "";
	}
	
	@Override
	public String getQuestionID() {
		return "eleventhID";
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
	public String getCorrectExplanation() {
		return corExpl;
	}

	@Override
	public String getIncorrectExplanation() {
		return incExpl;
	}

	@Override
	public boolean isMultipleChoice() {
		return true;
	}

}
