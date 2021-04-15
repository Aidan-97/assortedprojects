package junit;

import java.awt.*;
import java.awt.event.InputEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.util.List;
import java.util.ArrayList;

import org.junit.*;

import static org.junit.Assert.*;
import view.QuizGUI;
import questions.*;

public class QuizGUITest {
	
	QuizGUI quizgui1, quizgui2, quizgui3;
	// Question q;
	List<Question> lq1, lq2;
	Quiz quiz1, quiz2;
	
	@Before
	public void setUp(){
		Question[] qarray1 = {new TestQuestion(), new SecondTestQuestion(), new ThirdTestQuestion(), 
				new FourthTestQuestion(), new FifthTestQuestion() };
		
		lq1 = new ArrayList<>();
		
		for (Question q : qarray1){
			lq1.add(q);
		}
		
		quizgui1 = new QuizGUI(lq1, lq1.get(0), null, 1);
		quizgui2 = new QuizGUI(lq1.subList(1, lq1.size()), lq1.get(1), null, 2);
		
		lq2 = new ArrayList<>();
		lq2.add(new ThirdTestQuestion());
		lq2.add(new FourthTestQuestion());
		
		quizgui3 = new QuizGUI(lq2, new ThirdTestQuestion(), null, 1);
	}
	
	@Test
	public void testQuizSetUp(){
		assertTrue(quizgui1.getGivenQuiz().equals(lq1));
		assertTrue(quizgui1.getQuestion().equals(lq1.get(0)));
		assertTrue(quizgui1.getCurrentQuestion() == 1);
	}
	
	@Test
	public void testNextQuestionButton() throws AWTException {
		quizgui1.initGUI();
		
		// QuizGUI newgui = new QuizGUI(lq1, lq1.get(1));
		
		click(quizgui1.getNextQuestionButton(), 100);
		try { Thread.sleep(1000); } catch (Exception e) {}
		
		assertTrue(!(quizgui1.isOpen()));
	}
	
	@Test
	public void testClickAnswerSubmssion() throws AWTException {
		quizgui1.initGUI();
		
		JButton b = quizgui1.getAnswerButton();
		if (quizgui1.getQuestion().isMultipleChoice()) {
			if (b.getText().equals(quizgui1.getQuestion().getCorrectAnswer())){
				click(b, 100);
				try { Thread.sleep(200); } catch (Exception e) {}
				assertTrue(quizgui1.submitAnswer(b.getText(), quizgui1.getQuestion().getCorrectAnswer()));
			}
		}
		else
			assertTrue(!(quizgui1.submitAnswer(b.getText(), quizgui1.getQuestion().getCorrectAnswer())));
	}
	
	@Test
	public void testTypeAnswerSubmission() throws AWTException{
		quizgui3.setTextFieldText("");
		quizgui3.initGUI();
		
		JTextField jtf = quizgui3.getAnswerTextField();
		if (!(quizgui3.getQuestion().isMultipleChoice())){
			type(jtf, quizgui3.getQuestion().getCorrectAnswer(), 100);
			try { Thread.sleep(200); } catch (Exception e) {}
			assertTrue(quizgui3.submitAnswer(jtf.getText(), quizgui3.getQuestion().getCorrectAnswer()));
		}
	}
	
	private void click(AbstractButton button, int millis) throws AWTException {
	    Point point = button.getLocationOnScreen();
	    Robot robot = new Robot();
	    robot.mouseMove(point.x + button.getWidth() / 2, point.y + button.getHeight() / 2);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    try { Thread.sleep(millis); } catch (Exception e) {}
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	private void type(JTextField jtf, String s, int millis) throws AWTException {
		Point point = jtf.getLocationOnScreen();
		Robot robot = new Robot();
	    robot.mouseMove(point.x + jtf.getWidth() / 2, point.y + jtf.getHeight() / 2);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    try { Thread.sleep(millis); } catch (Exception e) {}
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    
	    byte[] bytes = s.getBytes();
		for (byte b : bytes){
			int code = b;
			robot.delay(40);
			robot.keyPress(code);
			robot.keyRelease(code);
		}
	}

}
