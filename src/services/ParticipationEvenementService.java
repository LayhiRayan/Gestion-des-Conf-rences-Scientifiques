package services;

import beans.Evenement;
import beans.Intervenant;
import beans.ParticipationEvenement;
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParticipationEvenementService implements IDao<ParticipationEvenement> {

    private Connexion connexion;

    public ParticipationEvenementService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(ParticipationEvenement o) {
        String req = "INSERT INTO ParticipationEvenement (evenement_id, intervenant_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getEvenement().getId());
            ps.setInt(2, o.getIntervenant().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(ParticipationEvenement o) {
        String req = "DELETE FROM ParticipationEvenement WHERE evenement_id = ? AND intervenant_id = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getEvenement().getId());
            ps.setInt(2, o.getIntervenant().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(ParticipationEvenement o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ParticipationEvenement findById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ParticipationEvenement> findAll() {
        List<ParticipationEvenement> participations = new ArrayList<>();
        String req = "SELECT * FROM ParticipationEvenement";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Evenement evenement = new EvenementService().findById(rs.getInt("evenement_id"));
                Intervenant intervenant = new IntervenantService().findById(rs.getInt("intervenant_id"));
                participations.add(new ParticipationEvenement(evenement, intervenant));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participations;
    }
}
