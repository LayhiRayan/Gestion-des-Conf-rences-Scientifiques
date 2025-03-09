package services;

import beans.ParticipationEvenement;
import beans.Utilisateur;
import beans.Evenement;
import beans.ERoleUtilisateur;
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ParticipationEvenementService implements IDao<ParticipationEvenement> {
    protected Connexion connexion;
    protected UtilisateurService utilisateurService;
    protected EvenementService evenementService;

    public ParticipationEvenementService() {
        connexion = Connexion.getInstance();
        utilisateurService = new UtilisateurService();
        evenementService = new EvenementService();
    }

    @Override
    public boolean create(ParticipationEvenement o) {
        String req = "INSERT INTO ParticipationEvenement (idUtilisateur, idEvenement) VALUES (?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getUtilisateur().getIdUtilisateur());
            ps.setInt(2, o.getEvenement().getIdEvenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, ex);
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
                Utilisateur utilisateur = utilisateurService.findById(rs.getInt("idUtilisateur"));
                Evenement evenement = evenementService.findById(rs.getInt("idEvenement"));
                
                if (utilisateur.getRoleUtilisateur() == ERoleUtilisateur.INTERVENANT) {
                    return new ParticipationEvenement(utilisateur, evenement);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, ex);
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
                Utilisateur utilisateur = utilisateurService.findById(rs.getInt("idUtilisateur"));
                Evenement evenement = evenementService.findById(rs.getInt("idEvenement"));
                
                if (utilisateur.getRoleUtilisateur() == ERoleUtilisateur.INTERVENANT) {
                    participations.add(new ParticipationEvenement(utilisateur, evenement));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participations;
    }
}
