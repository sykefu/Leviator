package com.Leviator.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class MenuScreen implements Screen {
	
	public MenuScreen() {
		System.out.println("GameScreen Attached");
	}

	@Override
	public void render(float delta) {
		// Draws RGB color 10, 15, 230, at 100% opacity
		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		System.out.println("GameScreen - resizing");
	}

	@Override
	public void show() {
		System.out.println("GameScreen - show called");
	}

	@Override
	public void hide() {
		System.out.println("GameScreen - hide called");		
	}

	@Override
	public void pause() {
		System.out.println("GameScreen - pause called");		
	}

	@Override
	public void resume() {
		System.out.println("GameScreen - resume called");		
	}

	@Override
	public void dispose() {
		// Leave blank
	}

}
