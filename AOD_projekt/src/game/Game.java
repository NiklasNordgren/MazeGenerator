package game;
/**
 * @author Hanna Med�n, Niklas Nordgren
 * @version 2019-01-06
 *This class holds the entire game, this is where it starts and 
 *it keeps track on the counter for the game. 
 */
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import entities.Player;
import input.KeyManager;
import states.GameState;
import states.State;

public class Game implements Runnable {

	private Thread thread;
	private boolean running;

	private Display display;

	private KeyManager keyManager;

	private BufferStrategy bs;
	private Graphics g;

	private GameState gameState;
	
	private int seconds, tenSecs;

	private int mazeSize = 27;

	public Game() {
		
		display = new Display(this);
		
		init(mazeSize);

	}

	private void init(int mazeSize) {

		gameState = new GameState(this, mazeSize);
		keyManager = new KeyManager(gameState.getPlayer());
		State.setState(gameState);

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
	
		seconds = 0;

		while(running) {

			now = System.nanoTime();
			timer += now - lastTime;
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			tenSecs = (int) (timer / 100000000);
			int minutes = seconds/60;
			
			if(minutes > 0) {
				display.setTime("Time: " + minutes + " m " + seconds % 60 + "." + tenSecs + " s");
			}else
				display.setTime("Time: "+ seconds + "."+ tenSecs  + " s");

			if(delta >= 1) {
				update();
				render();

				delta--;
			}

			if(timer >= 1000000000) {

				seconds++;

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

	public void setMazeSize(int mazeSize) {
		this.mazeSize = mazeSize;
	}
}
