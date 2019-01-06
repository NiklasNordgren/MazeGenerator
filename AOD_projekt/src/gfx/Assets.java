package gfx;
/**
 * @author Hanna Medén, Niklas Nordgren
 * @version 2019-01-06
 * I don't know what this is. Or what it does.
 */
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;

	public static BufferedImage[] btn_start;

	public static void init() {

		SpriteSheet menuSheet = new SpriteSheet(ImageLoader.loadImage("/menu.png"));
		
		btn_start = new BufferedImage[2];
		btn_start[0] = menuSheet.crop(0, 0, width*2, height);
		btn_start[1] = menuSheet.crop(width*2, 0, width*2, height);

	}

}
