package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

	private BufferedImage[] images;
	private ClickListener clickListener;
	
	public UIImageButton(int x, int y, int width, int height, BufferedImage[] images, ClickListener clickListener) {
		super(x, y, width, height);
		this.images = images;
		this.clickListener = clickListener;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[1], x, y, width, height, null);
		else
			g.drawImage(images[0], x, y, width, height, null);
	}

	@Override
	public void onClick() {
		clickListener.onClick();
	}
	
}
