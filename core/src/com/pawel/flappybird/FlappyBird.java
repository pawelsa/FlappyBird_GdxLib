package com.pawel.flappybird;

import com.badlogic.gdx.ApplicationAdapter;

public class FlappyBird extends ApplicationAdapter {

    Menager menager;

    @Override
    public void create() {

        menager = new Menager();
    }


    @Override
    public void render() {

        menager.render();
    }

}
