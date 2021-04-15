package questions;

import java.util.List;
import java.util.ArrayList;

/**
 *	Second test of user entry style question functionality, more realistic this time
 *
 *	@author AidanJoseph
 */

public class FourthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, corAnswer, displayText, incExpl;
	
	public FourthTestQuestion(){
		answers = new ArrayList<>();
		
		question = "What is the final result of the algorithm below run on the list: numList = [3, 1, 2, 4]\n"
				+ "     (Enter answer in the format: [a, b, c,..., z], with a, b, c & z representing integer values. MAKE SURE SPACES ARE INCLUDED)\n"
				+ "     [NB: head(list) = first element of list, tail(list) = list with first element removed]";
		
		corAnswer = "[6, 2, 4, 8, 1]";
		
		answers.add(corAnswer);
		
		displayText = "Algorithm1(numList)\n        if numList empty then\n           1\n        else 2*head(numList) + Algorithm1(tail(numList))\n\n'+' = concatenation here.";
		
		incExpl = "Correct answer is [6, 2, 4, 8, 1]. Check you understand the role of the passive and active flows\n"
				+ "in this recursive algorithm. It may help to visit the Note on the Copies Model and for problems\n"
				+ "like this in the future, to write out the full call trace of the algorithm. Also check that \n"
				+ "your answer was entered in the correct format (given in the question)";
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
		return "fourthID";
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
