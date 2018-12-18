import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Stack;

public class Game implements Runnable {

	private Thread thread;
	private boolean running;

	private Display display;
	private Player player;
	private KeyManager keyManager;
	
	private Maze maze;

	private BufferStrategy bs;
	private Graphics g;
	

	public Game() {

	}

	private void init() {
		display = new Display();
		player = new Player(0, 0);

		maze = new Maze(this);
	
		

	}

	private void update() {

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


		

		maze.render(g);
		//player.render(g);


		//End Drawing
		bs.show();
		g.dispose();
	}



	@Override
	public void run() {

		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int updates = 0;

		while(running) {

			now = System.nanoTime();
			timer += now - lastTime;
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if(delta >= 1) {
				update();
				render();

				updates ++;
				delta--;
			}

			if(timer >= 1000000000) {
				display.getFrame().setTitle("fps/updates: " + updates);
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

}
