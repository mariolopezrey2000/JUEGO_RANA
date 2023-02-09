package com.mygdx.juego;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.juego.enums.GameState;
import com.mygdx.juego.escenas.MainMenuScreen;
import com.mygdx.juego.utils.Constantes;
import com.mygdx.juego.utils.Pantallas;

public class JUMPCROA extends Game {
	public OrthographicCamera camera;
	public GameState state = GameState.TO_START;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constantes.APP_WIDTH, Constantes.APP_HEIGHT);
		Pantallas.mainMenuScreen = new MainMenuScreen(this);
		setScreen(Pantallas.mainMenuScreen);
	}

	@Override
	public void dispose() {

	}


}
