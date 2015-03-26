package com.drem.games.ggs.game.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.drem.games.ggs.Application;

/**
 * Created by drem on 3/23/15.
 */
public class PlayerEntity extends GameEntity {


    public PlayerEntity() {
        setLocation(Application.WIDTH / 2, Application.HEIGHT / 2);
        radians = 3.145f / 2;
    }

    public void setLocation(float lx, float ly) {
        this.x = lx;
        this.y = ly;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private void setShape() {

        shapex = new float[4];
        shapey = new float[4];

        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapex[1] = x + MathUtils.cos(radians - (4 * 3.145f / 5)) * 8;
        shapex[2] = x + MathUtils.cos(radians + (3.145f * 5)) * 8;
        shapex[3] = x + MathUtils.cos(radians + (4 * 3.145f / 5)) * 8;

        shapey[0] = y + MathUtils.sin(radians) * 8;
        shapey[1] = y + MathUtils.sin(radians - (4 * 3.145f / 5)) * 8;
        shapey[2] = y + MathUtils.sin(radians + (3.145f * 5)) * 8;
        shapey[3] = y + MathUtils.sin(radians + (4 * 3.145f / 5)) * 8;
    }

    public void update(float dt) {
        // DO SOMETHING HERE
        setShape();
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(1, 1, 1, 1);
//        shapeRenderer.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        // All drawing needs to be here

        shapeRenderer.end();
    }
}
