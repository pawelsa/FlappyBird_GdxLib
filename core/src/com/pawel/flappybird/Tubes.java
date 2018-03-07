package com.pawel.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;

import static com.pawel.flappybird.Menager.isAddingPoint;
import static com.pawel.flappybird.Menager.isGameOn;
import static com.pawel.flappybird.Menager.points;

/**
 * Created by Pawel on 06.03.2018.
 */

class Tubes {

    private Tube[] tubes = new Tube[2];

    private final int distanceBetweenTubes;
    private int tubePositionX;

    Tubes() {

        tubes[0] = new Tube();
        tubes[1] = new Tube();
        distanceBetweenTubes = Gdx.graphics.getWidth() * 3 / 4;
        tubePositionX = Gdx.graphics.getWidth();
    }

    void setTubesToStartPositionAfterPlayerDied() {

        tubes[0].setTubePositionY();
        tubes[1].setTubePositionY();
        tubePositionX = Gdx.graphics.getWidth();
    }

    void renderTubes() {

        if (isGameOn) {

            moveTubesAndSwapWhenOutOfScreen();
        }

        tubes[0].drawTubeAtPosition(tubePositionX);
        tubes[1].drawTubeAtPosition(tubePositionX + distanceBetweenTubes);
    }

    private void moveTubesAndSwapWhenOutOfScreen() {

        tubePositionX -= 4;

        if (tubePositionX < -tubes[1].getBottomTubeTexture().getWidth()) {

            tubePositionX += distanceBetweenTubes;
            swapTubesPositionsY();
        }
    }

    private void swapTubesPositionsY() {

        tubes[0] = tubes[1];
        tubes[1] = new Tube();
    }

    boolean collisionWithBird(final Circle bird) {

        return (tubes[0].hasCollidedWithBird(bird) || tubes[1].hasCollidedWithBird(bird));
    }

    void shouldAddPoint(final Circle bird) {

        if (bird.x + bird.radius * 2 > tubePositionX && bird.x + bird.radius * 2 < tubePositionX + 5) {

            if (!isAddingPoint) {
                points++;
                isAddingPoint = true;
            } else
                isAddingPoint = false;
        }else{
            isAddingPoint=false;
        }
    }

}
