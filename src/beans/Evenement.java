/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author HP-PC
 */
import beans.EThemeEvenement;

import java.util.Date;

public class Evenement {

    private int id;
    private String titre;
    private EThemeEvenement theme;
    private Date date;
    private String lieu;
    private Intervenant intervenant;

    public Evenement(int id, String titre, EThemeEvenement theme, Date date, String lieu, Intervenant intervenant) {
        this.id = id;
        this.titre = titre;
        this.theme = theme;
        this.date = date;
        this.lieu = lieu;
        this.intervenant = intervenant;
    }

    public Evenement(String titre, EThemeEvenement theme, Date date, String lieu, Intervenant intervenant) {
        this.titre = titre;
        this.theme = theme;
        this.date = date;
        this.lieu = lieu;
        this.intervenant = intervenant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public EThemeEvenement getTheme() {
        return theme;
    }

    public void setTheme(EThemeEvenement theme) {
        this.theme = theme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }
}
