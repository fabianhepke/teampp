package com.example.team.components;

import com.example.team.help.Token;

import java.util.Date;

public abstract class User {
    String benutzername, eMail, passwort;
    Token loginToken, verifyToken;
    Date erstellungsDatum;

    public User(String benutzername, String eMail, String passwort, Date erstellungsDatum) {
        this.benutzername = benutzername;
        this.eMail = eMail;
        this.passwort = passwort;
        this.erstellungsDatum = erstellungsDatum;
        loginToken = new Token(25);
        verifyToken = new Token(25);
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Date getErstellungsDatum() {
        return erstellungsDatum;
    }

    public void setErstellungsDatum(Date erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }




}
