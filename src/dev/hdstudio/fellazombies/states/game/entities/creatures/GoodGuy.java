package dev.hdstudio.fellazombies.states.game.entities.creatures;

import dev.hdstudio.fellazombies.states.game.entities.Entity;


public abstract class GoodGuy extends Entity {

    public GoodGuy(float x, float y) {
        super(x, y);
    }

    protected void findTarget() {
        setTarget(null);

        if (!entityManager.getZombies().isEmpty()) {
            target = entityManager.getZombies().get(0);

            for (Entity z : entityManager.getZombies()) {
                if (distance(this, target) > distance(this, z))
                    setTarget(z);
            }
        }
    }

    public abstract boolean isHunter();

    // getters and setters and shit

}
