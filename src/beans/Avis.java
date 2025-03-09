
package beans;


public class Avis {

    private int idAvis;
    private Utilisateur utilisateur;
    private Evenement evenement;
    private String commentaireAvis;
    private int noteAvis;

    public Avis(Utilisateur utilisateur, Evenement evenement, String commentaireAvis, int noteAvis) {
    this.utilisateur = utilisateur;
    this.evenement = evenement;
    this.commentaireAvis = commentaireAvis;
    this.noteAvis = noteAvis;
}


    public int getIdAvis() {
        return idAvis;
    }

    public void setIdAvis(int idAvis) {
        this.idAvis = idAvis;
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

    public String getCommentaireAvis() {
        return commentaireAvis;
    }

    public void setCommentaireAvis(String commentaireAvis) {
        this.commentaireAvis = commentaireAvis;
    }

    public int getNoteAvis() {
        return noteAvis;
    }

    public void setNoteAvis(int noteAvis) {
        this.noteAvis = noteAvis;
    }
}
