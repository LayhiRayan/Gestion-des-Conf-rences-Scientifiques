package beans;

import java.util.ArrayList;
import java.util.List;

public class Evenement {

    private int idEvenement;
    private String themeEvenement;
    private String dateEvenement;
    private String lieuEvenement;
    private String descriptionEvenement;
    private List<Utilisateur> utilisateursInscrits;
    private List<Ressource> ressources;

    public Evenement(int idEvenement, String themeEvenement, String dateEvenement, String lieuEvenement, String descriptionEvenement) {
        this.idEvenement = idEvenement;
        this.themeEvenement = themeEvenement;
        this.dateEvenement = dateEvenement;
        this.lieuEvenement = lieuEvenement;
        this.descriptionEvenement = descriptionEvenement;
        this.utilisateursInscrits = new ArrayList<>();
        this.ressources = new ArrayList<>();
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getThemeEvenement() {
        return themeEvenement;
    }

    public void setThemeEvenement(String themeEvenement) {
        this.themeEvenement = themeEvenement;
    }

    public String getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(String dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public List<Utilisateur> getUtilisateursInscrits() {
        return utilisateursInscrits;
    }

    public void addUtilisateurInscrit(Utilisateur utilisateur) {
        this.utilisateursInscrits.add(utilisateur);
    }

    public List<Ressource> getRessources() {
        return ressources;
    }

    public void addRessource(Ressource ressource) {
        this.ressources.add(ressource);
    }
}