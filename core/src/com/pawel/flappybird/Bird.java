package com.pawel.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

import static com.pawel.flappybird.Manager.isGameOn;
import static com.pawel.flappybird.Manager.batch;

/**
 * Created by Pawel on 06.03.2018.
 */

class Bird {

    private Texture bird[];

    private float velocity;
    private float birdPos;
    private final float gravity;

    private final int birdHorizontalPos;
    private boolean flappyState = false;

    private Circle circle;

    Bird() {

        setTextures();
        circle = new Circle();
        birdHorizontalPos = Gdx.graphics.getWidth() / 2 - bird[flappyState ? 0 : 1].getWidth() / 2;
        setBirdStartingPositionAndVelocity();
        gravity = 2;
    }

    void setBirdStartingPositionAndVelocity() {
        this.birdPos = Gdx.graphics.getHeight() / 2 - bird[1].getHeight() / 2;
        this.velocity = 0;
    }

    private void setTextures() {

        bird = new Texture[2];
        bird[0] = new Texture("bird.png");
        bird[1] = new Texture("bird2.png");
    }

    void calculatePosition() {

        if (isGameOn) {

            velocity += gravity;
            birdPos -= velocity;
        }

    }

    void renderBird() {

        circle.set(birdHorizontalPos + bird[0].getWidth() / 2, birdPos + bird[0].getHeight() / 2, bird[0].getHeight() / 2);

        batch.draw(bird[flappyState ? 0 : 1], birdHorizontalPos, birdPos);
        flappyState = !flappyState;
    }

    void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    boolean hasBirdDiedOutOfBounds() {

        return isGameOn && birdPos < 0;
    }

    public Circle getCircle() {
        return circle;
    }
}
