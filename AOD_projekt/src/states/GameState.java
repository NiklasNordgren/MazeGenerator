package states;

import java.awt.Graphics;

import entities.Player;
import environment.Maze;
import game.Game;

/**
 * The {@code GameState} class. Instansiates objects of the classes that belongs to the game state {@code State}.
 * 
 * @author Hanna Meden, Niklas Nordgren
 * @version 2019-01-06
 */
public class GameState extends State {
	
	private Player player;
	private Maze maze;
	
	private int mazeSize;
	
	/**
	 * Instantiates a new {@code GameState} object.
	 * 
	 * Sets this instance game object variable to the incoming {@code Game} object.
	 * 
	 * Initializes this instance mazeSize Integer variable to the value of the incoming Integer mazeSize.
	 *
	 * @param game the {@code Game} object
	 * @param mazeSize the width and height of the maze in number of cells
	 */
	public GameState(Game game, int mazeSize) {
		super();
		
		this.mazeSize = mazeSize;
		
		init(game);
	}
	
	/**
	 * Initializes the {@code Maze} object maze and the {@code Player} object player.
	 *
	 * @param game the {@code Game} object
	 */
	private void init(Game game) {
		maze = new Maze(mazeSize);
		player = new Player(game);
	}
	
	/* (non-Javadoc)
	 * @see states.State#update()
	 */
	@Override
	public void update() {
		
	}

	/* (non-Javadoc)
	 * @see states.State#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		maze.render(g);
		player.render(g);
	}
	
	/**
	 * Gets the {@code Player} object player.
	 *
	 * @return the {@code Player} object player
	 */
	public Player getPlayer() {
		return player;
	}

}
