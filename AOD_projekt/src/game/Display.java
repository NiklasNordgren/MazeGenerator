package game;
/**
 * @author Hanna Med�n, Niklas Nordgren
 * @version 2019-01-06
 * Here we create all the graphics to our game.
 */

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	private JLabel secondsLabel;

	private JButton buttonCreateMap, buttonReset, buttonShowPath;

	private JPanel panel;
	
	private Dimension dimensionButton;
	
	private JRadioButton radioButton1, radioButton2, radioButton3;

	public static int width = 1200, height = 900;

	public Display(Game game) {

		Dimension dimension = new Dimension(width, height);

		frame = new JFrame("A-MAZE-ing");
		frame.setMinimumSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.white);
		frame.setVisible(true);

		frame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		dimensionButton = new Dimension(140, 25);

		buttonReset = new JButton("Reset");
		buttonReset.setMnemonic(KeyEvent.VK_R);
		buttonReset.setFocusable(false);
		buttonReset.setPreferredSize(dimensionButton);
		buttonReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.resetGame();
			}
		});

		buttonShowPath = new JButton("Show path");
		buttonShowPath.setMnemonic(KeyEvent.VK_S);
		buttonShowPath.setFocusable(false);
		buttonShowPath.setPreferredSize(dimensionButton);
		buttonShowPath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getGameState().getPlayer().showSolutionPath();
			}
		});

		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		panel.setMaximumSize(new Dimension(width / 3, height));
		panel.setPreferredSize(new Dimension(width / 3, height));
		panel.setFocusable(false);

		c.insets = new Insets(10, 10, 10, 10);

		c.anchor = GridBagConstraints.CENTER;

		c.gridx = 0;
		c.gridy = 0;
		secondsLabel = new JLabel("Time: 0.0");
		secondsLabel.setFont(new Font("Arial",Font.BOLD, 55));
		
		panel.add(secondsLabel, c);
		c.gridy++;

		panel.add(buttonReset, c);

		c.gridy++;
		panel.add(buttonShowPath, c);

		radioButton1 = new JRadioButton("27 x 27");
		radioButton1.setBackground(Color.WHITE);
		radioButton1.setActionCommand("27 x 27");
		radioButton1.setFocusable(false);
		
		radioButton2 = new JRadioButton("17 x 17");
		radioButton2.setBackground(Color.WHITE);
		radioButton2.setActionCommand("17 x 17");
		radioButton2.setFocusable(false);
		
		radioButton3 = new JRadioButton("7 x 7");
		radioButton3.setBackground(Color.WHITE);
		radioButton3.setActionCommand("7 x 7");
		radioButton3.setFocusable(false);

		ButtonGroup group = new ButtonGroup();
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton3);

		c.gridy++;
		panel.add(new JLabel("Maze size:"), c);

		c.gridy++;
		panel.add(radioButton1, c);

		c.gridy++;
		panel.add(radioButton2, c);

		c.gridy++;
		panel.add(radioButton3, c);

		buttonCreateMap = new JButton("Create new maze");
		buttonCreateMap.setFocusable(false);
		buttonCreateMap.setPreferredSize(dimensionButton);
		buttonCreateMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String actionCommand = group.getSelection().getActionCommand();
			
				if(actionCommand.equals("27 x 27")) {
					game.setMazeSize(27);
					game.resetGame();
				}	
				else if(actionCommand.equals("17 x 17")) {
					game.setMazeSize(17);
					game.resetGame();
				}
				else if(actionCommand.equals("7 x 7")) {
					game.setMazeSize(7);
					game.resetGame();
				}
			}
		});

		c.gridy++;
		panel.add(buttonCreateMap, c);

		canvas = new Canvas();
		canvas.setPreferredSize(dimension);
		canvas.setMinimumSize(dimension);
		canvas.setMaximumSize(dimension);
		canvas.setFocusable(false);
		canvas.setBackground(Color.WHITE);

		c.insets = new Insets(0, 0, 0, 0);

		c.gridx = 0;
		c.gridy = 0;
		frame.add(canvas, c);

		c.gridx++;
		frame.add(panel, c);


		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public JFrame getFrame() {
		return frame;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public void setTime(String text) {
		secondsLabel.setText(text);
	}
}
