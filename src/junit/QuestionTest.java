package junit;

import org.junit.*;
import static org.junit.Assert.*;

import questions.*;

import java.util.List;
import java.util.ArrayList;

public class QuestionTest {
	
	Quiz q1, q2;
	Question question;
	List<Question> lq1, lq2;
	
	@Before
	public void setUp(){	
		q1 = new QuizImpl();
		q2 = new QuizImpl();
		
		question = q1.getRandomQuestion();
		
		lq1 = q1.getQuiz();
		lq2 = q2.getQuiz();
	}
	
	@Test
	public void testQuizRandomisation(){
		String quiz1 = "";
		String quiz2 = "";
		
		for (Question q : lq1){
			quiz1 = q.getQuestionID() + " ";
		}
		
		for (Question q : lq2){
			quiz2 = q.getQuestionID() + " ";
		}
		
		assertTrue(!(quiz1.equals(quiz2)));
	}
	
	@Test
	public void testQuestionTypeCreation(){
		if (question.getAnswers().size() == 1){
			assertTrue(!question.isMultipleChoice());
		}
		else {
			assertTrue(question.isMultipleChoice());
		}
	}
	
}
