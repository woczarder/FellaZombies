package dev.hdstudio.fellazombies.states;


import dev.hdstudio.fellazombies.Handler;
import dev.hdstudio.fellazombies.states.game.GameLauncher;

import java.awt.*;

public class GameState extends State {

    GameLauncher gameLauncher;


    public GameState(Handler handler) {
        super(handler);

        gameLauncher = new GameLauncher(handler);
    }

    @Override
    public void tick() {
        gameLauncher.tick();

        if (handler.getKeyManager().keyJustPressed(32)) { // spacebar
            handler.getGame().gameState = null;
            State.setState(handler.getGame().menuState);

            GameLauncher.getEntityManager().numberOfZombies = 0;
            GameLauncher.getEntityManager().numberOfCivilians = 0;
            GameLauncher.getEntityManager().numberOfHunters = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        gameLauncher.render(g);
    }
}