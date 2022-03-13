package com.teampp.domain.valueobjects;

import com.teampp.domain.entities.exceptions.WrongInputSyntaxException;

import java.util.Objects;

public final class Adress {
    private final String place;
    private final String street;
    private final int plz;
    private final String houseNr;

    public Adress(int plz, String place, String street, String houseNr) {
        if (plz<0 || String.valueOf(plz).length() != 5) {
            throw new WrongInputSyntaxException("Falsches plz Format");
        }
        this.place = place;
        this.street = street;
        this.plz = plz;
        this.houseNr = houseNr.toUpperCase();
    }

    public Adress() {
        this.place = "Daheim";
        this.street = null;
        this.plz = 0;
        this.houseNr = null;
    }

    public String getPlace() {
        return place;
    }

    public String getStreet() {
        return street;
    }

    public int getPlz() {
        return plz;
    }

    public String getHouseNr() {
        return houseNr;
    }

    @Override
    public String toString() {
        return plz + " " +  place + " " + street + " " + houseNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return plz == adress.plz &&
                Objects.equals(place, adress.place) &&
                Objects.equals(street, adress.street) &&
                Objects.equals(houseNr, adress.houseNr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, street, plz, houseNr);
    }
}
