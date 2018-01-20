package dev.hdstudio.fellazombies.states.game.entities.creatures.goodGuys;

import java.awt.*;

public class HunterPlayer extends Hunter {

    private int playerRange;

    public HunterPlayer(float x, float y, double speed, double fireRate, int range) {
        super(x, y, speed, fireRate, range);

        playerRange = range;
    }

    private void move() {
        if (handler.getKeyManager().aLeft)
            x--;
        if (handler.getKeyManager().aRight)
            x++;
        if (handler.getKeyManager().aDown)
            y++;
        if (handler.getKeyManager().aUp)
            y--;
    }

    public void tick() {

        findTarget();

        move();

        jiggle();

        kill();

    }

    public void render(Graphics g) {
        if (target != null) { // helper target line
            g.setColor(Color.BLUE);
            g.drawLine((int) this.x-3, (int)this.y-3, (int)target.getX()-3, (int)target.getY()-3);
            g.drawOval((int) x-playerRange, (int) y-playerRange, 2*playerRange, 2*playerRange);
        }

        g.setColor(Color.CYAN);
        g.fillRect((int) x-5, (int) y-5, 10, 10); // drawing the rectangular body
    }

}
