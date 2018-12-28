package game;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import entities.Player;
import environment.Maze;
import input.KeyManager;
import states.GameState;
import states.MenuState;
import states.State;

public class Game implements Runnable {

	private Thread thread;
	private boolean running;

	private Display display;

	private KeyManager keyManager;

	private Maze maze;
	
	private Player player;

	private BufferStrategy bs;
	private Graphics g;
	private int seconds;

	private GameState gameState;
	private MenuState menuState;

	public Game() {
		
		display = new Display();
		
		init();

	}

	private void init() {

		gameState = new GameState(this);
		keyManager = new KeyManager(gameState.getPlayer());
		menuState = new MenuState(this);
		State.setState(gameState);
		//State.setState(menuState);

		display.getFrame().addKeyListener(keyManager);
	}

	private void update() {
		maze.update();

		if(State.getState() != null)
			State.getState().update();

	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear screen
		g.clearRect(0, 0, display.getWidth(), display.getHeight());

		
/*		//Draw
		maze.render(g);
		player.render(g); */

		//Draw

		if(State.getState() != null)
			State.getState().render(g);
		
		//End Drawing
		bs.show();
		g.dispose();
	}

	@Override
	public void run() {

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int updates = 0;

		seconds = 1;

		while(running) {

			now = System.nanoTime();
			timer += now - lastTime;
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if(delta >= 1) {
				update();
				render();

				updates++;
				delta--;
			}

			if(timer >= 1000000000) {
				//display.getFrame().setTitle("fps/updates: " + updates);

				display.getFrame().setTitle("Seconds: " + seconds);

				seconds++;

				updates = 0;
				timer = 0;
			}
		}

		stop();

	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return display.getWidth();
	}

	public int getHeight() {
		return display.getHeight();
	}
	
	public void resetGame() {
		display.getFrame().removeKeyListener(keyManager);
		init();
		seconds = 0;
	}
	
	public int getSeconds() {
		return seconds;
	}

	public GameState getGameState() {
		return gameState;
	}
}
