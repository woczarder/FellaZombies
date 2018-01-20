package dev.hdstudio.fellazombies.states.game.entities.creatures.goodGuys;

import dev.hdstudio.fellazombies.states.game.GameLauncher;
import dev.hdstudio.fellazombies.states.game.entities.creatures.GoodGuy;


import java.awt.*;

public class Civilian extends GoodGuy {

    public Civilian(float x, float y, double speed) {
        super(x, y);

        entityManager.numberOfCivilians++;

        this.speed = (float) speed;
    }
    
    private void move() {
        runaway();
        if (GameLauncher.civilianTeleport) {
            teleport();
        }
    }

    @Override
    public void tick() {
        findTarget();

        if (GameLauncher.civilianMovement)
            move();

        jiggle();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) x-5, (int) y-5, 10, 10); // drawing the rectangular body

        if (GameLauncher.drawLines) {
            if (target != null) { // helper target line
                g.setColor(Color.GREEN);
                g.drawLine((int) this.x+3, (int)this.y+3, (int)target.getX()+3, (int)target.getY()+3);
            }
        }

    }

    public boolean isHunter() {
        return false;
    }
}
