package beans;

import beans.Evenement;
import java.util.List;
import java.util.ArrayList;

public class Utilisateur {

    private int idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String emailUtilisateur;
    private String motDePasseUtilisateur;
    private ERoleUtilisateur roleUtilisateur;
    private List<Evenement> evenementsInscrits;
    private List<Avis> avisLaisse;


    public Utilisateur(int idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String motDePasseUtilisateur, ERoleUtilisateur roleUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.motDePasseUtilisateur = motDePasseUtilisateur;
        this.roleUtilisateur = roleUtilisateur;
        this.evenementsInscrits = new ArrayList<>();
        this.avisLaisse = new ArrayList<>();
    }


    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getMotDePasseUtilisateur() {
        return motDePasseUtilisateur;
    }

    public void setMotDePasseUtilisateur(String motDePasseUtilisateur) {
        this.motDePasseUtilisateur = motDePasseUtilisateur;
    }

    public ERoleUtilisateur getRoleUtilisateur() {
        return roleUtilisateur;
    }

    public void setRoleUtilisateur(ERoleUtilisateur roleUtilisateur) {
        this.roleUtilisateur = roleUtilisateur;
    }

    public List<Evenement> getEvenementsInscrits() {
        return evenementsInscrits;
    }

    public void ajouterEvenementInscrit(Evenement evenement) {
        this.evenementsInscrits.add(evenement);
    }

    public List<Avis> getAvisLaisse() {
        return avisLaisse;
    }

    public void ajouterAvis(Avis avis) {
        this.avisLaisse.add(avis);
    }
}
