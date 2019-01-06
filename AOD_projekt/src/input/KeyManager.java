package input;
/**
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 * This class sets the hotkeys and what should happen if they are pressed.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entities.Player;

public class KeyManager implements KeyListener {
	
	private Player player;
	
	public KeyManager(Player player) {
		this.player = player;
	}
	
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

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}