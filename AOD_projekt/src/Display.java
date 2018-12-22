import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display {
	
	private JFrame frame;
	private Canvas canvas;
	private JPanel panel, panel2;
	private JButton button; 
	private int width = 800, height = 600;
	
	public Display() {
		
		Dimension dimension = new Dimension(width, height);
		button = new JButton("hello");
		panel = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		FlowLayout lay = new FlowLayout();
		
		frame = new JFrame("title");
		frame.setMinimumSize(dimension);
		frame.setMaximumSize(dimension);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(dimension);
		canvas.setMinimumSize(dimension);
		canvas.setMaximumSize(dimension);
		canvas.setFocusable(false);

		canvas.setBackground(Color.WHITE);
	
		panel2.add(canvas);
		panel.add(button);
	
		
		frame.add(panel);
		frame.add(panel2);
		
		frame.pack();
		
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
