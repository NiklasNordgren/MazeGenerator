package states;

import java.awt.Graphics;

/**
 * The abstract class {@code State}. Used to set and get the current state of the game. 
 * This program only uses one single state which is the {@code GameState}.
 * 
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 */
public abstract class State {

	private static State currentState = null;

	/**
	 * Sets the {@code State} object currentState to incoming {@code State} object parameter.
	 *
	 * @param state the new state
	 */
	public static void setState(State state) {
		currentState = state;
	}

	/**
	 * Gets the {@code State} object currentState.
	 *
	 * @return the currentState
	 */
	public static State getState() {
		return currentState;
	}

	/**
	 * Updates the variables of the {@code State} object that is set to this class currentState variable.
	 * This method gets called upon 60 times a second from the {@code Game} class if the current state is not equal to null.
	 */
	public abstract void update();
	
	/**
	 * Implicitly renders graphics to the {@code Display} class {@code Canvas} object using an {@code Graphics} object g.
	 * This method gets called upon 60 times a second from the {@code Game} class if the current state is not equal to null.
	 * 
	 * @param g the {@code Graphics} object
	 */
	public abstract void render(Graphics g);

}
