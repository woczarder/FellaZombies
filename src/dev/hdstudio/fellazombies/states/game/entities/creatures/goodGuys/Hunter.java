package dev.hdstudio.fellazombies.states.game.entities.creatures.goodGuys;

import dev.hdstudio.fellazombies.states.game.GameLauncher;
import dev.hdstudio.fellazombies.states.game.entities.creatures.GoodGuy;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Hunter extends GoodGuy {

    private Timer t;
    private float fireRate;
    private boolean gunLoaded;

    public Hunter(float x, float y, double speed, double fireRate, int range) {
        super(x, y);

        entityManager.numberOfHunters++;

        this.range = range;
        this.speed = (float) speed;
        this.fireRate = (float) fireRate;

        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                gunLoaded = true;

            }
        }, (int)(fireRate*1000), (int)(fireRate*1000) );
    }

    private void move() {
        if (target != null) {
            if (distance(this, target) > range)
                chase();
            else
                runaway();
        }
        if (GameLauncher.hunterTeleport) {
            teleport();
        }
    }

    protected void kill() {
        if ( (target != null) && isThereContact() && gunLoaded) {
            target.setAlive(false);
            gunLoaded = false;
        }
    }

    @Override
    public void tick() {
        findTarget();

        if (GameLauncher.hunterMovement)
            move();

        jiggle();

        kill();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);

        if (isThereContact())
            g.setColor(Color.WHITE);

        if (GameLauncher.drawLines) {

            if (target != null) { // helper target line
                g.setColor(Color.BLUE);
                g.drawLine((int) this.x-3, (int)this.y-3, (int)target.getX()-3, (int)target.getY()-3);
            }
        }

        g.setColor(Color.BLUE);
        g.fillRect((int) x-5, (int) y-5, 10, 10); // drawing the rectangular body

        if (gunLoaded) {
            g.setColor(Color.pink);
            g.drawRect((int) x-1, (int) y-1, 1, 1);
        }
    }

    public float getFireRate() {
        return fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public boolean isHunter() {
        return true;
    }

}
