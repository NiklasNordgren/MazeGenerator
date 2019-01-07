package states;

import java.awt.Graphics;

/**
 * The abstract class {@code State}. Used to set and get the current state of the game. 
 * This program only uses one single state which is the {@code GameState}.
 * 
 * 
 * @author Hanna Meden, Niklas Nordgren
 * @version 2019-01-06
 */
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
