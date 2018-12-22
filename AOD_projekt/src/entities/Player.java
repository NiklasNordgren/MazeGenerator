package entities;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import environment.Cell;
import environment.Maze;
import game.Display;
import game.Game;

public class Player {

	private Game game;
	private int playerSize = Cell.CELLSIZE - 5;
	private int x, y;
	private int speed = Cell.CELLSIZE;

	private Cell currentCell;

	private boolean goalIsFound;
	private boolean showSolution;

	public Player(Game game) {
		this.game = game;
		this.x = Maze.cells[0][0].getxPixels();
		this.y = Maze.cells[0][0].getyPixels();

		currentCell = Maze.cells[0][0];

	}

	public void update() {

	}

	public void render(Graphics g) {

		if(showSolution) {
			g.setColor(Color.MAGENTA);
			for(int x = 0; x < Maze.cells.length; x++)
				for(int y = 0; y < Maze.cells[x].length; y++) {
					if(Maze.cells[x][y].isSolution()) {

						Maze.cells[x][y].fillCell(g);
					}
				}
		}

		g.setColor(Color.darkGray);
		g.fillOval(x+2, y+2, playerSize, playerSize);


	}

	public void moveLeft() {

		if(x > 0 && currentCell.walls[2] != 1) {
			x -= speed;
			currentCell = Maze.cells[currentCell.getX()-1][currentCell.getY()];
		}

	}

	public void moveDown() {

		if(currentCell.walls[1] != 1) {
			y += speed;
			currentCell = Maze.cells[currentCell.getX()][currentCell.getY()+1];
			checkIfGoalIsReached();
		}

	}

	public void moveUp() {

		if(y > 0 && currentCell.walls[0] != 1) {
			y -= speed;
			currentCell = Maze.cells[currentCell.getX()][currentCell.getY()-1];
		}

	}

	public void moveRight() {

		if(currentCell.walls[3] != 1) {
			x += speed;
			currentCell = Maze.cells[currentCell.getX()+1][currentCell.getY()];
			checkIfGoalIsReached();
		}

	}

	public int getPlayerSize() {
		return playerSize;
	}

	public void showSolutionPath() {
		showSolution = !showSolution;
	}
	
	public void checkIfGoalIsReached() {
		if(currentCell == Maze.cells[Maze.cells.length-1][Maze.cells[0].length-1] && !goalIsFound) {
			System.out.println(currentCell.getX() + " " + currentCell.getY());
			goalIsFound = true;
			JDialog dialog = new JDialog();
			JOptionPane.showMessageDialog(dialog, "Winner winner chicken dinner!\nMaze completed in: " + game.getSeconds() + " s");
			game.resetGame();
		}	
	}

}