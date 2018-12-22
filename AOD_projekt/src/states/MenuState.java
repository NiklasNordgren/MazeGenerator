package states;

import java.awt.Graphics;

import game.Game;

public class MenuState extends State {
	
	private Game game;
	
	public MenuState(Game game) {
		this.game = game;
	}
	
	private void init() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		g.drawRect(100, 100, 100, 100);
	}

}
