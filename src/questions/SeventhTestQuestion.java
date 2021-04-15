package questions;

import java.util.List;
import java.util.ArrayList;

public class SeventhTestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer, corAnswer, displayText, incExpl, corExpl;
	
	public SeventhTestQuestion() {
		answers = new ArrayList<>();
		
		question = "Are the following recursive and iterative algorithms equivalent? (i.e. do they produce the same output for the same input?)";
		
		incAnswer = "No";
		corAnswer = "Yes";
		
		answers.add(incAnswer);
		answers.add(corAnswer);
		
		displayText = "int mysteryRec(int n)\n      "
				+ "if (n > 0)\n          "
				+ "return n + mysteryRec(n-1)\n      "
				+ "return 0\n\n"
				+ "int mysteryIte(int n)\n      "
				+ "int sum = 0\n      "
				+ "while (n > 0)\n          "
				+ "sum = sum + n\n          "
				+ "n = n-1\n      "
				+ "return sum";
		
		incExpl = "";
		corExpl = "";
	}

	@Override
	public String getQuestionID() {
		return "seventhID";
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
