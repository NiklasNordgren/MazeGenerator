import java.awt.Graphics;

public class Cell {

	public static final int CELLSIZE = 30;
	
	public int[] walls = {1, 1, 1, 1}; //N, S, W, E    1 = wall, 0 = no wall

	private int x, y;
	private int xPixels, yPixels;
	private boolean isVisited;
	private boolean isSolution;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.xPixels = x * Cell.CELLSIZE;
		this.yPixels = y * Cell.CELLSIZE;
	}

	public void drawWalls(Graphics g) {

		//North
		if(walls[0] == 1)
		g.drawLine(xPixels, yPixels, xPixels+CELLSIZE-3, yPixels);

		//South
		if(walls[1] == 1)
		g.drawLine(xPixels, yPixels + CELLSIZE, xPixels + CELLSIZE - 3, yPixels + CELLSIZE);

		//West
		if(walls[2] == 1)
		g.drawLine(xPixels, yPixels+3, xPixels, yPixels + CELLSIZE-3);

		//East
		if(walls[3] == 1)
		g.drawLine(xPixels + CELLSIZE , yPixels+3 , xPixels + CELLSIZE , yPixels + CELLSIZE-3);
	}
	
	public void fillCell(Graphics g) {
		g.fillRect(xPixels, yPixels, CELLSIZE, CELLSIZE);
	}
	
	public void fillCellGoal(Graphics g) {
		g.fillOval(xPixels+2, yPixels+2, CELLSIZE-5, CELLSIZE-5);
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

}
