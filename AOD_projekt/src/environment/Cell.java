package environment;
/**
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 * The maze is built of Cells, this decides the size and whereabouts 
 * of the cells and the walls.
 */
import java.awt.Graphics;

import game.Display;

public class Cell {

	public static final int CELLSIZE = 30;
	public static final int WALLSIZE = 5;

	public int[] walls = {1, 1, 1, 1}; //N, S, W, E    1 = wall, 0 = no wall

	private int x, y;
	private int xPixels, yPixels;
	private boolean isVisited;
	private boolean isSolution;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.xPixels = x * Cell.CELLSIZE + Display.width / 2 - Maze.cells.length * CELLSIZE / 2;
		this.yPixels = y * Cell.CELLSIZE + Display.height / 2 - Maze.cells[0].length * CELLSIZE / 2;
	}

	public void drawWalls(Graphics g) {

		//North
		if(walls[0] == 1) {
			//g.drawLine(xPixels, yPixels, xPixels+CELLSIZE, yPixels);
			g.fillRect(xPixels, yPixels, CELLSIZE, WALLSIZE);
		}

		//South
		if(walls[1] == 1) {
			//g.drawLine(xPixels, yPixels + CELLSIZE, xPixels + CELLSIZE, yPixels + CELLSIZE);
			g.fillRect(xPixels, yPixels + CELLSIZE, CELLSIZE, WALLSIZE);
		}

		//West
		if(walls[2] == 1) {
			//g.drawLine(xPixels, yPixels, xPixels, yPixels + CELLSIZE);
			g.fillRect(xPixels, yPixels, WALLSIZE, CELLSIZE);
		}

		//East
		if(walls[3] == 1) {
			//g.drawLine(xPixels + CELLSIZE , yPixels, xPixels + CELLSIZE , yPixels + CELLSIZE);
			g.fillRect(xPixels + CELLSIZE, yPixels, WALLSIZE, CELLSIZE+WALLSIZE);
		}
	}

	public void fillCell(Graphics g) {
		g.fillRect(xPixels + WALLSIZE * 2, yPixels + WALLSIZE * 2, CELLSIZE - WALLSIZE * 3, CELLSIZE - WALLSIZE * 3);
	}

	public boolean getIsVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public boolean isSolution() {
		return isSolution;
	}

	public void setSolution(boolean isSolution) {
		this.isSolution = isSolution;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getxPixels() {
		return xPixels;
	}

	public int getyPixels() {
		return yPixels;
	}

}
