package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import controller.*;
import questions.Quiz;
import questions.QuizImpl;

public class HomeGUI implements GUI {
	
	private JFrame frame;
	private Insets insets;
	private Quiz quiz;
	
	public HomeGUI() {
		frame = new JFrame("Welcome!");
		insets = frame.getInsets();
		quiz = new QuizImpl();
	}

	@Override
	public void initGUI() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocation(300, 20);
		frame.setVisible(true);
		frame.setLayout(null);
		
		try {
			frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("test2.jpeg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initDisplay();
		initButtons();
		initMenuBar();
	}

	@Override
	public void initDisplay() {
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(Color.gray);
		welcomePanel.setBorder(new EtchedBorder());
		welcomePanel.setPreferredSize(new Dimension(380, 45));
		welcomePanel.setLayout(new FlowLayout());
		
		JTextArea welcomeText = new JTextArea();
		welcomeText.setText("Aid To Understanding Recursion");
		welcomeText.setFont(new Font("Arial", Font.ITALIC, 24));
		welcomeText.setBackground(Color.gray);
		welcomeText.setEditable(false);
		welcomePanel.add(welcomeText);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.white);
		infoPanel.setBorder(new LineBorder(Color.black));
		infoPanel.setPreferredSize(new Dimension(480, 60));
		infoPanel.setLayout(new FlowLayout());
		
		JTextArea infoText = new JTextArea();
		infoText.setText("Welcome! This software will provide multiple aids in your understanding of recursion,\n"
				+ "via Notes, Algorithm Breakdowns & Quizzes. Access these through the relevant menu-\n"
				+ "bar options or see an example of each with the buttons below.");
		infoText.setFont(new Font("Arial", Font.PLAIN, 12));
		infoText.setBackground(Color.white);
		infoText.setEditable(false);
		infoPanel.add(infoText);
		
		frame.add(welcomePanel);
		frame.add(infoPanel);
		frame.setVisible(true);
		
		welcomePanel.setBounds((frame.getSize().width / 2) - (welcomePanel.getPreferredSize().width / 2) + insets.left, 35 + insets.top, welcomePanel.getPreferredSize().width, welcomePanel.getPreferredSize().height);
		infoPanel.setBounds((frame.getSize().width / 2) - (infoPanel.getPreferredSize().width / 2) + insets.left, 100 + insets.top, infoPanel.getPreferredSize().width, infoPanel.getPreferredSize().height);
	}

	@Override
	public void initNoteQuestionGUI() {
		return;
	}

	@Override
	public void initButtons() {
		JButton jb1 = new JButton("See Recursion: General Note");
		JButton jb2 = new JButton("See Fibonacci Algorithm Breakdown");
		JButton jb3 = new JButton("See Random Quiz Question");
		
		jb1.setBackground(Color.cyan);
		jb2.setBackground(Color.cyan);
		jb3.setBackground(Color.cyan);
		
		jb1.addActionListener(new ViewNoteListener(getMode(), "RecGen", null));
		jb2.addActionListener(new ViewABListener(getMode(), "Fibonacci Number Calculator", null));
		jb3.addActionListener(new ViewNoteQuestionListener(quiz.getRandomQuestion()));
		
		frame.add(jb1);
		frame.add(jb2);
		frame.add(jb3);
		frame.setVisible(true);
		
		jb1.setBounds(175 + insets.left, 180 + insets.top, 250, 25);
		jb2.setBounds(175 + insets.left, 210 + insets.top, 250, 25);
		jb3.setBounds(175 + insets.left, 240 + insets.top, 250, 25);
	}

	@Override
	public void initMenuBar() {
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
		fileMenu.add(item);
		item.setEnabled(false);
		
		
		// create alg breakdown menu
		ABMenu = new JMenu("Algorithm Breakdown");
		menuBar.add(ABMenu);
		
		item = new JMenuItem("Alg 1 - " + alg1);
		item.addActionListener(new ViewABListener(getMode(), alg1, null));
		ABMenu.add(item);
		ABMenu.addSeparator();
		
		item = new JMenuItem("Alg 2 - " + alg2);
		item.addActionListener(new ViewABListener(getMode(), alg2, null));
		ABMenu.add(item);
		ABMenu.addSeparator();
		
		item = new JMenuItem("Alg 3 - " + alg3);
		item.addActionListener(new ViewABListener(getMode(), alg3, null));
		ABMenu.add(item);
		
		
		// create quiz menu
		quizMenu = new JMenu("Quiz");
		menuBar.add(quizMenu); 
		
		item = new JMenuItem("Start Quiz");
		item.addActionListener(new StartQuizListener(getMode(), new HomeGUI()));
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

	@Override
	public boolean isOpen() {
		return frame.isVisible();
	}

	@Override
	public JFrame getMode() {
		return frame;
	}

}
