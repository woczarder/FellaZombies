package dev.hdstudio.fellazombies.states.game.entities.creatures;

import java.awt.*;

public class ZombiePlayer extends Zombie {

    public ZombiePlayer(float x, float y, double speed) {
        super(x, y, speed);
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
        g.setColor(Color.CYAN);

        g.fillRect((int) x-5, (int) y-5, 10, 10); // drawing the rectangular body
    }
}
