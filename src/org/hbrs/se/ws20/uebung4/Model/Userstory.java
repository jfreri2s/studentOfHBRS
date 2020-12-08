package org.hbrs.se.ws20.uebung4.Model;

import java.io.Serializable;

public class Userstory implements Serializable {
    private int id;
    private double prio;
    private String text;
    private String kriterien;
    private int mehrwert;
    private int strafe;
    private int aufwand;
    private int risiko;

    public Userstory(int id, String text, String kriterien, int mehrwert, int strafe, int aufwand, int risiko){
        this.id = id;
        this.text = text;
        this.kriterien = kriterien;
        this.mehrwert = mehrwert;
        this.strafe = strafe;
        this.aufwand = aufwand;
        this.risiko = risiko;
        setPrio(mehrwert, strafe, aufwand, risiko);
    }

    public Double getPrio(){
        return prio;
    }
    public void setPrio(int mehrwert, int strafe, int aufwand, int risiko) {
        double nenner = aufwand + risiko;
        if (nenner <= 0 || risiko < 0) {
            throw new IllegalArgumentException("Ungültige Eingabe");
        }

        this.prio = (mehrwert + strafe) / nenner;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText(){
        return text;
    }
    public void setKriterien(String krit){
        this.kriterien = krit;
    }
    public String getKriterien(){
        return kriterien;
    }
    public int getAufwand() {
        return aufwand;
    }
    public void setAufwand(int aufwand) {
        this.aufwand = aufwand;
    }
    public Integer getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Integer getMehrwert() {
        return mehrwert;
    }
    public void setMehrwert(int mehrwert) {
        this.mehrwert = mehrwert;
    }
    public Integer getRisk() {
        return risiko;
    }
    public void setRisk(int risiko) {
        this.risiko = risiko;
    }
    public Integer getStrafe() {
        return strafe;
    }
    public void setStrafe(int strafe) {
        this.strafe = strafe;
    }
    public String toString(){
        return "" + text + " " + kriterien + " Prio: " + getPrio();
    }
}
