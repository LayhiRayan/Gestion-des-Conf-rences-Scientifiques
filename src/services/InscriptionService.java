package services;

import beans.ParticipationEvenement;
import beans.Utilisateur;
import beans.Evenement;
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class InscriptionService implements IDao<ParticipationEvenement> {
    protected Connexion connexion;
    protected EvenementService evenementService;
    protected UtilisateurService utilisateurService;

    public InscriptionService() {
        connexion = Connexion.getInstance();
        evenementService = new EvenementService();
        utilisateurService = new UtilisateurService();
    }

    @Override
    public boolean create(ParticipationEvenement o) {
        String req = "INSERT INTO ParticipationEvenement (idEvenement, idUtilisateur) VALUES (?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getEvenement().getIdEvenement());
            ps.setInt(2, o.getUtilisateur().getIdUtilisateur());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(ParticipationEvenement o) {
        String req = "DELETE FROM ParticipationEvenement WHERE idUtilisateur = ? AND idEvenement = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getUtilisateur().getIdUtilisateur());
            ps.setInt(2, o.getEvenement().getIdEvenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(ParticipationEvenement o) {
        throw new UnsupportedOperationException("Impossible de mettre à jour une table avec une clé composée.");
    }

    @Override
    public abstract ParticipationEvenement findById(int id);

    public ParticipationEvenement findByUserAndEvent(int idUtilisateur, int idEvenement) {
        String req = "SELECT * FROM ParticipationEvenement WHERE idUtilisateur = ? AND idEvenement = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, idUtilisateur);
            ps.setInt(2, idEvenement);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ParticipationEvenement(
                        utilisateurService.findById(rs.getInt("idUtilisateur")),
                        evenementService.findById(rs.getInt("idEvenement"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ParticipationEvenement> findAll() {
        List<ParticipationEvenement> participations = new ArrayList<>();
        String req = "SELECT * FROM ParticipationEvenement";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                participations.add(new ParticipationEvenement(
                        utilisateurService.findById(rs.getInt("idUtilisateur")),
                        evenementService.findById(rs.getInt("idEvenement"))
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participations;
    }
}