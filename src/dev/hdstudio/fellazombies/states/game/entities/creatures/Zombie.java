package dev.hdstudio.fellazombies.states.game.entities.creatures;

import dev.hdstudio.fellazombies.states.game.GameLauncher;
import dev.hdstudio.fellazombies.states.game.entities.Entity;

import java.awt.*;

public class Zombie extends Entity {

    public Zombie(float x, float y, double speed) {
        super(x, y);

        entityManager.numberOfZombies++;

        range = 1;
        this.speed = (float) speed;
    }

    protected void findTarget() {
        setTarget(null);

        if (!entityManager.getGoodGuys().isEmpty()) {
            target = entityManager.getGoodGuys().get(0);

            for (Entity c : entityManager.getGoodGuys()) {
                if (distance(this, target) > distance(this, c))
                    setTarget(c);
            }
        }
    }

    private void move() {
        chase();
    }

    protected void kill() {
        if ( (target != null) && isThereContact() && target.isAlive() ) {
            target.setAlive(false);
            entityManager.addToSpawn(this);
        }
    }

    @Override
    public void tick() {

        findTarget();

        if (GameLauncher.zombieMovement)
            move();

        jiggle();

        kill();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);

        if (isThereContact())
            g.setColor(Color.WHITE);

        g.fillRect((int) x-5, (int) y-5, 10, 10); // drawing the rectangular body

        if (GameLauncher.drawLines) {
            if (target != null) { // helper target line
                g.setColor(Color.RED);
                g.drawLine((int) this.x, (int)this.y, (int)target.getX(), (int)target.getY());
            }
        }

    }

    // getters and setters and shit


}
