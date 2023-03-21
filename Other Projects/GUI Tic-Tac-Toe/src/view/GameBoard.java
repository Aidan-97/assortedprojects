package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;

// import controller.*;

public class GameBoard implements GUI {
	
	private JFrame frame;
	private JLabel statusLabel;
	private int turnsTaken;
	private boolean isGameWon;
	private ArrayList<JPanel> panels;
	private HashMap<JPanel, String> gameMap;
	
	public GameBoard() {
		frame = new JFrame("Tic-Tac-Toe");
		statusLabel = new JLabel();
		turnsTaken = 0;
		isGameWon = false;
		panels = new ArrayList<>();
		gameMap = new HashMap<>();
	}

	@Override
	public void initGUI() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(540, 460);
		frame.setResizable(false);
		frame.setLocation(300, 20);
		frame.setVisible(true);
		frame.setLayout(new FlowLayout());
		
		initDisplay();
		initMenuBar();
	}

	@Override
	public void initDisplay() {
		for (int i=0; i<9; i++){
			JPanel boardSquare = new JPanel();
			boardSquare.setBackground(Color.white);
			boardSquare.setPreferredSize(new Dimension(170, 120));
			boardSquare.setEnabled(true);
			panels.add(boardSquare);
			gameMap.put(boardSquare, "");
			boardSquare.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) { 
					
					if (getTurn().equals("Next to play: O") && (boardSquare.isEnabled())){
						turnsTaken++;
						try {
							BufferedImage myPicture = ImageIO.read(new File("Green O.PNG"));
							JLabel picLabel = new JLabel(new ImageIcon(myPicture));
							boardSquare.add(picLabel);
							boardSquare.setEnabled(false);
							boardSquare.setBackground(Color.white);
						} catch (IOException e){
							e.printStackTrace();
						}
						
						gameMap.put(boardSquare, "O");
						
					}
					
					else if (getTurn().equals("Next to play: X") && (boardSquare.isEnabled())){
						turnsTaken++;
						try {
							BufferedImage myPicture = ImageIO.read(new File("Red X.PNG"));
							JLabel picLabel = new JLabel(new ImageIcon(myPicture));
							boardSquare.add(picLabel);
							boardSquare.setEnabled(false);
							boardSquare.setBackground(Color.white);
						} catch (IOException e){
							e.printStackTrace();
						}
						
						gameMap.put(boardSquare, "X");
					}
					
					else if ((getTurn().equals("Next to play: X") || getTurn().equals("Next to play: O")) && !(boardSquare.isEnabled())){
						JOptionPane.showMessageDialog(null, "This square has been taken already");
					}

					if (turnsTaken > 9)
						turnsTaken = 9;
					
					showStatus(getTurn());
					checkForWin();
					
					if (turnsTaken == 9 && !isGameWon){
						JOptionPane.showMessageDialog(null, "Tie!");
						isGameWon = false;
						clear();
					}
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					if (boardSquare.isEnabled())
						boardSquare.setBackground(Color.lightGray);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					if (boardSquare.isEnabled())
						boardSquare.setBackground(Color.white);
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			frame.add(boardSquare);
		}
		
		frame.add(statusLabel);
		showStatus(getTurn());
	}

	@Override
	public void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem item;
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		item = new JMenuItem("Clear");
		item.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = showConfirmMessage("Are you sure you want to clear the board and restart the game?");
				if (result == 0){
					clear();
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
	public void clear(){
		for (JPanel panel : panels){
			gameMap.put(panel, "");
			panel.removeAll();
			panel.setEnabled(true);
			panel.setBackground(Color.white);
		}
		turnsTaken = 0;
		isGameWon = false;
		showStatus(getTurn());
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
	
	public ArrayList<JPanel> getPanels(){
		return panels;
	}
	
	public String getTurn(){
		String turn = "";
		if (turnsTaken == 0 || (turnsTaken % 2 == 0 && turnsTaken < 9))
			turn = "Next to play: O";
		else if (turnsTaken == 1 || (turnsTaken % 2 != 0 && turnsTaken < 9))
			turn = "Next to play: X";
		else{
			turn = "Game finished";
		}
		return turn;
	}
	
	public void checkForWin(){
		if ((gameMap.get(panels.get(0)).equals(gameMap.get(panels.get(1))) && gameMap.get(panels.get(1)).equals(gameMap.get(panels.get(2))) && !(gameMap.get(panels.get(0)).equals("")))
				|| (gameMap.get(panels.get(0)).equals(gameMap.get(panels.get(3))) && gameMap.get(panels.get(3)).equals(gameMap.get(panels.get(6))) && !(gameMap.get(panels.get(0)).equals("")))
				|| (gameMap.get(panels.get(0)).equals(gameMap.get(panels.get(4))) && gameMap.get(panels.get(4)).equals(gameMap.get(panels.get(8))) && !(gameMap.get(panels.get(0)).equals("")))
				|| (gameMap.get(panels.get(1)).equals(gameMap.get(panels.get(4))) && gameMap.get(panels.get(4)).equals(gameMap.get(panels.get(7))) && !(gameMap.get(panels.get(1)).equals("")))
				|| (gameMap.get(panels.get(2)).equals(gameMap.get(panels.get(5))) && gameMap.get(panels.get(5)).equals(gameMap.get(panels.get(8))) && !(gameMap.get(panels.get(2)).equals("")))
				|| (gameMap.get(panels.get(2)).equals(gameMap.get(panels.get(4))) && gameMap.get(panels.get(4)).equals(gameMap.get(panels.get(6))) && !(gameMap.get(panels.get(2)).equals("")))
				|| (gameMap.get(panels.get(3)).equals(gameMap.get(panels.get(4))) && gameMap.get(panels.get(4)).equals(gameMap.get(panels.get(5))) && !(gameMap.get(panels.get(3)).equals("")))
				|| (gameMap.get(panels.get(6)).equals(gameMap.get(panels.get(7))) && gameMap.get(panels.get(7)).equals(gameMap.get(panels.get(8))) && !(gameMap.get(panels.get(6)).equals("")))){
			isGameWon = true;
			JOptionPane.showMessageDialog(null, "Winner!");
			clear();
		}
	}
	
	private void showStatus(String text){
		statusLabel.setText(text);
	}
	
	private int showConfirmMessage(String message){
		JOptionPane jop = new JOptionPane(message, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
		return jop.showConfirmDialog(null, message);
	}

}
