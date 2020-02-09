package ru.coistem.testgame;

import com.badlogic.gdx.physics.box2d.*;

public class ContListener implements ContactListener {
    World world;
    public ContListener(World world) {
        this.world = world;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println(contact.toString());
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("?");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
