import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {

	private Game game;

	private Cell[][] cells;

	private Cell currentCell;
	private Cell nextCell;

	private Stack<Cell> stack;

	private ArrayList<Cell> neighbours;

	private Random random;
	private int counter;


	public Maze(Game game) {
		this.game = game;

		random = new Random();

		initializeCells();

		counter = 0;

		stack = new Stack<Cell>();

		int randomX = random.nextInt(cells.length);
		int randomY = random.nextInt(cells[0].length);

		createMaze(cells[randomX][randomY]);

	}

	private void initializeCells() {
		cells = new Cell[3][3];

		for(int x = 0; x < cells.length; x++)
			for(int y = 0; y < cells[x].length; y++)
				cells[x][y] = new Cell(x, y);
	}

	private void createMaze(Cell currentCell) {

		this.currentCell = currentCell;

		Cell nextCell;

		currentCell.setVisited(true);
		counter++;

		neighbours = findNeighbours(currentCell);

		stack.push(currentCell);

		if(neighbours.size() == 0) {
			nextCell = stack.pop();
			

		}else { 
			nextCell = selectRandomNeighbour(neighbours);

			removeWallBetween(currentCell, nextCell);

			

		}

		if(counter < 10)
			createMaze(nextCell);


	}
	private void removeWallBetween(Cell cc, Cell nc) {


		int ccX = cc.getX();
		int ccY = cc.getY();

		int ncX = nc.getX();
		int ncY = nc.getY();

		if(ccX - ncX == 1) {
			//Remove east wall
			cc.walls[3] = 0;
			nc.walls[2] = 0;
		}
		if(ccY - ncY == 1) {
			//Remove top wall
			cc.walls[0] = 0;
			nc.walls[1] = 0;

		}
		if(ccX - ncX == -1) {
			//Remove west wall
			cc.walls[2] = 0;
			nc.walls[3] = 0;
		}
		if(ccY - ncY == -1) {
			//Remove bottom wall
			cc.walls[1] = 0;
			nc.walls[0] = 0;
		}

	}

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

	private Cell selectRandomNeighbour(ArrayList<Cell> neighbours) {

		Cell randomCell = neighbours.get(random.nextInt(neighbours.size()));

		return randomCell;

	}


	public void update() {

	}



	public void render(Graphics g) {

		//Draw walls
		for(int x = 0; x < cells.length; x++)
			for(int y = 0; y < cells[x].length; y++)
				cells[x][y].drawWalls(g);

		
		for(int x = 0; x < cells.length; x++)
			for(int y = 0; y < cells[x].length; y++) {
				if(cells[x][y].getIsVisited()) {
					g.setColor(Color.RED);
					cells[x][y].fillCell(g);
				}


			}

		//Fill current
		g.setColor(Color.blue);
		//	currentCell.fillCell(g);

		//Fill next
		//g.setColor(Color.GREEN);
		//nextCell.fillCell(g);

		//Neighbour test
		/*
		for(int i = 0; i < neighbours.size(); i++) {
			neighbours.get(i).fillCell(g);
		}
		 */


	}

}
