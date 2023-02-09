package com.mygdx.juego.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.juego.escenas.GameScreen;
import com.mygdx.juego.escenas.MainMenuScreen;
import com.mygdx.juego.utils.Constantes;

import java.util.Iterator;



public class Coche extends Rectangle {

    private float speed;
    private int row;

    public Coche(float speed, int row) {
        this.speed = speed;
        this.row = row;
    }

    public static void move() {
        for (Iterator<Coche> iterCarL1 = GameScreen.listaCars.iterator(); iterCarL1.hasNext(); ) {
            Coche coche1 = iterCarL1.next();

            if (coche1.getRow() < 3)
                coche1.x += 100 * Gdx.graphics.getDeltaTime() * coche1.getSpeed();

            if (coche1.getRow() > 2)
                coche1.x -= 100 * Gdx.graphics.getDeltaTime() * coche1.getSpeed();

            if (coche1.x > Constantes.APP_WIDTH && coche1.getRow() < 3)
                iterCarL1.remove();

            if (coche1.x < - coche1.getWidth() && coche1.getRow() > 2)
                iterCarL1.remove();

            if (coche1.overlaps(GameScreen.player))
                GameScreen.playerDeath();
        }
    }

    public static void draw() {
        for (Coche coche1 : GameScreen.listaCars) {
            switch (coche1.getRow()) {
                case 1:
                    MainMenuScreen.batch.draw(Constantes.car1Texture, coche1.getX(), coche1.getY());
                    break;
                case 2:
                    MainMenuScreen.batch.draw(GameScreen.car2Flipped, coche1.getX(), coche1.getY());
                    break;
                case 3:
                    MainMenuScreen.batch.draw(GameScreen.car1Flipped, coche1.getX(), coche1.getY());
                    break;
                case 4:
                    MainMenuScreen.batch.draw(Constantes.car2Texture, coche1.getX(), coche1.getY());
                    break;
                    case 5:
                    MainMenuScreen.batch.draw(Constantes.car1Texture, coche1.getX(), coche1.getY());
                    break;
                case 6:
                    MainMenuScreen.batch.draw(GameScreen.car2Flipped, coche1.getX(), coche1.getY());
                    break;
                case 7:
                    MainMenuScreen.batch.draw(GameScreen.car1Flipped, coche1.getX(), coche1.getY());
                    break;
                case 8:
                    MainMenuScreen.batch.draw(Constantes.car2Texture, coche1.getX(), coche1.getY());
                    break;

            }
        }
    }

    public float getSpeed() {
        return speed;
    }

    public int getRow() {
        return row;
    }

    public static void spawnCar1() {
        Coche coche1 = new Coche(1.5f, 1);
        coche1.width = Constantes.FROG_WIDTH;
        coche1.height = Constantes.FROG_HEIGHT;
        coche1.x = - coche1.width;
        coche1.y = 800;
        GameScreen.listaCars.add(coche1);
    }

    public static void spawnCar2() {
        Coche coche1 = new Coche(3.5f, 2);
        coche1.width = Constantes.FROG_WIDTH;
        coche1.height = Constantes.FROG_HEIGHT;
        coche1.x = - coche1.width;
        coche1.y = 450;
        GameScreen.listaCars.add(coche1);
    }

    public static void spawnCar3() {
        Coche coche1 = new Coche(2.5f, 3);
        coche1.width = Constantes.FROG_WIDTH;
        coche1.height = Constantes.FROG_HEIGHT;
        coche1.x = Constantes.APP_WIDTH ;
        coche1.y = 1050;
        GameScreen.listaCars.add(coche1);
    }

    public static void spawnCar8() {
        Coche coche1 = new Coche(3.5f, 2);
        coche1.width = Constantes.FROG_WIDTH;
        coche1.height = Constantes.FROG_HEIGHT;
        coche1.x = - coche1.width;
        coche1.y = 1300;
        GameScreen.listaCars.add(coche1);
    }
    public static void spawnCar10() {
        Coche coche1 = new Coche(4f,4 );
        coche1.width = Constantes.FROG_WIDTH;
        coche1.height = Constantes.FROG_HEIGHT;
        coche1.x = Constantes.APP_WIDTH;
        coche1.y = 1700;
        GameScreen.listaCars.add(coche1);
    }

    public static void spawnCar9() {
        Coche coche1 = new Coche(1.5f, 1);
        coche1.width = Constantes.FROG_WIDTH;
        coche1.height = Constantes.FROG_HEIGHT;
        coche1.x = - coche1.width;
        coche1.y = 1450;
        GameScreen.listaCars.add(coche1);
    }

    public static void spawnCar4() {
        Coche coche1 = new Coche(4.5f,4 );
        coche1.width = Constantes.FROG_WIDTH;
        coche1.height = Constantes.FROG_HEIGHT;
        coche1.x = Constantes.APP_WIDTH;
        coche1.y = 700;
        GameScreen.listaCars.add(coche1);
    }


    public static void spawnCar6() {
        Coche coche1 = new Coche(1.5f, 1);
        coche1.width = Constantes.FROG_WIDTH;
        coche1.height = Constantes.FROG_HEIGHT;
        coche1.x = - coche1.width;
        coche1.y = 200;
        GameScreen.listaCars.add(coche1);
    }
}
