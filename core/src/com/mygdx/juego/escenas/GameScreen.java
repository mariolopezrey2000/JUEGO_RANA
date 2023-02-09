package com.mygdx.juego.escenas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.juego.JUMPCROA;
import com.mygdx.juego.entidades.Coche;
import com.mygdx.juego.entidades.Rana;
import com.mygdx.juego.enums.Axis;
import com.mygdx.juego.enums.GameState;
import com.mygdx.juego.utils.Constantes;
import com.mygdx.juego.utils.Pantallas;
import com.mygdx.juego.utils.Procesador;

import java.util.ArrayList;


public class GameScreen extends ScreenAdapter {
    ArrayList<Vector2> winPositions = new ArrayList<>();
    ArrayList<Boolean> winPositionsSelected = new ArrayList<>();
    public static JUMPCROA game;
    public static Rana player;
    public static Array<Coche> listaCars;

    Sound startSound = Constantes.startSound;
    Sound victorySound = Constantes.victorySound;
    static Sound deathSound = Constantes.deathSound;
    public static int vidas, victoria;
    long contadorSegundos;
    public static Sprite car1Flipped, car2Flipped;
    public static boolean isMoving = false;
    String text;

    boolean won;


    Timer.Task CarTimer = new Timer.Task() {
        @Override
        public void run() {
            Coche.spawnCar1();
            Coche.spawnCar2();
            Coche.spawnCar3();
            Coche.spawnCar4();
            Coche.spawnCar6();
            Coche.spawnCar8();
            Coche.spawnCar9();
            Coche.spawnCar10();
        }
    };

    public GameScreen(JUMPCROA game, Rana player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void show() {


        //mover con los dedos de la pantalla tactil arriba abajo y lados
        Gdx.input.setInputProcessor(new Procesador() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {


                if (screenY>450) {
                    player.move(Axis.Y, true);
                    System.out.println("arriba");
                    System.out.println("toque pantalla:"+screenY);
                    System.out.println("jugador"+player.getY());
                }else {
                    if (screenX>450) {
                        player.move(Axis.X, true);
                        System.out.println("derecha");
                        System.out.println("toque pantalla:"+screenX);
                        System.out.println("jugador"+player.getX());
                    }
                    if (screenX<450) {
                        player.move(Axis.X, false);
                        System.out.println("izquierda");
                        System.out.println("toque pantalla:"+screenX);
                        System.out.println("jugador"+player.getX());
                    }
                }

                return true;
            }
        });


        Rana.lastJump = TimeUtils.nanoTime();
        startSound.play();
        contadorSegundos = TimeUtils.nanoTime();
        listaCars = new Array<Coche>();
        vidas = 3;
        victoria = 0;
        winPositions.add(new Vector2(1080, 1700));
        winPositionsSelected.add(false);
        startTimer();
    }

    @Override
    public void render(float delta) {
        if (game.state == GameState.RUNNING){
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            MainMenuScreen.batch.begin();
            MainMenuScreen.batch.draw(Constantes.backgroundTexture, 0, 0);
            MainMenuScreen.batch.draw(Constantes.flechaizq,125,1840,300,100);
            MainMenuScreen.batch.draw(Constantes.flechadech,700,1840,300,100);

            if (!isMoving) {
                MainMenuScreen.batch.draw(Constantes.frogTexture, player.getX(), player.getY());
            } else {
                MainMenuScreen.batch.draw(Constantes.frogTextureJump, player.getX(), player.getY());
            }


            Coche.draw();
            Coche.move();
            mostrarVidas();

            if (player.y > 1700
            ) {
                double aux = 9999, distancia;
                int index = -1;
                for (int i = 0; i < winPositions.size(); i++) {
                    distancia = Math.sqrt(Math.pow(winPositions.get(i).x - player.x, 2) + Math.pow(winPositions.get(i).y - player.y , 2));
                    if (distancia < aux) {
                        aux = distancia;
                        index = i;
                    }
                }
                if (winPositionsSelected.get(index)) {
                    playerDeath();
                } else {
                    winPositionsSelected.set(index, true);
                    victorySound.play();
                    victoria++;
                    player.y = Constantes.FROG_Y;
                    player.x = Constantes.FROG_X;
                }
            }

            if (victoria == 1) {
                game.state = GameState.OVER;
                won = true;
            }

            if (vidas == 0 ) {
                game.state = GameState.OVER;
                won = false;
            }
            MainMenuScreen.batch.end();
        } else if (game.state == GameState.OVER) {
            for (int i = 0; i < winPositionsSelected.size(); i++) {
                winPositionsSelected.set(i, false);
            }
            MainMenuScreen.mainTheme.stop();
            Pantallas.gameOverScreen = new GameOverScreen(game, text, won);
            game.setScreen(Pantallas.gameOverScreen);
        }
    }

    public static void playerDeath() {

        deathSound.play();
        vidas--;
        player.x = Constantes.FROG_X;
        player.y = Constantes.FROG_Y;
    }

    public void mostrarVidas() {
        for (int i = 0; i < vidas; i++ ) {
            MainMenuScreen.batch.draw(Constantes.frogTexture, Constantes.APP_WIDTH * .1f * i, 0);
        }
    }


    public void startTimer() {
        try {
            Timer.schedule(CarTimer, 0f, 2.5f);
            car1Flipped = Constantes.car1Sprite;
            car2Flipped = Constantes.car2Sprite;
            car1Flipped.flip(true, false);
            car2Flipped.flip(true, false);
        } catch (Exception e) {}
    }



    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
