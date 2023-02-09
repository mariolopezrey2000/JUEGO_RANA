package com.mygdx.juego.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Constantes {

    public static final int APP_WIDTH = 1080;
    public static final int APP_HEIGHT = 2160 ;

    public static final int FROG_WIDTH = 80;
    public static final int FROG_HEIGHT = 80;
    public static final int FROG_X = APP_WIDTH / 2 - FROG_WIDTH / 2;
    public static final int FROG_Y = 56;

    public static final Texture frogTexture = new Texture(Gdx.files.internal("Frog_player.png"));
    public static final Texture frogTextureJump = new Texture(Gdx.files.internal("Frog_jump.png"));
    public static final TextureRegion backgroundTexture = new TextureRegion(new Texture("Background.png"), 0, 0, Constantes.APP_WIDTH, Constantes.APP_HEIGHT);
    public static final Texture car1Texture = new Texture(Gdx.files.internal("Car1.png"));
    public static final Texture car2Texture = new Texture(Gdx.files.internal("Car2.png"));

    
    public static final Texture flechadech = new Texture(Gdx.files.internal("Derecha.png"));
    public static final Texture flechaizq = new Texture(Gdx.files.internal("Izquierda.png"));
    public static Sprite car1Sprite = new Sprite(car1Texture);
    public static Sprite car2Sprite = new Sprite(car2Texture);
    
    public static final Music mainTheme = Gdx.audio.newMusic(Gdx.files.internal("Theme.mp3"));
    public static final Sound startSound = Gdx.audio.newSound(Gdx.files.internal("StartSound.wav"));
    public static final Sound jumpSound = Gdx.audio.newSound(Gdx.files.internal("JumpSound.wav"));
    public static final Sound victorySound = Gdx.audio.newSound(Gdx.files.internal("VictorySound.wav"));
    public static final Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("DeathSound.wav"));



}
