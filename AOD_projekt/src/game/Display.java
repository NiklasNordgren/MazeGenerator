package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display {
	
	private Game game;

	private JFrame frame;
	private Canvas canvas;
	
	private JButton buttonReset, buttonShowPath;
	private JPanel panel;

	public static int width = 1200, height = 900;

	public Display(Game game) {
		this.game = game;

		Dimension dimension = new Dimension(width, height);

		frame = new JFrame("title");
		frame.setMinimumSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.white);
		frame.setVisible(true);

		frame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		buttonReset = new JButton("Reset");
		buttonReset.setMnemonic(KeyEvent.VK_R);
		buttonReset.setFocusable(false);
		buttonReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.resetGame();
			}
		});
		
		buttonShowPath = new JButton("Show path");
		buttonShowPath.setMnemonic(KeyEvent.VK_S);
		buttonShowPath.setFocusable(false);
		buttonShowPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				game.getGameState().getPlayer().showSolutionPath();
			}
		});
		
		panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		panel.setFocusable(false);
		
		c.insets = new Insets(100, 100, 100, 100);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
	
		panel.add(buttonReset, c);
		
		c.gridy++;
		panel.add(buttonShowPath, c);

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

}
