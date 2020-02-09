package ru.coistem.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.Random;

public class Game extends ApplicationAdapter {
    World world;
    SpriteBatch batch;

    Random r = new Random();

    ArrayList<Bullet> bullets;

    Sprite person;
    Sprite other;

    Fixture personFixture;
    Fixture otherFixture;

    BodyDef personBodyDef;
    BodyDef otherBodyDef;

    Body personBody;
    static Body otherBody;

    PolygonShape personShape;
    PolygonShape otherShape;

    @Override
    public void create() {
        bullets = new ArrayList<>();

        world = new World(new Vector2(0, 0), true);
        batch = new SpriteBatch();

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
//                if (contact.getFixtureA().getUserData().equals("I")){
//                    world.destroyBody(contact.getFixtureA().getBody());
//
//                }
//                if (contact.getFixtureB().getUserData().equals("I")){
//                    world.destroyBody(contact.getFixtureB().getBody());
//
//                }
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                if (contact.getFixtureA() != null && contact.getFixtureA() != null) {
                    if (contact.getFixtureA().getBody().getUserData().equals("ii")) {
                        contact.getFixtureA().getBody().setUserData("0hp");
                    } else if (contact.getFixtureB().getBody().getUserData().equals("ii")) {
                        contact.getFixtureB().getBody().setUserData("0hp");
                    }
                }
            }
        });

        person = new Sprite(new Texture(Gdx.files.internal("per.png")));
        other = new Sprite(new Texture(Gdx.files.internal("per.png")));

        personBodyDef = new BodyDef();
        otherBodyDef = new BodyDef();

        personBodyDef.type = BodyDef.BodyType.DynamicBody;
        otherBodyDef.type = BodyDef.BodyType.StaticBody;

        personShape = new PolygonShape();
        otherShape = new PolygonShape();

        personShape.setAsBox(person.getWidth(), person.getHeight());
        otherShape.setAsBox(person.getWidth(), person.getHeight());

        personBody = world.createBody(personBodyDef);
        otherBody = world.createBody(otherBodyDef);

        personBody.setUserData("per");
        otherBody.setUserData("ii");

        otherBody.setTransform(1100, 100, 0);
        personBody.setTransform(0, 100, 0);

        personFixture = personBody.createFixture(personShape, 1f);
        otherFixture = otherBody.createFixture(otherShape, 1f);

        personFixture.setUserData("per");
        otherFixture.setUserData("ii");
    }

    @Override
    public void render() {
        if (otherBody.getUserData().equals("0hp")) {
            otherBody = null;
        }
        if (otherBody == null) {
            otherBody = world.createBody(otherBodyDef);
            otherBody.setTransform(r.nextInt(1820), r.nextInt(980), 0);
            otherBody.setUserData("ii");
            otherFixture = otherBody.createFixture(otherShape, 1f);
            System.out.println("lalalaw");
        }

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            bullets.get(i).draw(batch);
        }
        batch.end();
        world.step(Gdx.graphics.getDeltaTime(), 100, 10);

        person.setPosition(personBody.getPosition().x, personBody.getPosition().y);
        other.setPosition(otherBody.getPosition().x, otherBody.getPosition().y);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            System.exit(0);
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            personBody.setTransform(personBody.getPosition().x, personBody.getPosition().y + 5, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            personBody.setTransform(personBody.getPosition().x, personBody.getPosition().y - 5, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            personBody.setTransform(personBody.getPosition().x + 5, personBody.getPosition().y, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            personBody.setTransform(personBody.getPosition().x - 5, personBody.getPosition().y, 0);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Bullet bul = new Bullet(world, personBody);
            bullets.add(bul);

        }

        batch.begin();

        person.draw(batch);
        other.draw(batch);

        batch.end();
    }
}
