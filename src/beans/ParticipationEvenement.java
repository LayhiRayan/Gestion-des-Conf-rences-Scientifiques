package beans;

public class ParticipationEvenement {

    private Evenement evenement;
    private Intervenant intervenant;

    public ParticipationEvenement(Evenement evenement, Intervenant intervenant) {
        this.evenement = evenement;
        this.intervenant = intervenant;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }
}
