package gfx;
/**
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 * Maybe trying to get a picture in somewhere?
 */

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	} 

}
