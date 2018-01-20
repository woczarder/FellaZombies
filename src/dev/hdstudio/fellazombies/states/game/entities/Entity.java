package dev.hdstudio.fellazombies.states.game.entities;

import dev.hdstudio.fellazombies.Handler;

import java.awt.Graphics;

public abstract class Entity {

    public static float distance(Entity e1, Entity e2) {
        return ((float) Math.sqrt(Math.pow(e1.x - e2.x, 2) + Math.pow(e1.y - e2.y, 2)));
    }

    public abstract void tick();

    public abstract void render(Graphics g);


    // For every type of entity

    protected static Handler handler;
    protected static EntityManager entityManager;

    protected float x, y, speed;
    protected boolean alive;
    protected Entity target;
    protected int range;


    public Entity(float x, float y) {
        this.alive = true;
        this.x = x;
        this.y = y;
    }

    protected boolean isThereContact() {
        if (target == null)
            return false;
        else
            return (distance(this, target) <= range + 3);
    }

    protected void runaway() {
        if (target != null) {
            if (target.getX() > x)
                x -= speed;
            if (target.getX() < x)
                x += speed;
            if (target.getY() > y)
                y -= speed;
            if (target.getY() < y)
                y += speed;
        }
    }

    protected void chase() {
        if (target != null) {
            if (target.getX() > x)
                x += speed;
            if (target.getX() < x)
                x -= speed;
            if (target.getY() > y)
                y += speed;
            if (target.getY() < y)
                y -= speed;
        }
    }

    protected void jiggle() {
        x += Math.random() - 0.5;
        y += Math.random() - 0.5;

        if (x < 0)
            x = 1;
        if (x > handler.getWidth())
            x = handler.getWidth() - 1;
        if (y < 0)
            y = 1;
        if (y > handler.getHeight())
            y = handler.getHeight() - 1;
    }

    protected void teleport() {
        if (x < 5 || y < 5 || x > handler.getWidth()-5 || y > handler.getHeight()-5) {
            x = (float) (Math.random() * handler.getWidth());
            y = (float) (Math.random() * handler.getHeight());
        }
    }

    // getters and setters and shit

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Entity getTarget() {
        return target;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
