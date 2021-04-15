package model;

import java.util.List;

import questions.Question;

/**
 * Interface for definition of Notes that will be part of Notes section of system.
 * 
 * @author AidanJoseph
 *
 */
public interface Note {
	
	String getNoteID();
	
	String getNoteTitle();
	
	List<String> getBulletPoints();
	
	List<String> getDisplayTexts();
	
	List<Question> getRelevantQuestions();

}
