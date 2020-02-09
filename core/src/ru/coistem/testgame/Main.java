package ru.coistem.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import sun.security.acl.WorldGroupImpl;

import java.util.Random;

public class Main extends ApplicationAdapter {
	World world;
	SpriteBatch batch;

	Sprite person;
	Sprite box;

	Body body;
	BodyDef def;
	Fixture fixture;
	PolygonShape shape;

	BodyDef boxDef;
	Body boxBody;
	PolygonShape boxShape;

	float x = 0;
	float y = 0;
	float speed = 0;

	@Override
	public void create () {
		world = new World(new Vector2(0, 20), true);
		batch = new SpriteBatch();
		person = new Sprite(new Texture(Gdx.files.internal("per.png")));
		box = new Sprite(new Texture(Gdx.files.internal("per.png")));
		def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.bullet = true;
		body = world.createBody(def);
		body.setTransform(100, 100, 0);
		shape = new PolygonShape();
		shape.setAsBox(person.getWidth(), person.getHeight());
		fixture = body.createFixture(shape, 1f);
		person.setPosition(body.getPosition().x, body.getPosition().y);

		boxShape = new PolygonShape();
		boxShape.setAsBox(100, 100);
		boxDef = new BodyDef();
		boxDef.type = BodyDef.BodyType.StaticBody;
		boxBody = world.createBody(def);
		boxBody.setTransform(50, 50, 0);
	}

	@Override
	public void render () {

		update();
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		person.setPosition(body.getPosition().x, body.getPosition().y);
		batch.begin();
		person.draw(batch);
		box.draw(batch);
		batch.end();
	}
	public void update(){
		x+=(-x/100);
		y+=(-y/100);

		speed = Gdx.graphics.getDeltaTime() * 100;
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			System.exit(0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			y+=speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)){
			y-=speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)){
			x-=speed;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)){
			x+=speed;
		}

		int oldX = (int)body.getPosition().x;
		int oldY = (int)body.getPosition().y;

		body.setTransform(oldX +  x, oldY + y, 0);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
