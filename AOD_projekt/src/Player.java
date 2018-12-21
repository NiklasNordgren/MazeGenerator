import java.awt.Color;
import java.awt.Graphics;

public class Player {

	private Game game;
	private int size = 30;
	private int xPos, yPos;

	public Player(Game game, int xPos, int yPos) {
		this.game = game;
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void update() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(xPos, yPos, size, size);
	}

	public void moveLeft() {
		
		if(xPos > 0 && findCurrentCell().walls[2] != 1) {
			xPos -= 30;
			findCurrentCell();
		}
			
	}

	public void moveDown() {
		
		if(yPos + 30 < game.getHeight() && findCurrentCell().walls[1] != 1) {
			yPos += 30;
			findCurrentCell();
		}
			
	}

	public void moveUp() {
		
		if(yPos > 0 && findCurrentCell().walls[0] != 1) {
			yPos -= 30;
			findCurrentCell();
		}
			
	}

	public void moveRight() {
		
		if(xPos + 30 < game.getWidth() && findCurrentCell().walls[3] != 1) {
			xPos += 30;
			findCurrentCell();
		}
			
	}
	
	public Cell findCurrentCell() {
		
		Cell currentCell = Maze.cells[xPos/30][yPos/30];
		
		return currentCell;
	}

}
