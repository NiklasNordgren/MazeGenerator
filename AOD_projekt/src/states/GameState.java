package states;

import java.awt.Graphics;

import entities.Player;
import environment.Cell;
import environment.Maze;
import game.Display;
import game.Game;

public class GameState extends State {
	
	private Game game;
	
	private Player player;
	private Maze maze;
	
	public GameState(Game game) {
		super();
		init(game);
	}
	
	private void init(Game game) {
		this.game = game;
		maze = new Maze();
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
