package test;

import beans.Evenement;
import beans.EThemeEvenement;
import beans.Intervenant;
import beans.ParticipationEvenement;
import services.EvenementService;
import services.IntervenantService;
import services.ParticipationEvenementService;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        EvenementService evenementService = new EvenementService();
        IntervenantService intervenantService = new IntervenantService();
        ParticipationEvenementService participationService = new ParticipationEvenementService();

        Evenement evenement1 = new Evenement(0, EThemeEvenement.TECHNOLOGIE, "2025-06-10", "Rabat", null);
        Evenement evenement2 = new Evenement(0, EThemeEvenement.SCIENCE, "2025-07-15", "Marrakech", null);
        Evenement evenement3 = new Evenement(0, EThemeEvenement.INNOVATION, "2025-08-20", "Tanger", null);
        Evenement evenement4 = new Evenement(0, EThemeEvenement.EDUCATION, "2025-09-25", "Marrakech", null);

        evenementService.create(evenement1);
        evenementService.create(evenement2);
        evenementService.create(evenement3);
        evenementService.create(evenement4);

        List<Evenement> evenements = evenementService.findAll();
        System.out.println("\n📌 Liste des événements :");
        for (Evenement e : evenements) {
            System.out.println(e.getId() + " - " + e.getTheme() + " - " + e.getDate() + " - " + e.getLieu());
        }

        Intervenant intervenant1 = new Intervenant(0, "LAYHI", "Rayan", "Intelligence Artificielle");
        Intervenant intervenant2 = new Intervenant(0, "ALAMI", "Redouan", "CyberSécurité");
        Intervenant intervenant3 = new Intervenant(0, "ZAHID", "Noureddine", "Data Science");
        Intervenant intervenant4 = new Intervenant(0, "LACHGAR", "Mohamed", "Technologies Web");

        intervenantService.create(intervenant1);
        intervenantService.create(intervenant2);
        intervenantService.create(intervenant3);
        intervenantService.create(intervenant4);

        List<Intervenant> intervenants = intervenantService.findAll();
        System.out.println("\n📌 Liste des intervenants :");
        for (Intervenant i : intervenants) {
            System.out.println(i.getId() + " - " + i.getNom() + " " + i.getPrenom() + " - " + i.getSpecialite());
        }

        if (!evenements.isEmpty() && !intervenants.isEmpty()) {
            participationService.create(new ParticipationEvenement(evenements.get(0), intervenants.get(0)));
            participationService.create(new ParticipationEvenement(evenements.get(1), intervenants.get(1)));
            participationService.create(new ParticipationEvenement(evenements.get(2), intervenants.get(2)));
            participationService.create(new ParticipationEvenement(evenements.get(3), intervenants.get(3)));
        }

        List<ParticipationEvenement> participations = participationService.findAll();
        System.out.println("\n📌 Liste des participations :");
        for (ParticipationEvenement p : participations) {
            System.out.println("Événement : " + p.getEvenement().getTheme() + " | Intervenant : " + p.getIntervenant().getNom());
        }

        String dateRecherche = "2025-06-10";
        System.out.println("\n📅 Recherche des événements par date (" + dateRecherche + ") :");
        for (Evenement e : evenementService.findByDate(dateRecherche)) {
            System.out.println(e.getId() + " - " + e.getTheme() + " - " + e.getDate() + " - " + e.getLieu());
        }

        String lieuRecherche = "Marrakech";
        System.out.println("\n📍 Recherche des événements par lieu (" + lieuRecherche + ") :");
        for (Evenement e : evenementService.findByLieu(lieuRecherche)) {
            System.out.println(e.getId() + " - " + e.getTheme() + " - " + e.getDate() + " - " + e.getLieu());
        }

        String nomRecherche = "LAYHI";
        System.out.println("\n🔍 Recherche d'un intervenant par nom (" + nomRecherche + ") :");
        for (Intervenant i : intervenantService.findByNom(nomRecherche)) {
            System.out.println(i.getId() + " - " + i.getNom() + " " + i.getPrenom() + " - " + i.getSpecialite());
        }
    }
}
