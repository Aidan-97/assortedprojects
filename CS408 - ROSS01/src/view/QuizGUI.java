package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import questions.Question;
// import questions.Quiz;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import controller.*;

import java.util.List;
import java.util.ArrayList;

public class QuizGUI implements GUI {
	
	private JFrame frame;
	private Question question;
	private GUI previousGUI;
	
	private JButton button, nextQuestionButton;		// for testing purposes
	private JTextArea questionText, displayText;	// for testing purposes
	private JTextField textField;					// for testing purposes
	private String textFieldText;					// for testing purposes

	private List<Question> temp;
	private int currentQuestion;

	public QuizGUI(List<Question> temp, Question question, GUI previousGUI, int currentQuestion){
		this.question = question;
		this.temp = temp;
		this.previousGUI = previousGUI;
		this.currentQuestion = currentQuestion;
		frame = new JFrame("Quiz: Question " + currentQuestion);
		
		questionText = new JTextArea();
		displayText = new JTextArea();
		textField = new JTextField();
		button = new JButton();

		textFieldText = "Enter answer here (Delete this text first)";
	}
	
	public void initGUI(){
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		frame.setResizable(false);
		frame.setLocation(300, 20);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		initDisplay();
		initButtons();
		initMenuBar();
	}
	
	public void initNoteQuestionGUI(){
		frame.setSize(700, 400);
		frame.setResizable(false);
		frame.setLocation(100, 0);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		initDisplay();
		initButtons();
	}
	
	public void initDisplay(){
		JPanel questionPanel = new JPanel();
		questionPanel.setLayout(new BorderLayout());
		
		JPanel displayTextPanel = new JPanel();
		displayTextPanel.setLayout(new FlowLayout());
		
		questionText.setText("Q: " + question.getQuestionText());
		questionText.setEditable(false);
		questionPanel.add(questionText);
		questionPanel.setBorder(new EtchedBorder());
		questionPanel.setBackground(Color.white);
		
		displayText.setText(question.getTextFromDisplay());
		displayText.setEditable(false);
		displayText.setFont(new Font("Arial", Font.PLAIN, 20));		// temporary, for test questions
		displayTextPanel.add(displayText);
		displayTextPanel.setBorder(new EtchedBorder());
		displayTextPanel.setBackground(Color.white);
		
//		JLabel label = new JLabel("Score: " + score + "/5");
//		frame.add(label);
		
		frame.add(questionPanel, BorderLayout.NORTH);
		frame.add(displayTextPanel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public void initButtons(){
		if (question.isMultipleChoice()){
			final JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
			
			List<Integer> list = new ArrayList<>();
			for (int i=0; i < question.getAnswers().size(); i++){
				list.add(new Integer(i));
			}
			Collections.shuffle(list);
			for (int j=0; j < list.size(); j++){
				final String s = question.getAnswers().get(list.get(j));
				button = new JButton(s);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e){
						if (submitAnswer(s, question.getCorrectAnswer())){
							JOptionPane.showMessageDialog(null, "Correct! " + question.getCorrectExplanation());
							for (Component jb : buttonPanel.getComponents()){
								jb.setEnabled(false);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Incorrect! " + question.getIncorrectExplanation());
							for (Component jb : buttonPanel.getComponents()){
								jb.setEnabled(false);
							}
						}
					}
				});
				buttonPanel.add(button);
			}
			
			buttonPanel.setPreferredSize(new Dimension(350, 50));
			
			frame.add(buttonPanel, BorderLayout.SOUTH);
			frame.setVisible(true);
		}
		
		else {
			textField = new JTextField(textFieldText);
			textField.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e){
					try {
						String userEntry = textField.getText();
						
						if (submitAnswer(userEntry.trim(), question.getCorrectAnswer())){
							JOptionPane.showMessageDialog(null, "Correct! " + question.getCorrectExplanation());
							textField.setEnabled(false);
						}
						else {
							JOptionPane.showMessageDialog(null, "Incorrect! " + question.getIncorrectExplanation());
							textField.setEnabled(false);
						}
						
					} catch (Exception exc){
						JOptionPane.showMessageDialog(null, "Invalid input");
					}
				}
			});
			
			frame.add(textField, BorderLayout.SOUTH);
		}
		
		nextQuestionButton = new JButton("Next Question");
		nextQuestionButton.addActionListener(new NextQuestionListener(this));
		frame.add(nextQuestionButton, BorderLayout.EAST);
	}
	
	public void initMenuBar(){
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
		
		item = new JMenuItem("Alg 1 - " + alg1);
		item.addActionListener(new ViewABListener(getMode(), alg1, question));
		ABMenu.add(item);
		ABMenu.addSeparator();
		
		item = new JMenuItem("Alg 2 - " + alg2);
		item.addActionListener(new ViewABListener(getMode(), alg2, question));
		ABMenu.add(item);
		ABMenu.addSeparator();
		
		item = new JMenuItem("Alg 3 - " + alg3);
		item.addActionListener(new ViewABListener(getMode(), alg3, question));
		ABMenu.add(item);
		
		// create quiz menu
		quizMenu = new JMenu("Quiz");
		menuBar.add(quizMenu); 
		
		item = new JMenuItem("Quit");
		// item.setActionCommand("quit");
		item.addActionListener(new QuitQuizListener(getMode(), getPreviousGUI()));
		quizMenu.add(item);
		
		// create notes menu
		notesMenu = new JMenu("Notes");
		menuBar.add(notesMenu);
		
		item = new JMenuItem("Recursion - General");
		item.addActionListener(new ViewNoteListener(getMode(), note1, question));
		notesMenu.add(item);
		notesMenu.addSeparator();
		
		item = new JMenuItem("Copies Model");
		item.addActionListener(new ViewNoteListener(getMode(), note2, question));
		notesMenu.add(item);
		notesMenu.addSeparator();
		
		item = new JMenuItem("Fibonnaci Sequence (Iterative Approach)");
		item.addActionListener(new ViewNoteListener(getMode(), note3, question));
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
	
	public Question getQuestion(){
		return question;
	}
	
//	public void setQuestion(Question q){
//		question = q;
//		
//		questionText.setText(q.getQuestionText());
//		displayText.setText(q.getTextFromDisplay());
//		initButtons();
//	}
	
//	public boolean wasCorrect(){
//		return correct;
//	}
	
//	public int getScore(){
//		return score;
//	}
	
	public List<Question> getGivenQuiz(){
		return temp;
	}
	
	public GUI getPreviousGUI(){
		return previousGUI;
	}
	
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
	
	//  Methods below for unit testing purposes only
	
	public boolean submitAnswer(String s1, String s2){
		if (s1.equals(s2))
			return true;
		else
			return false;
	}
	
	public JButton getAnswerButton(){
		return button;
	}
	
	public JButton getNextQuestionButton(){
		return nextQuestionButton;
	}
	
	public JTextField getAnswerTextField(){
		return textField;
	}
	
	public String getQuestionText(){
		return questionText.getText();
	}
	
	public String getDisplayText(){
		return displayText.getText();
	}
	
	public String getTextFieldText(){
		return textFieldText;
	}
	
	public void setTextFieldText(String s){
		textFieldText = s;
	}
	
	public int getCurrentQuestion(){
		return currentQuestion;
	}
	
}
