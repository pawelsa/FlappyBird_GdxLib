package com.pawel.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import static com.pawel.flappybird.Menager.batch;

/**
 * Created by Pawel on 06.03.2018.
 */

class Tube {

    private Texture bottomTubeTexture;
    private Texture upperTubeTexture;
    private int bottomTubePos;
    private int upperTubePos;
    private final int distanceBetweenTubes = 500;

    private Rectangle upperTubeBound;
    private Rectangle bottomTubeBound;

    private ShapeRenderer shapeRenderer;

    Tube() {

        setTextures();
        setTubePositionY();
        shapeRenderer = new ShapeRenderer();
        upperTubeBound = new Rectangle();
        bottomTubeBound = new Rectangle();
    }

    private void setTextures() {

        bottomTubeTexture = new Texture("bottomtube.png");
        upperTubeTexture = new Texture("toptube.png");
    }

    private void createBoundRectangles(int positionX) {

        upperTubeBound.set(positionX, upperTubePos, upperTubeTexture.getWidth(), upperTubeTexture.getHeight());
        bottomTubeBound.set(positionX, bottomTubePos, bottomTubeTexture.getWidth(), bottomTubeTexture.getHeight());
    }


    void setTubePositionY() {

        int middlePointBetweenTubes = calculateMiddlePointBetweenTubes();

        upperTubePos = middlePointBetweenTubes + distanceBetweenTubes / 2;
        bottomTubePos = middlePointBetweenTubes - distanceBetweenTubes / 2 - bottomTubeTexture.getHeight();
    }

    private int calculateMiddlePointBetweenTubes() {

        Random rand = new Random();

        int distanceFromMiddleToEdge;
        int middlePointBetweenTubes;

        do {

            middlePointBetweenTubes = rand.nextInt(Gdx.graphics.getHeight());
            distanceFromMiddleToEdge = middlePointBetweenTubes - distanceBetweenTubes / 2;

        } while (!doesBothTubesFitOnScreen(distanceFromMiddleToEdge));

        return middlePointBetweenTubes;
    }

    private boolean doesBothTubesFitOnScreen(int distanceFromMiddleToEdge) {

        return ((Gdx.graphics.getHeight() - distanceFromMiddleToEdge) < upperTubeTexture.getHeight() &&
                distanceFromMiddleToEdge < bottomTubeTexture.getHeight());
    }


    void drawTubeAtPosition(int positionX) {

        createBoundRectangles(positionX);

        batch.draw(getBottomTubeTexture(), positionX, getBottomTubePos());
        batch.draw(getUpperTubeTexture(), positionX, getUpperTubePos());
    }

    boolean hasCollidedWithBird(final Circle bird) {

        return  Intersector.overlaps(bird, upperTubeBound) || Intersector.overlaps(bird, bottomTubeBound);
    }

    Texture getBottomTubeTexture() {
        return bottomTubeTexture;
    }

    private Texture getUpperTubeTexture() {
        return upperTubeTexture;
    }

    private int getBottomTubePos() {
        return bottomTubePos;
    }

    private int getUpperTubePos() {
        return upperTubePos;
    }
}
