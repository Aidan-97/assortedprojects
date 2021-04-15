package questions;

import java.util.List;
import java.util.ArrayList;

public class TwelfthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, corAnswer, displayText, incExpl;
	
	public TwelfthTestQuestion() {
		answers = new ArrayList<>();
		
		question = "What is the console output of printFunc(1000)? Algorithm shown below";
		
		displayText = "public void printFunc(int i) {\n      "
				+ "if (n > 4000)\n          "
				+ "return;\n      "
				+ "System.out.print(n + ' ');\n      "
				+ "printFunc(2*n);\n      "
				+ "System.out.print(n + ' ');\n}";
		
		corAnswer = "1000 2000 4000 4000 2000 1000";
		answers.add(corAnswer);
		
		incExpl = "Correct answer is: " + corAnswer;
	}

	@Override
	public String getQuestionID() {
		return "twelfthID";
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
		return "";
	}

	@Override
	public String getIncorrectExplanation() {
		return incExpl;
	}

	@Override
	public boolean isMultipleChoice() {
		return false;
	}

}
