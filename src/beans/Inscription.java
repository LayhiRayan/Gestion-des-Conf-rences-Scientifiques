
package beans;


public class Inscription {

    private int idInscription;
    private Utilisateur utilisateur;
    private Evenement evenement;


    public Inscription(int idInscription, Utilisateur utilisateur, Evenement evenement) {
        this.idInscription = idInscription;
        this.utilisateur = utilisateur;
        this.evenement = evenement;
    }


    public int getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
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
