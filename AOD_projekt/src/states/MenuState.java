package states;

import java.awt.Graphics;
import java.util.ArrayList;

import game.Display;
import game.Game;
import gfx.Assets;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIObject;

public class MenuState extends State {

	private Game game;
	private ArrayList<UIObject> uiObjects;

	public MenuState(Game game) {
		this.game = game;
		init();
	}

	private void init() {
		uiObjects = new ArrayList<>();

		uiObjects.add(new UIImageButton(Display.width / 2, Display.height / 2, 64, 32, Assets.btn_start, new ClickListener() {

			@Override
			public void onClick() {
				State.setState(game.getGameState());
			}
		}));


	}

	@Override
	public void update() {
		for(UIObject o : uiObjects)
			o.update();
	}

	@Override
	public void render(Graphics g) {
		for(UIObject o : uiObjects)
			o.render(g);
	}

}
