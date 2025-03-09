
package beans;


public class Ressource {

    private int idRessource;
    private String titreRessource;
    private String urlRessource;
    private Evenement evenement;


    public Ressource(int idRessource, String titreRessource, String urlRessource, Evenement evenement) {
        this.idRessource = idRessource;
        this.titreRessource = titreRessource;
        this.urlRessource = urlRessource;
        this.evenement = evenement;
    }


    public int getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(int idRessource) {
        this.idRessource = idRessource;
    }

    public String getTitreRessource() {
        return titreRessource;
    }

    public void setTitreRessource(String titreRessource) {
        this.titreRessource = titreRessource;
    }

    public String getUrlRessource() {
        return urlRessource;
    }

    public void setUrlRessource(String urlRessource) {
        this.urlRessource = urlRessource;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
