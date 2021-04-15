package questions;

import java.util.List;
/**
 * Interface for definition of QuizImpl class for assembly of 5 random Questions as
 * part of Quiz section of system.
 * 
 * @author AidanJoseph
 *
 */
public interface Quiz {
	
	public Question getRandomQuestion();
	
	public List<Question> getAllQuestions();
	
	public List<Question> getQuiz();

}
