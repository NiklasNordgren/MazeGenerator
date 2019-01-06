package game;
import java.util.Random;

public class Launcher {
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.start();			
	}
	public int randomGen(long seed) {
		Random gen = new Random(seed);
		int num = gen.nextInt();
		
		return num;

	}

}
