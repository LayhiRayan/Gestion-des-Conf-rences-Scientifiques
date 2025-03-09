package beans;

public class ParticipationEvenement {
    private Utilisateur utilisateur;
    private Evenement evenement;

    public ParticipationEvenement(Utilisateur utilisateur, Evenement evenement) {
        this.utilisateur = utilisateur;
        this.evenement = evenement;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
