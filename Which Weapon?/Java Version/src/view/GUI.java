package view;

// import javax.imageio.ImageIO;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
// import java.awt.image.BufferedImage;
import java.util.List;
import java.util.*;

import model.*;

public class GUI implements IGUI {
	
	private JFrame frame;
	private int turnsTaken;
	private boolean isGameWon;
	private ArrayList<JPanel> panels;
	private ArrayList<JTextArea> texts;
	private Insets insets;
	private IWeaponBuilder weaponBuilder;
	private List<String> weapons;
	private AutoCompleteComboBox accb;
	private String targetWeapon;
	private JLabel status, guess1, guess2, guess3, guess4, guess5, guess6;
	private JPanel weaponDisplay;
	
	public GUI(){
		frame = new JFrame("R&C Weapon Wordle");
		turnsTaken = 0;
		isGameWon = false;
		panels = new ArrayList<>();
		texts = new ArrayList<>();
		insets = frame.getInsets();
		
		weaponBuilder = new WeaponBuilder();
		weaponBuilder.buildWeaponMap();
		weaponBuilder.buildPathMap();
		weapons = weaponBuilder.getWeapons();
		
		String[] wa = new String[weapons.size()];
		
		for (int i=0; i<weapons.size(); i++){
			wa[i] = weapons.get(i);
		}
		
		accb = new AutoCompleteComboBox(wa);
		
		Collections.shuffle(weapons);
		targetWeapon = weapons.get(0);
		
		status = new JLabel("Turn: " + (turnsTaken+1));
		
		guess1 = new JLabel("Guess: ...");
		guess2 = new JLabel("Guess: ...");
		guess3 = new JLabel("Guess: ...");
		guess4 = new JLabel("Guess: ...");
		guess5 = new JLabel("Guess: ...");
		guess6 = new JLabel("Guess: ...");
		
		weaponDisplay = new JPanel();
	}

	@Override
	public void initGUI() throws IOException{
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(945, 880);
		frame.setResizable(false);
		frame.setLocation(300, 20);
		frame.setVisible(true);
		frame.setLayout(null);
		
		initPanels();
		initInputs();
		initLabels();
		initMenuBar();
	}

	@Override
	public void initPanels() throws IOException {
		// JPanel weaponDisplay = new JPanel();
		weaponDisplay.setBackground(Color.white);
		weaponDisplay.setBorder(new EtchedBorder());
		weaponDisplay.setPreferredSize(new Dimension(410, 195));
		
//		BufferedImage testPic = ImageIO.read(new File(System.getProperty("user.home")+"/Desktop/R&CWW-pics/bomb-glove.jpg"));
//		JLabel picLabel = new JLabel(new ImageIcon(testPic));
//		weaponDisplay.add(picLabel);
		
		frame.add(weaponDisplay);
		frame.setVisible(true);
		
		weaponDisplay.setBounds(270 + insets.left, 40 + insets.top, weaponDisplay.getPreferredSize().width, weaponDisplay.getPreferredSize().height);
		
		for (int i=0; i<900; i+=150){
			for (int j=0; j<450; j+=75){
				JPanel panel = new JPanel();
				panels.add(panel);
				panel.setBackground(Color.white);
				panel.setPreferredSize(new Dimension(135, 40));
				panel.setBounds(25 + i + insets.left, 340 + j + insets.top, panel.getPreferredSize().width, panel.getPreferredSize().height);
				
				JTextArea text = new JTextArea();
				texts.add(text);
				text.setText("...");
				panel.add(text);
				text.setEditable(false);
				
				frame.add(panel);
			}
		}
	}
	
	private void loadWeaponImage(String weapon) throws IOException {
		BufferedImage testPic = ImageIO.read(new File(System.getProperty("user.home")+weaponBuilder.getPathMap().get(weapon)));
		Image scaledImage = testPic.getScaledInstance(weaponDisplay.getWidth(), weaponDisplay.getHeight(), Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
		picLabel.setSize(410, 200);
		picLabel.setBounds(weaponDisplay.getBounds());
		weaponDisplay.add(picLabel);
	}
	
	@Override
	public void initInputs(){
		accb.setPreferredSize(new Dimension(370, 40));
		accb.setBounds(190 + insets.left, 260 + insets.top, accb.getPreferredSize().width, accb.getPreferredSize().height);
		
		frame.add(accb);
		frame.setVisible(true);
		
		JButton submit = new JButton("Submit");
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					clickSubmit(accb.getSelectedItem().toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		submit.setPreferredSize(new Dimension(150, 40));
		
		frame.add(submit);
		frame.setVisible(true);
		
		submit.setBounds(590 + insets.left, 260 + insets.top, submit.getPreferredSize().width, submit.getPreferredSize().height);
	}
	
	@Override
	public void initLabels(){
		
		//Columns
		JLabel console = new JLabel("Console");
		console.setBounds(70+insets.left, 320+insets.top, 60, 20);
		frame.add(console);
		
		JLabel game = new JLabel("Game");
		game.setBounds(225+insets.left, 320+insets.top, 60, 20);
		frame.add(game);
		
		JLabel planet = new JLabel("Planet");
		planet.setBounds(370+insets.left, 320+insets.top, 60, 20);
		frame.add(planet);
		
		JLabel range = new JLabel("Range");
		range.setBounds(520+insets.left, 320+insets.top, 60, 20);
		frame.add(range);
		
		JLabel rof = new JLabel("Rate of Fire");
		rof.setBounds(660+insets.left, 320+insets.top, 80, 20);
		frame.add(rof);
		
		JLabel cost = new JLabel("Cost");
		cost.setBounds(830+insets.left, 320+insets.top, 60, 20);
		frame.add(cost);
		
		// Guesses
		guess1.setBounds(405+insets.left, 380+insets.top, 155, 20);
		frame.add(guess1);
		
		guess2.setBounds(405+insets.left, 455+insets.top, 155, 20);
		frame.add(guess2);
		
		guess3.setBounds(405+insets.left, 530+insets.top, 155, 20);
		frame.add(guess3);
		
		guess4.setBounds(405+insets.left, 605+insets.top, 155, 20);
		frame.add(guess4);
		
		guess5.setBounds(405+insets.left, 680+insets.top, 155, 20);
		frame.add(guess5);
		
		guess6.setBounds(405+insets.left, 755+insets.top, 155, 20);
		frame.add(guess6);
		
		// Status
		status.setBounds(450+insets.left, 795+insets.top, 80, 20);
		frame.add(status);
		frame.setVisible(true);
	}

	@Override
	public void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem item;
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		item = new JMenuItem("Refresh");
		item.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = showConfirmMessage("Are you sure you want to refresh the game?");
				if (result == 0){
					refresh();
				}
			}
			
		});
		
		menu.add(item);
		menu.addSeparator();
		
		item = new JMenuItem("Close");
		item.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = showConfirmMessage("Are you sure you want to close the game?");
				if (result == 0){
					close();
				}
			}
			
		});
		
		menu.add(item);
		
		frame.setJMenuBar(menuBar);
		frame.revalidate();
	}

	@Override
	public void refresh() {
		for (JPanel panel : panels){
			panel.setBackground(Color.white);
		}
		for (JTextArea text : texts){
			text.setText("...");
			text.setBackground(Color.white);
		}
		turnsTaken = 0;
		isGameWon = false;
		status.setText("Turn: 1");
		guess1.setText("Guess: ...");
		guess2.setText("Guess: ...");
		guess3.setText("Guess: ...");
		guess4.setText("Guess: ...");
		guess5.setText("Guess: ...");
		guess6.setText("Guess: ...");
		Collections.shuffle(weapons);
		targetWeapon = weapons.get(0);
		
		for (Component c : weaponDisplay.getComponents()){
			if (c instanceof JLabel){
				weaponDisplay.remove(c);
			}
		}
		weaponDisplay.revalidate();
		weaponDisplay.repaint();
	}

	@Override
	public void close() {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}

	@Override
	public boolean isOpen() {
		return frame.isVisible();
	}

	@Override
	public JFrame getFrame() {
		return frame;
	}
	
	public void clickSubmit(String weapon) throws IOException{
		List<String> weaponDetails = weaponBuilder.getWeaponMap().get(weapon);
		
		if (turnsTaken == 0){
			turnsTaken++;
			status.setText("Turn: 2");
			guess1.setText("Guess: " + accb.getSelectedItem());
			for (int i=0; i<weaponDetails.size(); i++){
				if (weaponBuilder.getWeaponMap().get(accb.getSelectedItem()).get(i).equals(weaponBuilder.getWeaponMap().get(targetWeapon).get(i))){
					panels.get(i*6).setBackground(Color.green);
					texts.get(i*6).setText(weaponDetails.get(i));
					texts.get(i*6).setBackground(Color.green);
				} else {
					panels.get(i*6).setBackground(Color.red);
					texts.get(i*6).setText(weaponDetails.get(i));
					texts.get(i*6).setBackground(Color.red);
				}
			}
			checkWin(accb.getSelectedItem().toString(), targetWeapon);
		} else if (turnsTaken == 1){
			turnsTaken++;
			status.setText("Turn: 3");
			guess2.setText("Guess: " + accb.getSelectedItem());
			for (int i=0; i<weaponDetails.size(); i++){
				if (weaponBuilder.getWeaponMap().get(accb.getSelectedItem()).get(i).equals(weaponBuilder.getWeaponMap().get(targetWeapon).get(i))){
					panels.get(i*6+1).setBackground(Color.green);
					texts.get(i*6+1).setText(weaponDetails.get(i));
					texts.get(i*6+1).setBackground(Color.green);
				} else {
					panels.get(i*6+1).setBackground(Color.red);
					texts.get(i*6+1).setText(weaponDetails.get(i));
					texts.get(i*6+1).setBackground(Color.red);
				}
			}
			checkWin(accb.getSelectedItem().toString(), targetWeapon);
		} else if (turnsTaken == 2){
			turnsTaken++;
			status.setText("Turn: 4");
			guess3.setText("Guess: " + accb.getSelectedItem());
			for (int i=0; i<weaponDetails.size(); i++){
				if (weaponBuilder.getWeaponMap().get(accb.getSelectedItem()).get(i).equals(weaponBuilder.getWeaponMap().get(targetWeapon).get(i))){
					panels.get(i*6+2).setBackground(Color.green);
					texts.get(i*6+2).setText(weaponDetails.get(i));
					texts.get(i*6+2).setBackground(Color.green);
				} else {
					panels.get(i*6+2).setBackground(Color.red);
					texts.get(i*6+2).setText(weaponDetails.get(i));
					texts.get(i*6+2).setBackground(Color.red);
				}
			}
			checkWin(accb.getSelectedItem().toString(), targetWeapon);
		} else if (turnsTaken == 3){
			turnsTaken++;
			status.setText("Turn: 5");
			guess4.setText("Guess: " + accb.getSelectedItem());
			for (int i=0; i<weaponDetails.size(); i++){
				if (weaponBuilder.getWeaponMap().get(accb.getSelectedItem()).get(i).equals(weaponBuilder.getWeaponMap().get(targetWeapon).get(i))){
					panels.get(i*6+3).setBackground(Color.green);
					texts.get(i*6+3).setText(weaponDetails.get(i));
					texts.get(i*6+3).setBackground(Color.green);
				} else {
					panels.get(i*6+3).setBackground(Color.red);
					texts.get(i*6+3).setText(weaponDetails.get(i));
					texts.get(i*6+3).setBackground(Color.red);
				}
			}
			checkWin(accb.getSelectedItem().toString(), targetWeapon);
		} else if (turnsTaken == 4){
			turnsTaken++;
			status.setText("Turn: 6");
			guess5.setText("Guess: " + accb.getSelectedItem());
			for (int i=0; i<weaponDetails.size(); i++){
				if (weaponBuilder.getWeaponMap().get(accb.getSelectedItem()).get(i).equals(weaponBuilder.getWeaponMap().get(targetWeapon).get(i))){
					panels.get(i*6+4).setBackground(Color.green);
					texts.get(i*6+4).setText(weaponDetails.get(i));
					texts.get(i*6+4).setBackground(Color.green);
				} else {
					panels.get(i*6+4).setBackground(Color.red);
					texts.get(i*6+4).setText(weaponDetails.get(i));
					texts.get(i*6+4).setBackground(Color.red);
				}
			}
			checkWin(accb.getSelectedItem().toString(), targetWeapon);
		} else {
			turnsTaken++;
			status.setText("Game Over!");
			guess6.setText("Guess: " + accb.getSelectedItem());
			for (int i=0; i<weaponDetails.size(); i++){
				if (weaponBuilder.getWeaponMap().get(accb.getSelectedItem()).get(i).equals(weaponBuilder.getWeaponMap().get(targetWeapon).get(i))){
					panels.get(i*6+5).setBackground(Color.green);
					texts.get(i*6+5).setText(weaponDetails.get(i));
					texts.get(i*6+5).setBackground(Color.green);
				} else {
					panels.get(i*6+5).setBackground(Color.red);
					texts.get(i*6+5).setText(weaponDetails.get(i));
					texts.get(i*6+5).setBackground(Color.red);
				}
			}
			loadWeaponImage(targetWeapon);
			checkWin(accb.getSelectedItem().toString(), targetWeapon);
		}
	}
	
	public void checkWin(String selWeapon, String tarWeapon) throws IOException {
		if ( weaponBuilder.getWeaponMap().get(selWeapon).get(0).equals(weaponBuilder.getWeaponMap().get(tarWeapon).get(0))
				&& weaponBuilder.getWeaponMap().get(selWeapon).get(1).equals(weaponBuilder.getWeaponMap().get(tarWeapon).get(1))
				&& weaponBuilder.getWeaponMap().get(selWeapon).get(2).equals(weaponBuilder.getWeaponMap().get(tarWeapon).get(2))
				&& weaponBuilder.getWeaponMap().get(selWeapon).get(3).equals(weaponBuilder.getWeaponMap().get(tarWeapon).get(3))
				&& weaponBuilder.getWeaponMap().get(selWeapon).get(4).equals(weaponBuilder.getWeaponMap().get(tarWeapon).get(4))
				&& weaponBuilder.getWeaponMap().get(selWeapon).get(5).equals(weaponBuilder.getWeaponMap().get(tarWeapon).get(5))){
			isGameWon = true;
			loadWeaponImage(targetWeapon);
			JOptionPane.showMessageDialog(null, "Winner! You got it in " + turnsTaken + " guesses.");
			refresh();
		} else {
			if (turnsTaken == 6){
				if (!isGameWon){
					JOptionPane.showMessageDialog(null, "Unlucky! Correct answer was: " + targetWeapon);
					refresh();
				}
			}
		}
	}
	
	private int showConfirmMessage(String message){
		JOptionPane jop = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		return jop.showConfirmDialog(null, message);
	}


}
