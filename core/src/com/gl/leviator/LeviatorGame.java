package com.gl.leviator;

import com.gl.leviator.Screen.MenuScreen;
import com.badlogic.gdx.Game;

public class LeviatorGame extends Game {
	
	@Override
	public void create () {
		System.out.println("LeviatorGame Created!");
		setScreen(new MenuScreen());
	}

}