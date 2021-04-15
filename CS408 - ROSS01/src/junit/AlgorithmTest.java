package junit;

import org.junit.*;
import static org.junit.Assert.*;

import model.Algorithm;
import model.FibonacciAlgorithm;
import model.QuicksortAlgorithm;
import model.OddEvenAlgorithm;

public class AlgorithmTest {
	
	Algorithm a1, a2, a3;
	
	@Before
	public void setUp(){
		a1 = new QuicksortAlgorithm();
		a2 = new FibonacciAlgorithm();
		a3 = new OddEvenAlgorithm();
	}
	
	@Test
	public void testListSizesMatch(){
		// Quicksort
		assertTrue(a1.getHighlightedSteps().size() == a1.getPseudoHighlights().size());
		assertTrue(a1.getDataSteps().size() == a1.getStepAnnotations().size() - 1);
		
		// Fibonacci
		assertTrue(a2.getHighlightedSteps().size() == a2.getPseudoHighlights().size());
		assertTrue(a2.getDataSteps().size() == a2.getStepAnnotations().size() - 1);
		
		// Odd/Even
		assertTrue(a3.getHighlightedSteps().size() == a3.getPseudoHighlights().size());
		assertTrue(a3.getDataSteps().size() == a3.getStepAnnotations().size() - 1);
	}
	
	@Test
	public void testFinalDataDisplay(){
		// Quicksort
		assertTrue(a1.getFinalDataDisplay().equals(String.join("\n", a1.getDataSteps())));
		
		// Fibonacci
		assertTrue(a2.getFinalDataDisplay().equals(String.join("\n", a2.getDataSteps())));
		
		// Odd/Even
		assertTrue(a3.getFinalDataDisplay().equals(String.join("\n", a3.getDataSteps())));
	}
	
	@Test
	public void testFinalAnnotationDisplay(){
		// Quicksort
		assertTrue(a1.getFinalAnnotationDisplay().equals(String.join("\n", a1.getStepAnnotations())));
		
		// Fibonacci
		assertTrue(a2.getFinalAnnotationDisplay().equals(String.join("\n", a2.getStepAnnotations())));
		
		// Odd/Even
		assertTrue(a3.getFinalAnnotationDisplay().equals(String.join("\n", a3.getStepAnnotations())));
	}

}
