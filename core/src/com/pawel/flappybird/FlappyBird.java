package com.pawel.flappybird;

import com.badlogic.gdx.ApplicationAdapter;

public class FlappyBird extends ApplicationAdapter {

    private Manager manager;

    @Override
    public void create() {

        manager = new Manager();
    }


    @Override
    public void render() {

        manager.render();
    }

}
