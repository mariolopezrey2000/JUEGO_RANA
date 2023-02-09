package com.mygdx.juego.escenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.juego.JUMPCROA;
import com.mygdx.juego.enums.GameState;
import com.mygdx.juego.utils.Constantes;
import com.mygdx.juego.utils.Pantallas;


public class GameOverScreen extends ScreenAdapter {

    JUMPCROA game;
    long timer;
    String text;
    boolean won;
    String returnedScore;
    public static BitmapFont font;



    public GameOverScreen(JUMPCROA game, String text, boolean won) {
        this.game = game;
        this.text = text;
        this.won = won;


    }

    @Override
    public void show() {
        timer = System.currentTimeMillis();
        font = new BitmapFont(Gdx.files.internal("ARCADE.fnt"), Gdx.files.internal("ARCADE.png"), false);
        font.setColor(Color.GREEN);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        MainMenuScreen.batch.begin();
        MainMenuScreen.batch.draw(Constantes.backgroundTexture, 0, 0);


        if (won) {
            font.getData().setScale(2.5f);
            font.draw(MainMenuScreen.batch, "Has ganado", 350, 1080);

        } else {
            font.getData().setScale(2.5f);
            font.draw(MainMenuScreen.batch, "Has perdido", 350, 1080);
        }
        MainMenuScreen.batch.end();

        if (game.state == GameState.TO_START) {
            MainMenuScreen.batch = null;
            MainMenuScreen.font = null;
            game.setScreen(Pantallas.mainMenuScreen);
        }

        if(System.currentTimeMillis()>timer+5000){
            game.state = GameState.TO_START;
        }

    }



    @Override
    public void hide() {

    }

}
