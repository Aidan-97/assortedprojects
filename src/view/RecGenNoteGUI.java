package view;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import controller.*;
import model.Note;

public class RecGenNoteGUI implements GUI {
	
	private JFrame frame;
	private JTextArea dtext2, dtext3, dtext4;
	private JPanel typePanel, qpanel, defPanel;
	private Note note;
	private Insets insets;
	
	private JRadioButton opt2;					// for testing purposes
	private JButton qbutton;					// for testing purposes
	private boolean exampleQuetsionVisible;		// for testing purposes
	
	public RecGenNoteGUI(Note note) {
		frame = new JFrame("Notes - " + note.getNoteTitle());
		this.note = note;
		
		dtext2 = new JTextArea();
		dtext3 = new JTextArea();
		dtext4 = new JTextArea();
		
		qpanel = new JPanel();
		typePanel = new JPanel();
		defPanel = new JPanel();
		
		opt2 = new JRadioButton("Tail Recursion");
		qbutton = new JButton("See relevant quiz question");
		exampleQuetsionVisible = false;
		insets = frame.getInsets();
	}

	@Override
	public void initGUI() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(805, 730);
		frame.setResizable(false);
		frame.setLocation(300, 20);
		frame.setVisible(true);
		frame.setLayout(null);
		
		initDisplay();
		initButtons();
		initMenuBar();
	}

	@Override
	public void initDisplay() {
//		// scroll pane
//		JScrollPane pane = new JScrollPane();
//		pane.setPreferredSize(new Dimension(795, 600));
//		Insets insets = pane.getInsets();
//		frame.add(pane);
//		frame.setVisible(true);
		
		// bullet points 1
		JPanel bulletPanel = new JPanel();
		bulletPanel.setBackground(Color.lightGray);
		bulletPanel.setBorder(new EtchedBorder());
		bulletPanel.setPreferredSize(new Dimension(790, 192));
			
		JTextArea bulletText = new JTextArea();
		bulletText.setFont(new Font("Arial", Font.BOLD, 12));
		bulletText.setBackground(Color.lightGray);
		bulletText.setEditable(false);
		bulletPanel.add(bulletText);
		
		String bp = String.join("\n\n", note.getBulletPoints().subList(0, 4));
		bulletText.setText(bp);
		
		frame.add(bulletPanel);
		frame.setVisible(true);
		
		bulletPanel.setBounds(5 + insets.left, 5 + insets.top, bulletPanel.getPreferredSize().width, bulletPanel.getPreferredSize().height);
	
		// display texts
		JTextArea dtext1 = new JTextArea();
		
		dtext1.setText(note.getDisplayTexts().get(0));
		dtext1.setEditable(false);
		
		// dtext2 defined in fields
		dtext2.setText("Click panel to reveal call trace of factorial(5)");
		dtext2.setSize(180, 150);
		dtext2.setEditable(false);
		
		JPanel dpanel1 = new JPanel();
		JPanel dpanel2 = new JPanel();
		
		dpanel1.setLayout(new BorderLayout());
		dpanel1.setBackground(Color.white);
		dpanel1.setBorder(new TitledBorder("Factorial Pseudocode"));
		dpanel1.setPreferredSize(new Dimension(260, 200));
		dpanel1.add(dtext1, BorderLayout.WEST);
		frame.add(dpanel1);
		frame.setVisible(true);
		
		dpanel1.setBounds(140 + insets.left, 200 + insets.top, dpanel1.getPreferredSize().width, dpanel1.getPreferredSize().height);
		
		dpanel2.setLayout(new BorderLayout());
		dpanel2.setBackground(Color.white);
		dpanel2.setBorder(new TitledBorder("Call trace of factorial(5)"));
		dpanel2.setPreferredSize(new Dimension(260, 200));
		dpanel2.add(dtext2, BorderLayout.WEST);
		frame.add(dpanel2);
		frame.setVisible(true);
		
		dpanel2.setBounds(405 + insets.left, 200 + insets.top, dpanel2.getPreferredSize().width, dpanel2.getPreferredSize().height);
	
		JTextArea bulletText2 = new JTextArea();
		bulletText2.setText(note.getBulletPoints().get(4));
		bulletText2.setFont(new Font("Arial", Font.BOLD, 12));
		bulletText2.setBackground(Color.lightGray);
		bulletText2.setEditable(false);
		
		JPanel bulletPanel2 = new JPanel();
		bulletPanel2.setLayout(new BorderLayout());
		bulletPanel2.setBackground(Color.lightGray);
		bulletPanel2.setBorder(new EtchedBorder());
		bulletPanel2.setPreferredSize(new Dimension(790, 23));
		bulletPanel2.add(bulletText2, BorderLayout.WEST);
		
		frame.add(bulletPanel2);
		frame.setVisible(true);
		
		bulletPanel2.setBounds(5 + insets.left, 405 + insets.top, bulletPanel2.getPreferredSize().width, bulletPanel2.getPreferredSize().height);
		
		// panel with radio buttons (or other) to show different types of recursion (head, tail, mutual etc.)
		dtext3.setText(note.getDisplayTexts().get(2));
		dtext3.setEditable(false);
		
		typePanel.setLayout(new BorderLayout());
		typePanel.setBackground(Color.white);
		typePanel.setBorder(new TitledBorder("Linear Recursion: Example"));
		typePanel.setPreferredSize(new Dimension(260, 200));
		typePanel.add(dtext3, BorderLayout.WEST);
		frame.add(typePanel);
		frame.setVisible(true);
		
		typePanel.setBounds(5 + insets.left, 435 + insets.top, typePanel.getPreferredSize().width, typePanel.getPreferredSize().height);
		
		dtext4.setText(note.getDisplayTexts().get(7));
		dtext4.setEditable(false);
		
		defPanel.setLayout(new BorderLayout());
		defPanel.setBackground(Color.white);
		defPanel.setBorder(new TitledBorder("Linear Recursion: Definition"));
		defPanel.setPreferredSize(new Dimension(345, 140));
		defPanel.add(dtext4, BorderLayout.WEST);
		frame.add(defPanel);
		frame.setVisible(true);
		
		defPanel.setBounds(425 + insets.left, 435 + insets.top, defPanel.getPreferredSize().width, defPanel.getPreferredSize().height);
		
		ButtonGroup bg = new ButtonGroup();
		final JRadioButton opt1 = new JRadioButton("Linear Recursion", true);
		// final JRadioButton opt2 = new JRadioButton("Tail Recursion");
		final JRadioButton opt3 = new JRadioButton("Mutual Recursion");
		final JRadioButton opt4 = new JRadioButton("Binary Recursion");
		final JRadioButton opt5 = new JRadioButton("Nested Recursion");
		
		bg.add(opt1);
		bg.add(opt2);
		bg.add(opt3);
		bg.add(opt4);
		bg.add(opt5);
		
		opt1.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				typePanel.setBorder(new TitledBorder("Linear Recursion: Example"));
				defPanel.setBorder(new TitledBorder("Linear Recursion: Definition"));
				dtext3.setText(note.getDisplayTexts().get(2));
				dtext4.setText(note.getDisplayTexts().get(7));
			}
			
		});
		
		opt2.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				typePanel.setBorder(new TitledBorder("Tail Recursion: Example"));
				defPanel.setBorder(new TitledBorder("Tail Recursion: Definition"));
				dtext3.setText(note.getDisplayTexts().get(3));
				dtext4.setText(note.getDisplayTexts().get(8));
			}
			
		});
		
		opt3.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				typePanel.setBorder(new TitledBorder("Mutual Recursion: Example"));
				defPanel.setBorder(new TitledBorder("Mutual Recursion: Definition"));
				dtext3.setText(note.getDisplayTexts().get(4));
				dtext4.setText(note.getDisplayTexts().get(9));
			}
			
		});
		
		opt4.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				typePanel.setBorder(new TitledBorder("Binary Recursion: Example"));
				defPanel.setBorder(new TitledBorder("Binary Recursion: Definiftion"));
				dtext3.setText(note.getDisplayTexts().get(5));
				dtext4.setText(note.getDisplayTexts().get(10));
			}
			
		});
		
		opt5.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				typePanel.setBorder(new TitledBorder("Nested Recursion: Example"));
				defPanel.setBorder(new TitledBorder("Nested Recursion: Definition"));
				dtext3.setText(note.getDisplayTexts().get(6));
				dtext4.setText(note.getDisplayTexts().get(11));
			}
			
		});
		
		frame.add(opt1);
		frame.add(opt2);
		frame.add(opt3);
		frame.add(opt4);
		frame.add(opt5);
		
		opt1.setBounds(275 + insets.left, 440 + insets.top, 150, 20);
		opt2.setBounds(275 + insets.left, 480 + insets.top, 150, 20);
		opt3.setBounds(275 + insets.left, 520 + insets.top, 150, 20);
		opt4.setBounds(275 + insets.left, 560 + insets.top, 150, 20);
		opt5.setBounds(275 + insets.left, 600 + insets.top, 150, 20);
		
		frame.setVisible(true);
		
		qpanel.setBackground(Color.lightGray);
		qpanel.setPreferredSize(new Dimension(200, 40));
		qpanel.setBorder(new EtchedBorder());
		frame.add(qpanel);
		frame.setVisible(true);
		
		qpanel.setBounds((frame.getSize().width - qpanel.getPreferredSize().width) + insets.left, (frame.getSize().height - 100) + insets.bottom, qpanel.getPreferredSize().width, qpanel.getPreferredSize().height);
		
		dtext2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				dtext2.setText(note.getDisplayTexts().get(1));	
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
	
	}

	@Override
	public void initNoteQuestionGUI() {
		return;
	}

	@Override
	public void initButtons() {
		// JButton qbutton = new JButton("See relevant quiz question");
		
		Random random = new Random();
		
		qbutton.addActionListener(new ViewNoteQuestionListener(note.getRelevantQuestions().get(random.nextInt(note.getRelevantQuestions().size()))));
		qbutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setExampleQuestionVisible(true);
			}
			
		});
		qpanel.add(qbutton);
		// frame.add(qbutton);
		frame.setVisible(true);
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
		
		// String note1 = "RecGen";
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
		item.addActionListener(new StartQuizListener(getMode(), new RecGenNoteGUI(note)));
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
		quizMenu.add(item);
		
		
		// create notes menu
		notesMenu = new JMenu("Notes");
		menuBar.add(notesMenu);
		
		item = new JMenuItem("Recursion - General");
		item.addActionListener(null);
		notesMenu.add(item);
		notesMenu.addSeparator();
		if (note.getNoteID().equals("recGenNoteID"))
			item.setEnabled(false);
		
		item = new JMenuItem("Copies Model");
		item.addActionListener(new ViewNoteListener(getMode(), note2, null));
		notesMenu.add(item);
		notesMenu.addSeparator();
		
		item = new JMenuItem("Fibonnaci Sequence (Iterative Approach)");
		item.addActionListener(new ViewNoteListener(getMode(), note3, null));
		notesMenu.add(item);
		if (note.getNoteID().equals("fibNoteID"))
			item.setEnabled(false);
		
		
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

	@Override
	public void removeElement(){
		frame.setVisible(false);
		frame.dispose();
	}
	
	@Override
	public void closeSection(){
		removeElement();
		System.exit(0);
	}

	@Override
	public void openSection() {
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
	
	
	// Methods below for unit testing purposes only
	
	
	public JTextArea getFactCallTracePanel(){
		return dtext2;
	}
	
	public String getFactCallTraceText(){
		return dtext2.getText();
	}
	
	public String getRecTypeExText(){
		return dtext3.getText();
	}
	
	public String getRecTypeDefText(){
		return dtext4.getText();
	}
	
	public JRadioButton getTailRecButton(){
		return opt2;
	}
	
	public JButton getSeeQuestionButton(){
		return qbutton;
	}
	
	public void setExampleQuestionVisible(boolean b){
		exampleQuetsionVisible = b;
	}
	
	public boolean isExampleQuestionVisible(){
		return exampleQuetsionVisible;
	}
	
}
