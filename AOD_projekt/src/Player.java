import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	private int size = 30;
	private int xPos, yPos;
	
	public Player(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(xPos, yPos, size, size);
	}

	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	public void moveDown() {
		// TODO Auto-generated method stub
		
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		
	}

	public void moveRight() {
		// TODO Auto-generated method stub
		
	}

}
