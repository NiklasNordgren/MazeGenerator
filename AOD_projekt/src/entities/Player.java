package entities;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import environment.Cell;
import environment.Maze;
import game.Game;

/**
 * The {@code Player} class. Paints the player represented as a filled dark gray circle to the screen.
 * Contains methods to move the player on the screen, check if the goal cell is reached, show the path
 * that leads to the goal cell and reset the game.
 * 
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 */
public class Player {

	private Game game;
	
	private Cell currentCell;

	private int playerSize = Cell.CELLSIZE - Cell.WALLSIZE - 1;
	private int x, y;
	private int speed = Cell.CELLSIZE;

	private boolean cheat = false;
	private boolean showSolution;

	/**
	 * Instantiates a new player object.
	 * 
	 * Sets this instance game object variable to the parameter {@code game} object.
	 * 
	 * Initializes the top left x and y coordinates where the player is to be displayed onto the
	 * {@code Canvas} object provided by the {@code Display} class.
	 * 
	 * Initializes the currentCell object variable to the first {@code Cell} 
	 * in the two dimensional {@code Cell} array provided by the {@code Maze} class.
	 * 
	 * @param game the {@code Game} object
	 */
	public Player(Game game) {
		this.game = game;
		this.x = Maze.cells[0][0].getxPixels() + Cell.WALLSIZE;
		this.y = Maze.cells[0][0].getyPixels() + Cell.WALLSIZE;

		currentCell = Maze.cells[0][0];
	}

	/**
	 * This method is currently not being used. May be used to update variables of the {@code Player} class.
	 * Update gets called upon 60 times per second by the {@code Game} class implicitly through the {@code GameState} class.
	 */
	public void update() {

	}

	/**
	 * Responsible for rendering graphics related to the {@code Player} class implicitly to the {@code Canvas} object
	 * provided by the {@code Display} class through the {@code GameState} class.
	 * 
	 * @param g the Graphics object
	 */
	public void render(Graphics g) {

		if(showSolution) {
			g.setColor(Color.MAGENTA);
			for(int x = 0; x < Maze.cells.length; x++) {
				for(int y = 0; y < Maze.cells[x].length; y++) {
					try {
						if(Maze.cells[x][y].isSolution()) 
							Maze.cells[x][y].fillCell(g);

					}catch(NullPointerException e) {

					}
				}
			}
		}
		g.setColor(Color.darkGray);
		g.fillOval(x, y, playerSize, playerSize);

	}

	/**
	 * Moves the player one cell to the left.
	 */
	public void moveLeft() {

		if(x > 0 && currentCell.walls[2] != 1) {
			x -= speed;
			currentCell = Maze.cells[currentCell.getX()-1][currentCell.getY()];
		}
	}

	/**
	 * Moves the player one cell down.
	 */
	public void moveDown() {

		if(currentCell.walls[1] != 1) {
			y += speed;
			currentCell = Maze.cells[currentCell.getX()][currentCell.getY()+1];
			checkIfGoalIsReached();
		}
	}

	/**
	 * * Moves the player one cell up.
	 */
	public void moveUp() {

		if(y > 0 && currentCell.walls[0] != 1) {
			y -= speed;
			currentCell = Maze.cells[currentCell.getX()][currentCell.getY()-1];
		}
	}
	/**
	 * Moves the player one cell to the right.
	 */
	public void moveRight() {

		if(currentCell.walls[3] != 1) {
			x += speed;
			currentCell = Maze.cells[currentCell.getX()+1][currentCell.getY()];
			checkIfGoalIsReached();
		}
	}

	/**
	 * Gets the player size.
	 *
	 * @return the player size
	 */
	public int getPlayerSize() {
		return playerSize;
	}

	/**
	 * Sets the cheat {@code boolean} variable to true.
	 * Toggles the showSolution {@code boolean} variable.
	 */
	public void showSolutionPath() {
		cheat = true;
		showSolution = !showSolution;
	}

	/**
	 * Checks if the goal is reached.
	 * 
	 * If the player represented by a dark gray circle has reached the last Cell in the two dimensional Cell array provided by the {@code Maze} class,
	 * a {@code JDialog} is displayed, containing information to the user.
	 * 
	 */
	public void checkIfGoalIsReached() {

		if(currentCell == Maze.cells[Maze.cells.length-1][Maze.cells[0].length-1]) {

			game.dialogOpen = true;
			JDialog dialog = new JDialog();
			
			if(cheat)
				JOptionPane.showMessageDialog(dialog, "You've reached the goal but you've achieved this through cheating. \nMaze completed in: " + game.getSeconds() + " s");
			else
				JOptionPane.showMessageDialog(dialog, "Congratulations! You've done it! \nMaze completed in: " + game.getSeconds() + " s");
			game.resetGame();
		}	
	}

	/**
	 * Resets the game implicitly by calling the resetGame method provided by the {@code Game} class by using the game object.
	 */
	public void resetGame() {
		game.resetGame();
	}
}