package com.vladwild.chest.with.gems.gamestarter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vladwild.chest.with.gems.screens.Start;

public class ChestWithGems extends Game {
	private SpriteBatch spriteBatch;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		this.setScreen(new Start(this));
	}

	public SpriteBatch getSpriteBatch(){
		return spriteBatch;
	}
}
