package ru.coistem.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Bullet {
    Fixture fixture;
    Sprite sprite;
    Body body;

    public Bullet(World world, Body personBody) {
        sprite = new Sprite(new Texture(Gdx.files.internal("bullet.png")));
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        body.setTransform(personBody.getPosition().x, personBody.getPosition().y, 0);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth(), sprite.getHeight());
        fixture = body.createFixture(shape, 1f);
        fixture.setUserData("bullet");
        body.setUserData("bullet");
    }

    public void update() {
        body.setLinearVelocity(100000000000f, 0);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
    }
    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
}
