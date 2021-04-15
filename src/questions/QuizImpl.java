package questions;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
/**
 * Implementation of Quiz functionality, all implemented questions gathered for
 * assembly of random 5-question quizzes.
 * 
 * @author AidanJoseph
 *
 */
public class QuizImpl implements Quiz {
	
	private List<Question> questionSet;
	private Question q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15;
	
	public QuizImpl(){
		questionSet = new ArrayList<>();
		
		q1 = new TestQuestion();
		q2 = new SecondTestQuestion();
		q3 = new ThirdTestQuestion();
		q4 = new FourthTestQuestion();
		q5 = new FifthTestQuestion();
		q6 = new SixthTestQuestion();
		q7 = new SeventhTestQuestion();
		q8 = new EighthTestQuestion();
		q9 = new NinthTestQuestion();
		q10 = new TenthTestQuestion();
		q11 = new EleventhTestQuestion();
		q12 = new TwelfthTestQuestion();
		q13 = new ThirteenthTestQuestion();
		q14 = new FourteenthTestQuestion();
		q15 = new FifteenthTestQuestion();
		
		questionSet.add(q1);
		questionSet.add(q2);
		questionSet.add(q3);
		questionSet.add(q4);
		questionSet.add(q5);
		questionSet.add(q6);
		questionSet.add(q7);
		questionSet.add(q8);
		questionSet.add(q9);
		questionSet.add(q10);
		questionSet.add(q11);
		questionSet.add(q12);
		questionSet.add(q13);
		questionSet.add(q14);
		questionSet.add(q15);
	}

	@Override
	public Question getRandomQuestion() {
		Random rand = new Random();
		return questionSet.get(rand.nextInt(questionSet.size()));
	}

	@Override
	public List<Question> getAllQuestions() {
		return questionSet;
	}

	@Override
	public List<Question> getQuiz() {
		List<Question> temp = new ArrayList<>();
		Collections.shuffle(questionSet);
		for (Question q : questionSet){
			temp.add(q);
		}
		
		return temp.subList(0, 5);
	}

}
