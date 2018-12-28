package states;

import java.awt.Graphics;

import entities.Player;
import environment.Maze;
import game.Game;

public class GameState extends State {
	
	private Player player;
	private Maze maze;
	
	private int mazeSize;
	
	public GameState(Game game, int mazeSize) {
		super();
		
		this.mazeSize = mazeSize;
		
		init(game);
	}
	
	private void init(Game game) {
		maze = new Maze(mazeSize);
		player = new Player(game);
	}
	
	@Override
	public void update() {
		player.update();
	}

	@Override
	public void render(Graphics g) {
		maze.render(g);
		player.render(g);
	}
	
	public Player getPlayer() {
		return player;
	}

}
