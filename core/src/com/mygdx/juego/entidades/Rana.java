package com.mygdx.juego.entidades;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.juego.enums.Axis;
import com.mygdx.juego.escenas.GameScreen;
import com.mygdx.juego.utils.Constantes;


public class Rana extends Rectangle {
    Sound jumpSound = Constantes.jumpSound;
    public static long lastJump;

    public Rana() {

    }

    public void move(Axis axis, boolean positive) {
        float x = axis == Axis.X ? positive ? getX() + Constantes.FROG_WIDTH : getX() - Constantes.FROG_WIDTH : getX(),
                y = axis == Axis.Y ? positive ? getY() + Constantes.FROG_HEIGHT : getY() - Constantes.FROG_HEIGHT : getY();
        if (x > 0 && x < Constantes.APP_WIDTH - Constantes.FROG_WIDTH && y >= Constantes.FROG_Y && TimeUtils.timeSinceNanos(lastJump) > 300000000) {
            GameScreen.isMoving = true;
            setPosition(x, y);
            jumpSound.play();
            Rana.lastJump = TimeUtils.nanoTime();
        }
    }
}
