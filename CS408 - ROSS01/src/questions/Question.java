package questions;

import java.util.List;
/**
 * Interface for definition of Questions that will be part of the Quiz section of the 
 * system.
 * 
 * @author AidanJoseph
 *
 */
public interface Question {
	
	public String getQuestionID();
	
	public String getQuestionText();
	
	public List<String> getAnswers(); 
	
	public String getCorrectAnswer();
	
	public String getTextFromDisplay();
	
	public String getCorrectExplanation();
	
	public String getIncorrectExplanation();
	
	public boolean isMultipleChoice();

}
