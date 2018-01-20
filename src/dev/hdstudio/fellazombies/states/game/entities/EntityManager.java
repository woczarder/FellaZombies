package dev.hdstudio.fellazombies.states.game.entities;

import dev.hdstudio.fellazombies.Handler;
import dev.hdstudio.fellazombies.states.game.GameLauncher;
import dev.hdstudio.fellazombies.states.game.entities.creatures.GoodGuy;
import dev.hdstudio.fellazombies.states.game.entities.creatures.Zombie;


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager {

    public static int numberOfZombies = 0, numberOfCivilians = 0, numberOfHunters = 0;

    private ArrayList<GoodGuy> goodGuys;
    private ArrayList<Zombie> zombies;
    private ArrayList<Zombie> zombiesToSpawn;

    private Handler handler;


    public EntityManager(Handler handler) {
        this.handler = handler;

        goodGuys = new ArrayList<>();
        zombies = new ArrayList<>();
        zombiesToSpawn = new ArrayList<>();
    }

    public void tick() {
        Iterator<GoodGuy> it = goodGuys.iterator();
        while(it.hasNext()){
            GoodGuy e = it.next();
            e.tick();
            if(!e.isAlive()) {
                it.remove();
                if (e.isHunter())
                    numberOfHunters--;
                else
                    numberOfCivilians--;
            }
        }

        Iterator<Zombie> it2 = zombies.iterator();
        while(it2.hasNext()){
            Zombie e = it2.next();
            e.tick();
            if(!e.isAlive()) {
                it2.remove();
                numberOfZombies--;
            }
        }

        Iterator<Zombie> it3 = zombiesToSpawn.iterator();
        while(it3.hasNext()){
            Zombie e = it3.next();
            addZombie(e);
            it3.remove();
        }
    }

    public void render(Graphics g) {
        for (Entity c : goodGuys) {
            c.render(g);
        }

        for (Entity z: zombies) {
            z.render(g);
        }
    }

    // getters and setters and shit

    public void addGoodGuy(GoodGuy c) {
        goodGuys.add(c);
        c.setHandler(handler);
        c.setEntityManager(this);
    }

    public void addZombie(Zombie z) {
        zombies.add(z);
        z.setHandler(handler);
        z.setEntityManager(this);
    }

    public void addToSpawn(Zombie z) {
        zombiesToSpawn.add(new Zombie(z.getX(), z.getY(), GameLauncher.zombieSpeed));
    }

    public ArrayList<GoodGuy> getGoodGuys() {
        return goodGuys;
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

}
