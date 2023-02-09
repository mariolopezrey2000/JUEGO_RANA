package com.mygdx.juego.escenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.juego.JUMPCROA;
import com.mygdx.juego.entidades.Rana;
import com.mygdx.juego.enums.GameState;
import com.mygdx.juego.utils.Constantes;
import com.mygdx.juego.utils.Pantallas;
import com.mygdx.juego.utils.Procesador;


public class MainMenuScreen extends ScreenAdapter {

    JUMPCROA game;
    Rana player;
    public static SpriteBatch batch;
    public static BitmapFont font;
    public static Music mainTheme = Constantes.mainTheme;


    public MainMenuScreen(JUMPCROA game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("ARCADE.fnt"), Gdx.files.internal("ARCADE.png"), false);
        font.setColor(Color.GREEN);
        mainTheme.setLooping(true);
        mainTheme.setVolume(.6f);
        mainTheme.play();

        Gdx.input.setInputProcessor(new Procesador() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                game.state = GameState.RUNNING;
                return true;
            }
        });

        player = new Rana();
        player.x = Constantes.FROG_X;
        player.y = Constantes.FROG_Y;
        player.width = Constantes.FROG_WIDTH;
        player.height = Constantes.FROG_HEIGHT;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();


        batch.draw(Constantes.backgroundTexture, 0, 0);
        batch.draw(Constantes.frogTexture, player.x, player.y);

        if (game.state == GameState.TO_START) {
            font.getData().setScale(4f);
            font.draw(batch, "FROGGER", Constantes.APP_WIDTH * .30f, Constantes.APP_HEIGHT * .75f);
            font.getData().setScale(2.5f);
            font.draw(batch, "PULSA  INTRO  PARA  EMPEZAR", Constantes.APP_WIDTH * .075f, Constantes.APP_HEIGHT * .65f);
        }
        batch.end();
        if (game.state == GameState.RUNNING) {
            if (Pantallas.gameScreen == null)
                Pantallas.gameScreen = new GameScreen(game, player);
            game.setScreen(Pantallas.gameScreen);
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
