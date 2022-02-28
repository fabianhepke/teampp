package com.teampp.domain.entities.enums;

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
