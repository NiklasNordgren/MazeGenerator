package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import entities.Player;
import gfx.Assets;
import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.MenuState;
import states.State;

public class Game implements Runnable {

	private Thread thread;
	private boolean running;

	private Display display;

	private KeyManager keyManager;
	private MouseManager mouseManager;

	private BufferStrategy bs;
	private Graphics g;

	private GameState gameState;
	private MenuState menuState;
	
	private int seconds;
	private int mazeSize = 27;

	public Game() {
		
		display = new Display(this);
		
		init(27);

	}

	private void init(int mazeSize) {
		
		menuState = new MenuState(this);
		gameState = new GameState(this, mazeSize);
		
		keyManager = new KeyManager(gameState.getPlayer());
		//mouseManager = new MouseManager();		
		
		State.setState(gameState);
		//State.setState(menuState);
		
		//display.getCanvas().addMouseMotionListener(mouseManager);
		//display.getCanvas().addMouseListener(mouseManager);
		
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addKeyListener(keyManager);
	}

	private void update() {

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
	
	public void resetGame() {
		display.getFrame().removeKeyListener(keyManager);
		display.getCanvas().removeKeyListener(keyManager);
		
		init(mazeSize);
		seconds = 0;
	}
	
	public int getSeconds() {
		return seconds;
	}

	public GameState getGameState() {
		return gameState;
	}
	
	public Player getPlayer() {
		return gameState.getPlayer();
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public void setMazeSize(int mazeSize) {
		this.mazeSize = mazeSize;
	}

}
