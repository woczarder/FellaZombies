package dev.hdstudio.fellazombies;

import dev.hdstudio.fellazombies.display.Display;
import dev.hdstudio.fellazombies.input.KeyManager;
import dev.hdstudio.fellazombies.states.MenuState;
import dev.hdstudio.fellazombies.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game {
//3840 × 2160
    private Display display;
    private final int width = 1280,
                    height = 720;

    private BufferStrategy bs;
    private Graphics g;

    private Handler handler;

    // States
    public State gameState;
    public State menuState;

    // Input
    private KeyManager keyManager;

    public Game() {

        keyManager = new KeyManager();

        init();

        //GAME LOOP
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(true){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                Toolkit.getDefaultToolkit().sync(); // For Linux
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
                handler.signal++;
            }
        }
    }

    private void init() {
        display = new Display(width, height);
        display.getFrame().addKeyListener(keyManager);

        handler = new Handler(this);

        menuState = new MenuState(handler);
        State.setState(menuState);
    }

    private void tick(){
        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!

        g.setColor(Color.GRAY);
        g.fillRect(0,0,width,height);

        if (State.getState() != null)
            State.getState().render(g);


        //End Drawing!
        bs.show();
        g.dispose();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }
}
