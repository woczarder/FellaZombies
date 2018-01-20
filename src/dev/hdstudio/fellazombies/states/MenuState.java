package dev.hdstudio.fellazombies.states;

import dev.hdstudio.fellazombies.Handler;
import dev.hdstudio.fellazombies.states.game.GameLauncher;

import java.awt.*;

public class MenuState extends State {

    private int linePadding = 15; // like padding between lines in A document
    private int label_xPos = 10;
    private int label_yPos = 10;

    public MenuState(Handler handler) {
        super(handler);

        GameLauncher.civilianSpeed = 0.3;
        GameLauncher.zombieSpeed = 0.4;
        GameLauncher.hunterSpeed = 0.3;

        GameLauncher.zombieMovement = true;
        GameLauncher.civilianMovement = true;
        GameLauncher.hunterMovement = true;

        GameLauncher.civilianNo = 100;
        GameLauncher.zombieNo = 10;
        GameLauncher.hunterNo = 5;

        GameLauncher.hunterFireRate = 1;
        GameLauncher.hunterRange = 50;
    }

    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(32)) { // spacebar - start a game
            handler.getGame().gameState = new GameState(handler);
            State.setState(handler.getGame().gameState);
        }

        check_booleans();

        check_increments();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,handler.getWidth(), handler.getHeight());

        g.setColor(Color.WHITE);
        g.drawString("Press spacebar to start game",                                                        label_xPos, label_yPos);
        g.drawString("Civilians: "+String.valueOf(GameLauncher.civilianNo),                                 label_xPos, label_yPos+linePadding);
        g.drawString("Zombies: "+String.valueOf(GameLauncher.zombieNo),                                     label_xPos, label_yPos+linePadding*2);
        g.drawString("Hunters: "+String.valueOf(GameLauncher.hunterNo),                                     label_xPos, label_yPos+linePadding*3);

        g.drawString("R: Civilians edge teleport: "+String.valueOf(GameLauncher.civilianTeleport),          label_xPos, label_yPos+linePadding*5);
        g.drawString("F: Hunters edge teleport: "+String.valueOf(GameLauncher.hunterTeleport),              label_xPos, label_yPos+linePadding*6);

        g.drawString("E: Civilians movement: "+String.valueOf(GameLauncher.civilianMovement),               label_xPos, label_yPos+linePadding*8);
        g.drawString("D: Zombies movement: "+String.valueOf(GameLauncher.zombieMovement),                   label_xPos, label_yPos+linePadding*9);
        g.drawString("C: Hunters movement: "+String.valueOf(GameLauncher.hunterMovement),                   label_xPos, label_yPos+linePadding*10);

        g.drawString("W: Civilians speed: "+String.valueOf(GameLauncher.civilianSpeed),                     label_xPos, label_yPos+linePadding*12);
        g.drawString("S: Zombies speed: "+String.valueOf(GameLauncher.zombieSpeed),                         label_xPos, label_yPos+linePadding*13);
        g.drawString("X: Hunters speed: "+String.valueOf(GameLauncher.hunterSpeed),                         label_xPos, label_yPos+linePadding*14);
        g.drawString("V: Hunters fire rate: "+String.valueOf(GameLauncher.hunterFireRate),                  label_xPos, label_yPos+linePadding*16);
        g.drawString("B: Hunters range: "+String.valueOf(GameLauncher.hunterRange),                         label_xPos, label_yPos+linePadding*17);


        g.drawString("P: Debug mode: "+String.valueOf(GameLauncher.drawLines),                          label_xPos, label_yPos+linePadding*19);
    }

    public static void check_booleans() {
        if (handler.getKeyManager().keyJustPressed(82)) // R - civilians teleport
            GameLauncher.civilianTeleport ^= true;

        if (handler.getKeyManager().keyJustPressed(70)) // F - hunters teleport
            GameLauncher.hunterTeleport ^= true;

        if (handler.getKeyManager().keyJustPressed(80)) // P - draw lines
            GameLauncher.drawLines ^= true;

        if (handler.getKeyManager().keyJustPressed(69)) // E - movement civilians
            GameLauncher.civilianMovement ^= true;

        if (handler.getKeyManager().keyJustPressed(68)) // D - movement zombies
            GameLauncher.zombieMovement ^= true;

        if (handler.getKeyManager().keyJustPressed(67)) // C - movement hunters
            GameLauncher.hunterMovement ^= true;
    }

    public static void check_increments() {

        // Number of entities

        if (handler.getKeyManager().Q && handler.getKeyManager().aUp)
            GameLauncher.civilianNo++;

        if (handler.getKeyManager().Q && handler.getKeyManager().aDown)
            GameLauncher.civilianNo--;

        if (handler.getKeyManager().Q && handler.getKeyManager().keyJustPressed(39))
            GameLauncher.civilianNo++;

        if (handler.getKeyManager().Q && handler.getKeyManager().keyJustPressed(37))
            GameLauncher.civilianNo--;


        if (handler.getKeyManager().A && handler.getKeyManager().aUp)
            GameLauncher.zombieNo++;

        if (handler.getKeyManager().A && handler.getKeyManager().aDown)
            GameLauncher.zombieNo--;

        if (handler.getKeyManager().A && handler.getKeyManager().keyJustPressed(39))
            GameLauncher.zombieNo++;

        if (handler.getKeyManager().A && handler.getKeyManager().keyJustPressed(37))
            GameLauncher.zombieNo--;


        if (handler.getKeyManager().Z && handler.getKeyManager().aUp)
            GameLauncher.hunterNo++;

        if (handler.getKeyManager().Z && handler.getKeyManager().aDown)
            GameLauncher.hunterNo--;

        if (handler.getKeyManager().Z && handler.getKeyManager().keyJustPressed(39))
            GameLauncher.hunterNo++;

        if (handler.getKeyManager().Z && handler.getKeyManager().keyJustPressed(37))
            GameLauncher.hunterNo--;



        // Speed of entities

        if (handler.getKeyManager().W && handler.getKeyManager().keyJustPressed(39))
            GameLauncher.civilianSpeed += 0.1;

        if (handler.getKeyManager().W && handler.getKeyManager().keyJustPressed(37))
            GameLauncher.civilianSpeed -= 0.1;


        if (handler.getKeyManager().S && handler.getKeyManager().keyJustPressed(39))
            GameLauncher.zombieSpeed += 0.1;

        if (handler.getKeyManager().S && handler.getKeyManager().keyJustPressed(37))
            GameLauncher.zombieSpeed -= 0.1;


        if (handler.getKeyManager().X && handler.getKeyManager().keyJustPressed(39))
            GameLauncher.hunterSpeed += 0.1;

        if (handler.getKeyManager().X && handler.getKeyManager().keyJustPressed(37))
            GameLauncher.hunterSpeed -= 0.1;


        // Hunter fire rate and range

        if (handler.getKeyManager().B && handler.getKeyManager().aUp)
            GameLauncher.hunterRange++;

        if (handler.getKeyManager().B && handler.getKeyManager().aDown)
            GameLauncher.hunterRange--;

        if (handler.getKeyManager().B && handler.getKeyManager().keyJustPressed(39))
            GameLauncher.hunterRange++;

        if (handler.getKeyManager().B && handler.getKeyManager().keyJustPressed(37))
            GameLauncher.hunterRange--;

        if (handler.getKeyManager().V && handler.getKeyManager().keyJustPressed(39))
            GameLauncher.hunterFireRate += 0.1;

        if (handler.getKeyManager().V && handler.getKeyManager().keyJustPressed(37))
            GameLauncher.hunterFireRate -= 0.1;

    }
}
