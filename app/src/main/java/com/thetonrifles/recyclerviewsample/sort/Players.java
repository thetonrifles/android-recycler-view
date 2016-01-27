package com.thetonrifles.recyclerviewsample.sort;

import java.util.ArrayList;

class Players {

    public static ArrayList<Player> getAll() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(1, "James Kub"));
        players.add(new Player(2, "Peter Hanly"));
        players.add(new Player(3, "Josh Penny"));
        players.add(new Player(1, "Danny Jackson"));
        players.add(new Player(3, "Brad Black"));
        return players;
    }

}
