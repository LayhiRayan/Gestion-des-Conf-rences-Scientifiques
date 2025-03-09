package beans;

import java.util.List;

public class Evenement {

    private int id;
    private EThemeEvenement theme;
    private String date;
    private String lieu;
    private List<ParticipationEvenement> participations;

    public Evenement(int id, EThemeEvenement theme, String date, String lieu, List<ParticipationEvenement> participations) {
        this.id = id;
        this.theme = theme;
        this.date = date;
        this.lieu = lieu;
        this.participations = participations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EThemeEvenement getTheme() {
        return theme;
    }

    public void setTheme(EThemeEvenement theme) {
        this.theme = theme;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<ParticipationEvenement> getParticipations() {
        return participations;
    }

    public void setParticipations(List<ParticipationEvenement> participations) {
        this.participations = participations;
    }
}
