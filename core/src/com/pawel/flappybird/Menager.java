package com.pawel.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Pawel on 06.03.2018.
 */

public class Menager{

    static SpriteBatch batch;
    private Texture background;

    private Bird bird;
    private Tubes tubes;

    static boolean isGameOn = false;


    Menager(){

        batch = new SpriteBatch();
        bird = new Bird();
        tubes = new Tubes();
        background = new Texture("bg.png");
    }

    void render(){

        bird.calculatePosition();

        manageTouches();

        drawElements();
        managePlayersDeath();


    }

    private void managePlayersDeath() {

        if (bird.hasBirdDiedOutOfBounds() || tubes.collisionWithBird(bird.getCircle())) {
            restartGame();
        }
    }

    void restartGame(){

        bird.setBirdStartingPositionAndVelocity();
        isGameOn = false;
        tubes.setTubesToStartPositionAfterPlayerDied();
    }

    private void manageTouches() {

        if (Gdx.input.justTouched()) {

            if (isGameOn) {

                bird.setVelocity(-20);
            } else {

                isGameOn = !isGameOn;
            }
        }
    }

    private void drawElements() {

        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tubes.renderTubes();
        bird.renderBird();

        batch.end();
    }
}
