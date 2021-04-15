package questions;

import java.util.List;
import java.util.ArrayList;

public class FifthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer, corAnswer, displayText, incExpl, corExpl;
	
	public FifthTestQuestion() {
		answers = new ArrayList<>();
		
		question = "Is the displayed Java code for a recursive list searching algorithm written properly?";
		
		incAnswer = "Yes";
		corAnswer = "No";
		
		answers.add(incAnswer);
		answers.add(corAnswer);
		
		displayText = "public static int findString(List<String> inputList){\n        "
				+ "if (inputList.get(0).equals(targetString))\n            "
						+ "return 1 + findString(inputList.subList(1, inputList.size()));\n        "
				+ "else\n            return findString(inputList.subList(1, inputList.size()));\n}";
		
		incExpl = "Since the algorithm here has no base case";
		corExpl = incExpl;
	}

	@Override
	public String getQuestionID() {
		return "fifthID";
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
	public String getCorrectExplanation() {
		return corExpl;
	}

	@Override
	public String getIncorrectExplanation() {
		return incExpl;
	}

}
