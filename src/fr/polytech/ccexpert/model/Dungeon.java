package fr.polytech.ccexpert.model;

import com.codename1.io.URL;

import java.util.List;

public class Dungeon {
    private URL urlYoutube;
    private int door;
    private int base;
    private List<Hero> heroes;

    public Dungeon(URL urlYoutube, int door, int base, List<Hero> heroes) {
        this.urlYoutube = urlYoutube;
        this.door = door;
        this.base = base;
        this.heroes = heroes;
    }

    public URL getUrlYoutube() {
        return urlYoutube;
    }

    public int getDoor() {
        return door;
    }

    public int getBase() {
        return base;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }
}