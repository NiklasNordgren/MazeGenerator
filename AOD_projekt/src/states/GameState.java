package states;

import java.awt.Graphics;

import entities.Player;
import environment.Maze;
import game.Game;

public class GameState extends State {

	private Player player;
	private Maze maze;
	
	public GameState(Game game) {
		super();
		init(game);
	}
	
	private void init(Game game) {
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
