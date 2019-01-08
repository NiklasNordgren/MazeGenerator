package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import entities.Player;
import input.KeyManager;
import states.GameState;
import states.State;

/**
 * The {@code Game} class. Instansiates objects of all the classes that are components of the maze game.
 * 
 * Implements the Runnable Interface, which enables the instances of the class to be executed by a thread.
 * 
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 */
public class Game implements Runnable {

	private Thread thread;
	private boolean running;
	public boolean dialogOpen;

	private Display display;

	private KeyManager keyManager;

	private BufferStrategy bs;
	private Graphics g;

	private GameState gameState;

	private int seconds, tenSecs;

	private int mazeSize = 7;

	/**
	 * Instantiates a new {@code Game} object.
	 * 
	 * Initializes objects used by the {@code Game} object to create the maze game.
	 * 
	 */
	public Game() {

		display = new Display(this);

		init(mazeSize);

	}

	/**
	 * Initializes the objects used by the {@code Game} object.
	 *
	 * @param mazeSize the width and height of the maze in number of cells
	 */
	private void init(int mazeSize) {

		gameState = new GameState(this, mazeSize);

		keyManager = new KeyManager(gameState.getPlayer());
		State.setState(gameState);

		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addKeyListener(keyManager);
	}

	/**
	 * This method is currently not being used. May be used to update variables of the {@code Game} class.
	 * This Method gets called upon 60 times per second.
	 * 
	 * Checks if the current state is not equal to null.
	 * If not, get the current state and update it.
	 */
	private void update() {

		if(State.getState() != null)
			State.getState().update();
	}

	/**
	 * Responsible for rendering all graphics of the maze game.
	 * Initializes the {@code BufferStrategy} object bs to the {@code BufferStrategy} provided by the {@code Display} Class.
	 * If a {@code BufferStrategy} does not currently exists, create a {@code BufferStrategy} containing 3 buffers.
	 * 
	 * If the current state does not equal null, get the current state and call its render method by passing the {@code Graphics} object g.
	 * 
	 * 
	 * @param g the Graphics object
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
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

			if(!dialogOpen) {
				if(minutes > 0)
					display.setTime("Time: " + minutes + " m " + seconds % 60 + "." + tenSecs + " s");
				else
					display.setTime("Time: "+ seconds + "."+ tenSecs  + " s");
			}
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

	/**
	 * Starts the game loop by setting the running boolean to true and initializing the {@code Thread} object thread 
	 * aswell as starting its execution. 
	 * Returns if running boolean value is true.
	 */
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stops the game loop by setting the running boolean to false and calling the {@code Thread} object thread's join method.
	 * Returns if the running boolean is false.
	 */
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
	
	/**
	 * Resets the game by calling the init method and resetting the seconds variable to 0.
	 */
	public void resetGame() {

		dialogOpen = false;
		display.getFrame().removeKeyListener(keyManager);
		display.getCanvas().removeKeyListener(keyManager);

		init(mazeSize);
		seconds = 0;
	}
	/**
	 * Gets the seconds.
	 *
	 * @return the seconds
	 */
	public String getSeconds() {
		return seconds + "." + tenSecs;
	}

	/**
	 * Gets the {@code GameState} object gameState.
	 *
	 * @return the game state
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	/**
	 * Gets the {@code Player} object player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return gameState.getPlayer();
	}

	/**
	 * Sets the maze width and height to the Integer parameter {@code mazeSize}.
	 *
	 * @param mazeSize the new maze size
	 */
	public void setMazeSize(int mazeSize) {
		this.mazeSize = mazeSize;
	}
}
