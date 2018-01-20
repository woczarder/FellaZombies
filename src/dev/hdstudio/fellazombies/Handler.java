package dev.hdstudio.fellazombies;

import dev.hdstudio.fellazombies.input.KeyManager;

public class Handler {

    public static int signal = 0;

    private Game game;

    public Handler(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

}