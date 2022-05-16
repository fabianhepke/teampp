package com.teampp.domain.user;

public enum Rank {
    PLAYER("Spieler"),
    Trainer("Trainer"),
    PLAYERADMIN("Spieler/Admin"),
    NORANK("Kein Rang");

    public String rank;
    private Rank(String rank) {
        this.rank = rank;
    }

}
