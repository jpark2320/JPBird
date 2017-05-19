package com.jpark.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by JPark on 7/31/16.
 */
public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bounds;
    private Texture texture;
    private Animation birdAnimation;

    private Sound flap;
    public static Sound failed;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdAnimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 4, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());

        flap = Gdx.audio.newSound(Gdx.files.internal("flapping.ogg"));
        failed = Gdx.audio.newSound(Gdx.files.internal("failed.ogg"));
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if (position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump() {
        velocity.y = 200;
        flap.play(0.1f);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
