package states;
/**
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 * Keeps track of states.
 */
import java.awt.Graphics;

public abstract class State {

	private static State currentState = null;

	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}

	public abstract void update();
	public abstract void render(Graphics g);

}
