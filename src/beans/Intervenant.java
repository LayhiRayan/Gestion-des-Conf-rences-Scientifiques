package beans;

public class Intervenant {
    private Utilisateur utilisateur;
    private String specialite;

    public Intervenant(Utilisateur utilisateur, String specialite) {
        this.utilisateur = utilisateur;
        this.specialite = specialite;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
