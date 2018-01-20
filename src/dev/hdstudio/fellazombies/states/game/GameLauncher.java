package dev.hdstudio.fellazombies.states.game;

import dev.hdstudio.fellazombies.Handler;
import dev.hdstudio.fellazombies.states.MenuState;
import dev.hdstudio.fellazombies.states.game.entities.EntityManager;
import dev.hdstudio.fellazombies.states.game.entities.creatures.Zombie;
import dev.hdstudio.fellazombies.states.game.entities.creatures.ZombiePlayer;
import dev.hdstudio.fellazombies.states.game.entities.creatures.goodGuys.Civilian;
import dev.hdstudio.fellazombies.states.game.entities.creatures.goodGuys.Hunter;
import dev.hdstudio.fellazombies.states.game.entities.creatures.goodGuys.HunterPlayer;

import java.awt.*;

public class GameLauncher {

    private static EntityManager entityManager;
    private static Handler handler;

    public static int civilianNo;
    public static int zombieNo;
    public static int hunterNo;

    public static boolean civilianTeleport;
    public static boolean hunterTeleport;
    public static boolean drawLines;
    public static boolean civilianMovement;
    public static boolean zombieMovement;
    public static boolean hunterMovement;

    public static double civilianSpeed;
    public static double zombieSpeed;
    public static double hunterSpeed;
    public static double hunterFireRate;
    public static int hunterRange;

    public GameLauncher(Handler handler) {
        this.handler = handler;


        entityManager = new EntityManager(handler);

        // adding entities

        for (int i = 0; i < civilianNo; i++)
            entityManager.addGoodGuy(new Civilian((float) Math.random()*handler.getWidth(), (float) Math.random()*handler.getHeight(), civilianSpeed));

        for (int i = 0; i < zombieNo; i++)
            entityManager.addZombie(new Zombie((float) Math.random()*handler.getWidth(), (float) Math.random()*handler.getHeight(), zombieSpeed));

        for (int i = 0; i < hunterNo; i++)
            entityManager.addGoodGuy(new Hunter((float) Math.random()*handler.getWidth(), (float) Math.random()*handler.getHeight(), hunterSpeed, hunterFireRate, hunterRange));

        //entityManager.addZombie(new ZombiePlayer(100, 100, 1));

        entityManager.addGoodGuy(new HunterPlayer(100, 100, 1, 0.1, 60));
    }

    public void tick() {
        entityManager.tick();

        MenuState.check_booleans();
    }

    public void render(Graphics g) {
        entityManager.render(g);

        g.setColor(Color.WHITE);
        g.drawString("Civilians: "+String.valueOf(entityManager.numberOfCivilians), 10, 15);
        g.drawString("Zombies: "+String.valueOf(entityManager.numberOfZombies), 10, 30);
        g.drawString("Hunters: "+String.valueOf(entityManager.numberOfHunters), 10, 45);

        if (entityManager.getGoodGuys().size() == 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("TERRORISTS WIN", 0, handler.getHeight()/2);
            g.setColor(Color.RED);
            g.drawString("TERRORISTS WIN", 2, handler.getHeight()/2);
        } else if (entityManager.getZombies().size() == 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString("FUCKBOIS WIN", 0, handler.getHeight()/2);
            g.setColor(Color.RED);
            g.drawString("FUCKBOIS WIN", 2, handler.getHeight()/2);
        }
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
