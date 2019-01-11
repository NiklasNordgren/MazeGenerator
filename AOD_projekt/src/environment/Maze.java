package environment;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * 
 * The {@code Maze} class. Creates the maze, finds the path through the maze, and is also able to draw the path onto the screen.
 * The algorithm used to create the maze is called recursive backtracking.
 * 
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 */
public class Maze {

	public static Cell[][] cells;

	private Cell nextCell;
	private Cell startCell;
	private Cell goalCell;

	private Stack<Cell> stack;

	private ArrayList<Cell> neighbours;

	private Random random;

	private int mazeWidth;
	private int mazeHeight;

	private boolean goalIsFound;

	/**
	 * Instantiates a new {@code Maze} object.
	 * 
	 * Initializes the Integer variables {@code mazeWidth} and {@code mazeHeight} to the value of the {@code size} parameter.
	 *
	 * @param size the width and height two dimensional Cell array created within the {@code Maze} object
	 */
	public Maze(int size) {

		mazeWidth = size;
		mazeHeight = size;

		init();

	}

	/**
	 * Initializes the objects used by the {@code Maze} object.
	 */
	private void init() {

		random = new Random();

		initializeCells();

		stack = new Stack<Cell>();

		goalCell = cells[cells.length-1][cells[0].length-1];

		startCell = cells[0][0];

		createMaze(goalCell);
	}

	/**
	 * Initialize the two dimensional {@code Cell} array cells.
	 */
	private void initializeCells() {

		cells = new Cell[mazeWidth][mazeHeight];

		for(int x = 0; x < cells.length; x++)
			for(int y = 0; y < cells[x].length; y++) 
				cells[x][y] = new Cell(x, y);

	}

	/**
	 * Creates the maze using the recursive backtracking algorithm.
	 *
	 * @param currentCell the current cell
	 */
	private void createMaze(Cell currentCell) {

		if(isAllVisited())
			return;
		if(currentCell.equals(startCell) && !goalIsFound)
			goalIsFound = true;

		currentCell.setVisited(true);
		neighbours = findNeighbours(currentCell);

		if(neighbours.size() == 0) {
			nextCell = stack.pop();
			if(!goalIsFound)
				currentCell.setSolution(false);
		}else {
			nextCell = selectRandomNeighbour(neighbours);
			if(!goalIsFound)
				currentCell.setSolution(true);

			stack.push(currentCell);

			removeWallBetween(currentCell, nextCell);
		}

		createMaze(nextCell);

	}

	/**
	 * Checks if is all {@code Cell} objects in the two dimensional {@code Cell} array cells has been visited.
	 *
	 * @return true, if is all are visited, false otherwise
	 */
	private boolean isAllVisited() {
		for(int x = 0; x < cells.length; x++)
			for(int y = 0; y < cells[x].length; y++)
				if(cells[x][y].getIsVisited() == false)
					return false;
		return true;
	}


	/**
	 * Removes the wall between two {@code Cell} objects.
	 *
	 * @param currentCell
	 * @param nextCell
	 */
	private void removeWallBetween(Cell currentCell, Cell nextCell) {

		int currentCellX = currentCell.getX();
		int currentCellY = currentCell.getY();

		int nextCellX = nextCell.getX();
		int nextCellY = nextCell.getY();

		if(currentCellY - nextCellY == 1) {
			currentCell.walls[0] = 0;
			nextCell.walls[1] = 0;
		}

		if(currentCellY - nextCellY == -1) {
			currentCell.walls[1] = 0;
			nextCell.walls[0] = 0;
		}

		if(currentCellX - nextCellX == 1) {
			currentCell.walls[2] = 0;
			nextCell.walls[3] = 0;
		}

		if(currentCellX - nextCellX == -1) {
			currentCell.walls[3] = 0;
			nextCell.walls[2] = 0;
		}
	}

	/**
	 * Finds the neighbours of the current cell that are not visited or out of bounds and adds them to an {@code ArrayList} holding 
	 * {@code Cell} objects.
	 * 
	 * @param currentCell
	 * @return the array list of {@code Cell} objects 
	 */
	private ArrayList<Cell> findNeighbours(Cell currentCell) {

		ArrayList<Cell> neighbours = new ArrayList<Cell>();

		if(!(currentCell.getY() == 0)) {
			Cell up = cells[currentCell.getX()][currentCell.getY() - 1];
			if(!up.getIsVisited())
				neighbours.add(up);
		}

		if(!(currentCell.getX() == 0)) {
			Cell west = cells[currentCell.getX() - 1][currentCell.getY()];
			if(!west.getIsVisited())
				neighbours.add(west);
		}

		if(!(currentCell.getY() == cells[0].length - 1)) {
			Cell down = cells[currentCell.getX()][currentCell.getY() + 1];
			if(!down.getIsVisited())
				neighbours.add(down);
		}

		if(!(currentCell.getX() == cells.length - 1)) {
			Cell east = cells[currentCell.getX() + 1][currentCell.getY()];
			if(!east.getIsVisited())
				neighbours.add(east);
		}
		return neighbours;
	}

	/**
	 * Selects a random neighbour from the incoming neighbours {@code ArrayList} holding 
	 * {@code Cell} objects.
	 *
	 * @param neighbours the neighbours of the current cell
	 * @return a random cell chosen from the neighbours {@code ArrayList}
	 */
	private Cell selectRandomNeighbour(ArrayList<Cell> neighbours) {

		Cell randomCell = neighbours.get(random.nextInt(neighbours.size()));

		return randomCell;

	}

	/**
	 * This method is currently not being used. May be used to update variables of the {@code Maze} class.
	 * This Method gets called upon 60 times per second by the Game class implicitly.
	 */
	public void update() {

	}

	/**
	 * Is responsible for rendering graphics related to the {@code Maze} class implicitly to the {@code Canvas} object
	 * provided by the {@code Display} class. Draws the walls of each {@code Cell} object in the two dimensional array cells.
	 * Aswell as the start- and goal cell of the maze.
	 * 
	 * @param g the Graphics object
	 */
	public void render(Graphics g) {

		try {
			//Draw walls
			for(int x = 0; x < cells.length; x++)
				for(int y = 0; y < cells[x].length; y++) {

					try {
						cells[x][y].drawWalls(g);
					}catch(NullPointerException e) {

					}
				}
		}

		catch(ArrayIndexOutOfBoundsException e) {

		}

		//Fill goal
		g.setColor(Color.GREEN);
		goalCell.fillCell(g);
	}

}
