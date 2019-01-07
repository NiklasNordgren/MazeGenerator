package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Player;

/**
 * The {@code KeyManager} class. Implements the KeyListener Interface in order to override its methods.
 * This class is used by the maze game to convert input from the user to logical game actions. Such as player
 * movement etc.
 * 
 * @author Hanna Meden, Niklas Nordgren
 * @version 2019-01-06
 */
public class KeyManager implements KeyListener {
	
	private Player player;
	
	/**
	 * Instantiates a new {@code KeyManager} object.
	 * 
	 * Sets this instance player object variable to the incoming {@code Player} object.
	 *
	 * @param player the {@code Player} object
	 */
	public KeyManager(Player player) {
		this.player = player;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
			player.moveLeft();
		if(e.getKeyCode() == KeyEvent.VK_RIGHT|| e.getKeyCode() == KeyEvent.VK_D)
			player.moveRight();
		if(e.getKeyCode() == KeyEvent.VK_DOWN|| e.getKeyCode() == KeyEvent.VK_S)
			player.moveDown();
		if(e.getKeyCode() == KeyEvent.VK_UP|| e.getKeyCode() == KeyEvent.VK_W)
			player.moveUp();
		if(e.getKeyCode() == KeyEvent.VK_C)
			player.showSolutionPath();
		if(e.getKeyCode() == KeyEvent.VK_R)
			player.resetGame();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	
	}
}