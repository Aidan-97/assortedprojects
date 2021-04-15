package junit;

import java.awt.*;
import java.awt.event.InputEvent;

import javax.swing.AbstractButton;

import org.junit.*;
import static org.junit.Assert.*;

import view.ABGUI;

import model.Algorithm;
import model.FibonacciAlgorithm;
import model.QuicksortAlgorithm;

public class ABGUITest {

	ABGUI abgui, abgui2;
	Algorithm alg, alg2;
	
	@Before
	public void setUp(){
		alg = new FibonacciAlgorithm();
		alg2 = new QuicksortAlgorithm();
		abgui = new ABGUI(alg);
		abgui2 = new ABGUI(alg2);
	}
	
	@Test
	public void testDataStepsTakenSetUp(){
		assertTrue(abgui.getDataStepsTaken() == 0);
	}
	
	@Test
	public void testFinalResultClick() throws AWTException{
		abgui.initGUI();
		
		click(abgui.getFinalResultButton(), 100);
		try { Thread.sleep(200); } catch (Exception e) {}
		
		assertTrue(abgui.getDataStepsTaken() == alg.getDataSteps().size());
		assertTrue(abgui.getDataStepsText().equals(alg.getFinalDataDisplay()));
		assertTrue(abgui.getResultText().equals(alg.getResult()));
		assertTrue(abgui.getAnnoText().equals(alg.getFinalAnnotationDisplay()));
		assertTrue(abgui.getNextStepButton().isEnabled() == false);
		assertTrue(abgui.getPreviousStepButton().isEnabled());
	}
	
	@Test
	public void testNextStepClick() throws AWTException {
		abgui.initGUI();
		
		click(abgui.getNextStepButton(), 100);
		try { Thread.sleep(200); } catch (Exception e) {}
		
		assertTrue(abgui.getDataStepsTaken() == 1);
		assertTrue(abgui.getPreviousStepButton().isEnabled());
		assertTrue(abgui.getDataStepsText().equals(String.join("\n", alg.getDataSteps().subList(0, 2))));
		assertTrue(abgui.getAnnoText().equals(String.join("\n", alg.getStepAnnotations().subList(0, 2))));
	}
	
	@Test
	public void testPreviousStepClick() throws AWTException {
		abgui.initGUI();
		
		click(abgui.getNextStepButton(), 100);
		try { Thread.sleep(200); } catch (Exception e) {}
		
		click(abgui.getPreviousStepButton(), 100);
		try { Thread.sleep(200); } catch (Exception e) {}
		
		assertTrue(abgui.getDataStepsTaken() == 0);
		assertTrue(abgui.getDataStepsText().equals(alg.getDataSteps().get(0)));
		assertTrue(abgui.getAnnoText().equals(alg.getStepAnnotations().get(0)));
		assertTrue(abgui.getPreviousStepButton().isEnabled() == false);
	}
	
	@Test
	public void testMultiDisplayButton() throws AWTException{
		abgui2.initGUI();
		
		assertTrue(abgui2.getAlgorithm().getAlgorithmID().equals(alg2.getAlgorithmID()));
		
		click(abgui2.getMultiDisplayButton(), 100);
		try { Thread.sleep(200); } catch (Exception e) {}
		
		assertTrue(abgui2.getAlgorithm().getAlgorithmID().equals(alg2.getMultiDisplayAlgorithm().getAlgorithmID()));
	}
	
	private void click(AbstractButton button, int millis) throws AWTException {
	    Point point = button.getLocationOnScreen();
	    Robot robot = new Robot();
	    robot.mouseMove(point.x + button.getWidth() / 2, point.y + button.getHeight() / 2);
	    robot.mousePress(InputEvent.BUTTON1_MASK);
	    try { Thread.sleep(millis); } catch (Exception e) {}
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
}
