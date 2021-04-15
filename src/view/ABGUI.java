package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;

import model.Algorithm;

import java.awt.*;
import java.awt.event.*;

import controller.*;

public class ABGUI implements GUI {

	private JFrame frame;
	private Algorithm algorithm;
	private JTextArea dataText, pseudoText, resultText, annoText;
	private Insets insets;
	
	private JButton prev, next, fin;			// for testing purposes
	private JRadioButton opt1, opt2;			// for testing purposes
	private int dataStepsTaken;					// for testing purposes

	public ABGUI(Algorithm algorithm){
		frame = new JFrame("Algorithm Breakdown - " + algorithm.getAlgorithmTitle());
		this.algorithm = algorithm;
		
		dataText = new JTextArea();
		pseudoText = new JTextArea();
		resultText = new JTextArea();
		annoText = new JTextArea();
		
		if (algorithm.isMultiDisplay()){
			opt1 = new JRadioButton(algorithm.getMultiDisplayButtonLabels().get(0), true);
			opt2 = new JRadioButton(algorithm.getMultiDisplayButtonLabels().get(1));
		}
		
		prev = new JButton("Previous Step");
		next = new JButton("Next Step");
		fin = new JButton("Final Result");
		
		insets = frame.getInsets();
		dataStepsTaken = 0;
	}
	
	public void initGUI(){
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(700, 670);
		frame.setResizable(false);
		frame.setLocation(300, 20);
		frame.setLayout(null);
		frame.setVisible(true);
		
		initDisplay();
		initButtons();
		initMenuBar();
	}
	
	public void initDisplay(){
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BorderLayout());
	
		JPanel pseudoPanel = new JPanel();
		pseudoPanel.setLayout(new BorderLayout());
		
		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new BorderLayout());
		
		JScrollPane annoPane = new JScrollPane(annoText);
		annoPane.setLayout(new ScrollPaneLayout());
		
		dataText.setText(algorithm.getDataSteps().get(dataStepsTaken));
		dataText.setEditable(false);
		dataPanel.setBorder(new TitledBorder("Data"));
		dataPanel.add(dataText, BorderLayout.WEST);
		dataPanel.setPreferredSize(new Dimension(270, 345));
		dataPanel.setBackground(Color.white);
		
		pseudoText.setText(algorithm.getPseudocode());
		pseudoText.setEditable(false);
		pseudoPanel.setBorder(new TitledBorder("Pseudocode"));
		pseudoPanel.add(pseudoText, BorderLayout.WEST);
		pseudoPanel.setPreferredSize(new Dimension(270, 345));
		pseudoPanel.setBackground(Color.white);
		
		JLabel label = new JLabel("Result: ");
		
		resultText.setEditable(false);
		resultPanel.setBorder(new EtchedBorder());
		resultPanel.add(label, BorderLayout.WEST);
		resultPanel.add(resultText, BorderLayout.CENTER);
		resultPanel.setPreferredSize(new Dimension(120, 20));
		resultPanel.setBackground(Color.white);
		
		annoText.setText(algorithm.getStepAnnotations().get(dataStepsTaken));
		annoText.setEditable(false);
		annoPane.setBorder(new EtchedBorder());
		annoPane.setPreferredSize(new Dimension(685, 50));
		annoPane.setBackground(Color.white);
		
		frame.add(dataPanel);
		frame.add(pseudoPanel);
		frame.add(resultPanel);
		frame.add(annoPane);
		
		dataPanel.setBounds(25 + insets.left, 10 + insets.top, dataPanel.getPreferredSize().width, dataPanel.getPreferredSize().height);
		pseudoPanel.setBounds(frame.getSize().width - 300 + insets.left, 10 + insets.top, pseudoPanel.getPreferredSize().width, pseudoPanel.getPreferredSize().height);
		resultPanel.setBounds(frame.getSize().width / 2 - (resultPanel.getPreferredSize().width / 2) + insets.left, frame.getSize().height / 2 + 40 + insets.top, resultPanel.getPreferredSize().width, resultPanel.getPreferredSize().height);
		annoPane.setBounds(5 + insets.left, (frame.getSize().height / 2) + 90 + insets.top, annoPane.getPreferredSize().width, annoPane.getPreferredSize().height);
		
		if (algorithm.isMultiDisplay()){
			ButtonGroup multiOptions = new ButtonGroup();
			// final JRadioButton opt1 = new JRadioButton(algorithm.getMultiDisplayButtonLabels().get(0), true);
			// final JRadioButton opt2 = new JRadioButton(algorithm.getMultiDisplayButtonLabels().get(1));
			
			multiOptions.add(opt1);
			multiOptions.add(opt2);
			
			opt2.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// setDataStepsTaken(0);
					setMultiAlgorithm(algorithm.getMultiDisplayAlgorithm());
				}
				
			});
			
			frame.add(opt1);
			frame.add(opt2);
			
			opt1.setBounds(frame.getSize().width / 2 - 320 + insets.left, frame.getSize().height / 2 + 30 + insets.top, 100, 40);
			opt2.setBounds(frame.getSize().width / 2 - 220 + insets.left, frame.getSize().height / 2 + 30 + insets.top, 140, 40);
			
			frame.setVisible(true);
		}
		
		frame.setVisible(true);
	}
	
	public void initButtons(){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout());
		
//		final JButton prev = new JButton("Previous Step");
//		final JButton next = new JButton("Next Step");
//		final JButton fin = new JButton("Final Result");
		
		final Highlighter h = dataText.getHighlighter();
		final Highlighter h2 = pseudoText.getHighlighter();
		// final Highlighter h3 = annoText.getHighlighter();
		
		prev.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e){
				dataStepsTaken--;
				next.setEnabled(true);
				fin.setEnabled(true);
				resultText.setText("");
				
				if (dataStepsTaken == 0){
					prev.setEnabled(false);
					dataText.setText(algorithm.getDataSteps().get(0));
					annoText.setText(algorithm.getStepAnnotations().get(0));
				}
				
				else {	
					String[] sArray = new String[dataStepsTaken+1];
					String[] annoArray = new String[dataStepsTaken+1];
					
					for (int i=0; i < dataStepsTaken+1; i++){
						sArray[i] = algorithm.getDataSteps().get(i);
						annoArray[i] = algorithm.getStepAnnotations().get(i);
					}		
					
					String s = String.join("\n", sArray);
					dataText.setText(s);
					
					String s2 = String.join("\n", annoArray);
					annoText.setText(s2);
					
					displayHighlighter();
				}				
			}
		});
		prev.setEnabled(false);
		buttonPanel.add(prev);

		next.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e){
				if (dataStepsTaken < algorithm.getDataSteps().size() - 1){
					prev.setEnabled(true);
					dataStepsTaken++;
					dataText.setText(dataText.getText() + "\n" + algorithm.getDataSteps().get(dataStepsTaken));
					annoText.setText(annoText.getText() + "\n" + algorithm.getStepAnnotations().get(dataStepsTaken));
					
					displayHighlighter();
				}
				
				else{
					dataStepsTaken++;
					next.setEnabled(false);
					fin.setEnabled(false);
					resultText.setText(algorithm.getResult());
					annoText.setText(annoText.getText() + "\n" + algorithm.getResultAnnotation());
					resultHighlighter();
					h.removeAllHighlights();
					h2.removeAllHighlights();
					return;
				}					
			}
		});
		buttonPanel.add(next);
		
		fin.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e){
				dataStepsTaken = algorithm.getDataSteps().size();
				prev.setEnabled(true);
				next.setEnabled(false);
				fin.setEnabled(false);
				h.removeAllHighlights();
				h2.removeAllHighlights();
				resultText.setText(algorithm.getResult());
				annoText.setText(algorithm.getFinalAnnotationDisplay());
				dataText.setText(algorithm.getFinalDataDisplay());
				resultHighlighter();
			}
			
		});
		buttonPanel.add(fin);
		
		frame.add(buttonPanel); 
		
		buttonPanel.setBounds(150 + insets.left, 525 + insets.top, 400, 25);
		
		frame.setVisible(true);
	}
	
	public void initMenuBar(){
		final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu, ABMenu, quizMenu, notesMenu, helpMenu;
		JMenuItem item;
		
		String alg1 = "Quicksort";
		String alg2 = "Fibonacci Number Calculator";
		String alg3 = "Odd or Even";
		
		String note1 = "RecGen";
		String note2 = "CopMod";
		String note3 = "FibIte";
		
		// create file menu
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		item = new JMenuItem("Close");
		// item.setAccelerator(...) - KeyStroke (i.e. Ctrl+C) potential small bonus feature for some menu items
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				closeSection();
			}
			
		});
		fileMenu.add(item);
		fileMenu.addSeparator();
		
		item = new JMenuItem("Home");
		item.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				removeElement();
				GUI home = new HomeGUI();
				home.initGUI();
			}
			
		});
		
		fileMenu.add(item);
		
		
		// create alg breakdown menu
		ABMenu = new JMenu("Algorithm Breakdown");
		menuBar.add(ABMenu);
		
		item = new JMenuItem("Alg 1 - Quicksort");
		item.addActionListener(new ViewABListener(getMode(), alg1, null));
		ABMenu.add(item);
		ABMenu.addSeparator();
		if (algorithm.getAlgorithmID().equals("quickID"))
			item.setEnabled(false);
		
		item = new JMenuItem("Alg 2 - Fibonacci Sequence");
		item.addActionListener(new ViewABListener(getMode(), alg2, null));
		ABMenu.add(item);
		ABMenu.addSeparator();
		if (algorithm.getAlgorithmID().equals("fibID"))
			item.setEnabled(false);
		
		item = new JMenuItem("Alg 3 - Odd or Even");
		item.addActionListener(new ViewABListener(getMode(), alg3, null));
		ABMenu.add(item);
		if (algorithm.getAlgorithmID().equals("oddEvenID"))
			item.setEnabled(false);
		
		
		// create quiz menu
		quizMenu = new JMenu("Quiz");
		menuBar.add(quizMenu); 
		
		item = new JMenuItem("Start Quiz");
		item.addActionListener(new StartQuizListener(getMode(), new ABGUI(algorithm)));
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
		quizMenu.add(item);
		
		
		// create notes menu
		notesMenu = new JMenu("Notes");
		menuBar.add(notesMenu);
		
		item = new JMenuItem("Recursion - General");
		item.addActionListener(new ViewNoteListener(getMode(), note1, null));
		notesMenu.add(item);
		notesMenu.addSeparator();
		
		item = new JMenuItem("Copies Model");
		item.addActionListener(new ViewNoteListener(getMode(), note2, null));
		notesMenu.add(item);
		notesMenu.addSeparator();
		
		item = new JMenuItem("Fibonnaci Sequence (Iterative Approach)");
		item.addActionListener(new ViewNoteListener(getMode(), note3, null));
		notesMenu.add(item);
		
		
		// create help menu
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		item = new JMenuItem("Using Notes");
		item.addActionListener(new NoteHelpListener());
		helpMenu.add(item);
		helpMenu.addSeparator();
		
		item = new JMenuItem("Using Quiz");
		item.addActionListener(new QuizHelpListener());
		helpMenu.add(item);
		helpMenu.addSeparator();
		
		item = new JMenuItem("Using Algorithm Breakdown");
		item.addActionListener(new ABHelpListener());
		helpMenu.add(item);
		
		
		frame.setJMenuBar(menuBar);
		frame.revalidate();
	}
	
	// COULD NEED ALTERING
	public void displayHighlighter(){
		final Highlighter h = dataText.getHighlighter();
		final Highlighter h2 = pseudoText.getHighlighter();
		final Highlighter h3 = annoText.getHighlighter();
		
		// int highlightSteps = 0;
		for (int i=0; i < algorithm.getHighlightedSteps().size(); i++){
			// highlightSteps = i;
			for (String s : algorithm.getHighlightedSteps().get(i)){
				if (dataText.getText().contains(s)){
					h.removeAllHighlights();
					h2.removeAllHighlights();
					
					int pos = algorithm.getFinalDataDisplay().indexOf(s, 0);
					int pos2 = algorithm.getPseudocode().indexOf(algorithm.getPseudoHighlights().get(i), 0);
					
					try {
						h.addHighlight(pos, pos + s.length(), DefaultHighlighter.DefaultPainter);
						h2.addHighlight(pos2, pos2 + algorithm.getPseudoHighlights().get(i).length(), DefaultHighlighter.DefaultPainter);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
				
				else if (!(dataText.getText().contains(algorithm.getHighlightedSteps().get(0).get(0)))){
					h2.removeAllHighlights();
				}
			}
		}
		for (int l=0; l < algorithm.getStepAnnotations().size(); l++){
			if (annoText.getText().contains(algorithm.getStepAnnotations().get(l)) && !(annoText.getText().contains(algorithm.getResultAnnotation()))){
				h3.removeAllHighlights();
				
				int pos = algorithm.getFinalAnnotationDisplay().indexOf(algorithm.getStepAnnotations().get(l), 0);
				
				try {
					h3.addHighlight(pos, pos + algorithm.getStepAnnotations().get(l).length(), DefaultHighlighter.DefaultPainter);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public void resultHighlighter(){
		Highlighter h3 = annoText.getHighlighter();
		Highlighter h4 = resultText.getHighlighter();
				
		if (annoText.getText().contains(algorithm.getResultAnnotation())){
			// h3.removeAllHighlights();
			
			int pos = algorithm.getFinalAnnotationDisplay().indexOf(algorithm.getResultAnnotation(), 0);
			int pos2 = algorithm.getResult().indexOf(algorithm.getResult(), 0);
			
			try {
				h3.addHighlight(pos, pos + algorithm.getResultAnnotation().length(), DefaultHighlighter.DefaultPainter);
				h4.addHighlight(pos2, pos2 + algorithm.getResult().length(), DefaultHighlighter.DefaultPainter);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public Algorithm getAlgorithm(){
		return algorithm;
	}
	
	public void setMultiAlgorithm(Algorithm a){
		algorithm = a;
		dataStepsTaken = 0;
		
		dataText.setText(a.getDataSteps().get(0));
		pseudoText.setText(a.getPseudocode());
		annoText.setText(a.getStepAnnotations().get(0));
		resultText.setText("");
		
		prev.setEnabled(false);
		next.setEnabled(true);
		fin.setEnabled(true);
	}
	
//	public void setDataStepsTaken(int steps){
//		dataStepsTaken = steps;
//	}
	
	public void removeElement(){
		frame.setVisible(false);
		frame.dispose();
	}
	
	public void closeSection(){
		removeElement();
		System.exit(0);
	}
	
	public void openSection(){
		frame.setVisible(true);
		frame.validate();
		frame.repaint();
	}
	
	public boolean isOpen(){
		return frame.isVisible();
	}
	
	public JFrame getMode(){
		return frame;
	}

	@Override
	public void initNoteQuestionGUI() {
		return;
	}
	
	
	// Methods below for unit testing purposes only
	
	
	public JButton getFinalResultButton(){
		return fin;
	}
	
	public JButton getPreviousStepButton(){
		return prev;
	}
	
	public JButton getNextStepButton(){
		return next;
	}
	
	public JRadioButton getMultiDisplayButton(){
		return opt2;
	}
	
	public int getDataStepsTaken(){
		return dataStepsTaken;
	}
	
	public String getDataStepsText(){
		return dataText.getText();
	}
	
	public String getResultText(){
		return resultText.getText();
	}
	
	public String getAnnoText(){
		return annoText.getText();
	}
	
}
