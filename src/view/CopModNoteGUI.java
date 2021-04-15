package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.*;
import model.Note;

public class CopModNoteGUI implements GUI {
	
	private JFrame frame;
	private JTextArea dtext2;
	private Note note;
	private Insets insets;
	
	public CopModNoteGUI(Note note) {
		frame = new JFrame("Notes - " + note.getNoteTitle());
		this.note = note;
		insets = frame.getInsets();
		dtext2 = new JTextArea();
	}

	@Override
	public void initGUI() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(805, 550);
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
		//bullet points
		JPanel bulletPanel = new JPanel();
		bulletPanel.setBackground(Color.lightGray);
		bulletPanel.setBorder(new EtchedBorder());
		bulletPanel.setPreferredSize(new Dimension(790, 180));
			
		JTextArea bulletText = new JTextArea();
		bulletText.setFont(new Font("Arial", Font.BOLD, 12));
		bulletText.setBackground(Color.lightGray);
		bulletText.setEditable(false);
		bulletPanel.add(bulletText);
		
		String bp = String.join("\n\n", note.getBulletPoints());
		bulletText.setText(bp);
		
		frame.add(bulletPanel);
		frame.setVisible(true);
		
		bulletPanel.setBounds(5 + insets.left, 5 + insets.top, bulletPanel.getPreferredSize().width, bulletPanel.getPreferredSize().height);
		
		JTextArea dtext1 = new JTextArea();
		dtext1.setText(note.getDisplayTexts().get(0));
		dtext1.setEditable(false);
		
		JPanel dpanel1 = new JPanel();
		JPanel dpanel2 = new JPanel();
		
		dpanel1.setLayout(new BorderLayout());
		dpanel1.setBackground(Color.white);
		dpanel1.setBorder(new TitledBorder("Example Algorithm"));
		dpanel1.setPreferredSize(new Dimension(320, 250));
		dpanel1.add(dtext1, BorderLayout.WEST);
		frame.add(dpanel1);
		frame.setVisible(true);
		
		dpanel1.setBounds(50 + insets.left, 190 + insets.top, dpanel1.getPreferredSize().width, dpanel1.getPreferredSize().height);
	
		dtext2.setText("Click panel to reveal call trace of Algorithm1 run on the\nlist: [4, 1, 3, 5]");
		dtext2.setEditable(false);
		dtext2.setPreferredSize(new Dimension(295, 180));
		
		dpanel2.setLayout(new BorderLayout());
		dpanel2.setBackground(Color.white);
		dpanel2.setBorder(new TitledBorder("Call trace of Algorithm1"));
		dpanel2.setPreferredSize(new Dimension(320, 250));
		dpanel2.add(dtext2, BorderLayout.WEST);
		frame.add(dpanel2);
		frame.setVisible(true);
		
		dpanel2.setBounds(430 + insets.left, 190 + insets.top, dpanel2.getPreferredSize().width, dpanel2.getPreferredSize().height);
		
		dtext2.addMouseListener(new MouseListener(){

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
		JButton qbutton = new JButton("See relevant quiz question");
		qbutton.setPreferredSize(new Dimension(200, 25));
		
		Random random = new Random();
		
		qbutton.addActionListener(new ViewNoteQuestionListener(note.getRelevantQuestions().get(random.nextInt(note.getRelevantQuestions().size()))));
		frame.add(qbutton);
		frame.setVisible(true);
		
		qbutton.setBounds(300 + insets.left, 450 + insets.top, qbutton.getPreferredSize().width, qbutton.getPreferredSize().height);
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
		// String note2 = "CopMod";
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
		item.addActionListener(new StartQuizListener(getMode(), new CopModNoteGUI(note)));
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
		item.addActionListener(null);
		notesMenu.add(item);
		notesMenu.addSeparator();
		if (note.getNoteID().equals("copModNoteID"))
			item.setEnabled(false);
		
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

}
