package environment;
import java.awt.Graphics;

import game.Display;

/**
 * The {@code Cell} class. This classed is used by the {@code Maze} class to create a two dimensional Cell array which represents
 * the maze in the game.
 * 
 * Each Cell object has an Integer array containing four integers representing the four walls surrounding the cell object.
 * If the integer at a position has the value of 1, the wall exists, otherwise the wall doesn´t exist.
 * 
 * The integer at position [0] in the array represents the north wall.
 * The integer at position [1] in the array represents the south wall.
 * The integer at position [2] in the array represents the west wall. 
 * The integer at position [3] in the array represents the east wall.
 * 
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 */
public class Cell {

	public static final int CELLSIZE = 30;
	public static final int WALLSIZE = 5;

	public int[] walls = {1, 1, 1, 1}; //N, S, W, E    1 = wall

	private int x, y;
	private int xPixels, yPixels;

	private boolean isVisited;
	private boolean isSolution;

	/**
	 * Instantiates a new cell. Takes two Integers, x and y as parameter to determine the location of the Cell object in the two dimensional Cell array
	 * provided by the {@code Maze} class.
	 * 
	 * Sets the xPixel variable to determine the location of the top left position of the Cell object on the x-axis.
	 * Sets the yPixel variable to determine the location of the top left position of the Cell object on the y-axis.
	 * 
	 * @param x the x position
	 * @param y the y position
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.xPixels = x * Cell.CELLSIZE + Display.width / 2 - Maze.cells.length * CELLSIZE / 2;
		this.yPixels = y * Cell.CELLSIZE + Display.height / 2 - Maze.cells[0].length * CELLSIZE / 2;
	}

	/**
	 * Draws the walls of a {@code Cell} object using an {@code Graphics} object g.
	 * The walls to be drawn depend on the Integer array walls values.
	 * An Integer value of 1 indicates that a wall should be drawn at corresponding position.
	 *
	 * @param g the Graphics object
	 */
	public void drawWalls(Graphics g) {

		//North
		if(walls[0] == 1) {
			g.fillRect(xPixels, yPixels, CELLSIZE, WALLSIZE);
		}

		//South
		if(walls[1] == 1) {
			g.fillRect(xPixels, yPixels + CELLSIZE, CELLSIZE, WALLSIZE);
		}

		//West
		if(walls[2] == 1) {
			g.fillRect(xPixels, yPixels, WALLSIZE, CELLSIZE);
		}

		//East
		if(walls[3] == 1) {
			g.fillRect(xPixels + CELLSIZE, yPixels, WALLSIZE, CELLSIZE+WALLSIZE);
		}
	}

	/**
	 * Draws a filled rectangle at the Cell objets position.
	 *
	 * @param g the Graphics object
	 */
	public void fillCell(Graphics g) {
		g.fillRect(xPixels + WALLSIZE * 2, yPixels + WALLSIZE * 2, CELLSIZE - WALLSIZE * 3, CELLSIZE - WALLSIZE * 3);
	}

	/**
	 * Checks if the Cell has been visited or not by getting the isVisited boolean variable.
	 * This method is used in the {@code Maze} class to create the maze of the game.
	 * 
	 * @return the checks if is visited
	 */
	public boolean getIsVisited() {
		return isVisited;
	}

	/**
	 * Sets the isVisited boolean variable.
	 * This method is used in the {@code Maze} class to create the maze of the game.
	 *
	 * @param isVisited the new visited
	 */
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	/**
	 * Checks if the Cell is a part of the path that leads to the goal.
	 * 
	 * @return true, if it leads to the goal. false otherwise
	 */
	public boolean isSolution() {
		return isSolution;
	}

	/**
	 * Sets if the Cell is a part of the path that leads to the goal.
	 *
	 * @param isSolution the new boolean value
	 */
	public void setSolution(boolean isSolution) {
		this.isSolution = isSolution;
	}

	/**
	 * Gets the x position of the Cell object in the two dimensional Cell array provided by the {@code Maze} class.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the y position of the Cell object in the two dimensional Cell array provided by the {@code Maze} class.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets the x pixel position of the upper left corner of the Cell object.
	 *
	 * @return the x pixels
	 */
	public int getxPixels() {
		return xPixels;
	}

	/**
	 * Gets the y pixel position of the upper left corner of the Cell object.
	 *
	 * @return the y pixels
	 */
	public int getyPixels() {
		return yPixels;
	}

}
