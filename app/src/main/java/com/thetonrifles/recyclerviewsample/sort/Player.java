package com.thetonrifles.recyclerviewsample.sort;

import android.support.annotation.NonNull;

class Player implements Comparable<Player> {

    private int rank;
    private String name;

    public Player(int rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Player another) {
        int diff = rank - another.rank;
        return (diff==0) ? name.compareTo(another.name) : diff;
    }

}
