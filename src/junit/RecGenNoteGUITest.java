package junit;

import java.awt.*;
import java.awt.event.InputEvent;

import javax.swing.AbstractButton;
import javax.swing.JTextArea;

import org.junit.*;

import static org.junit.Assert.*;

import view.RecGenNoteGUI;

import model.Note;
import model.RecGenNote;

public class RecGenNoteGUITest {
	
	Note rgn;
	RecGenNoteGUI rgnGUI;
	
	@Before
	public void setUp(){
		rgn = new RecGenNote();
		rgnGUI = new RecGenNoteGUI(rgn);
	}
	
	@Test
	public void testTailRecButton() throws AWTException{
		rgnGUI.initGUI();
		
		assertTrue(rgnGUI.getRecTypeExText().equals(rgn.getDisplayTexts().get(2)));
		assertTrue(rgnGUI.getRecTypeDefText().equals(rgn.getDisplayTexts().get(7)));
		
		click(rgnGUI.getTailRecButton(), 100);
		try { Thread.sleep(200); } catch (Exception e) {}
		
		assertTrue(rgnGUI.getRecTypeExText().equals(rgn.getDisplayTexts().get(3)));
		assertTrue(rgnGUI.getRecTypeDefText().equals(rgn.getDisplayTexts().get(8)));
	}
	
	@Test
	public void testSeeQuizQuestionButton() throws AWTException{
		rgnGUI.initGUI();
		
		assertTrue(rgnGUI.isExampleQuestionVisible() == false);
		
		click(rgnGUI.getSeeQuestionButton(), 100);
		try { Thread.sleep(200); } catch (Exception e) {}
		
		assertTrue(rgnGUI.isExampleQuestionVisible());
	}
	
	@Test
	public void testFactCallTracePanelClick() throws AWTException{
		rgnGUI.initGUI();
		
		assertTrue(rgnGUI.getFactCallTraceText().equals("Click panel to reveal call trace of factorial(5)"));
		
		clickTextArea(rgnGUI.getFactCallTracePanel(), 100);
		try { Thread.sleep(200); } catch (Exception e) {}
		
		assertTrue(rgnGUI.getFactCallTraceText().equals(rgn.getDisplayTexts().get(1)));
	}
	
	private void click(AbstractButton button, int millis) throws AWTException {
	    Point point = button.getLocationOnScreen();
	    Robot robot = new Robot();
	    robot.mouseMove(point.x + button.getWidth() / 2, point.y + button.getHeight() / 2);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    try { Thread.sleep(millis); } catch (Exception e) {}
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	private void clickTextArea(JTextArea jta, int millis) throws AWTException {
	    Point point = jta.getLocationOnScreen();
	    Robot robot = new Robot();
	    robot.mouseMove(point.x + jta.getWidth() / 2, point.y + jta.getHeight() / 2);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    try { Thread.sleep(millis); } catch (Exception e) {}
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
