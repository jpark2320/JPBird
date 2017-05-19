package com.jpark.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jpark.game.FlappyDemo;

/**
 * Created by JPark on 7/29/16.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    private Texture jp;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("background.png");
        playBtn = new Texture("playButton.png");
        jp = new Texture("jp.png");
    }

    @Override
    public  void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(jp, cam.position.x - jp.getWidth() / 2, cam.position.y + 100);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y - 80);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        jp.dispose();
    }
}
