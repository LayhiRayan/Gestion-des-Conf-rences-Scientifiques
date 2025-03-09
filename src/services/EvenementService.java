package services;

import beans.Evenement;
import beans.EThemeEvenement;
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

public class EvenementService implements IDao<Evenement> {

    private Connexion connexion;

    public EvenementService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Evenement o) {
        String req = "INSERT INTO Evenement (theme, date, lieu) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getTheme().name());
            ps.setString(2, o.getDate());
            ps.setString(3, o.getLieu());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Evenement o) {
        String req = "DELETE FROM Evenement WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Evenement o) {
        String req = "UPDATE Evenement SET theme = ?, date = ?, lieu = ? WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getTheme().name());
            ps.setString(2, o.getDate());
            ps.setString(3, o.getLieu());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Evenement findById(int id) {
        String req = "SELECT * FROM Evenement WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Evenement(
                        rs.getInt("id"),
                        EThemeEvenement.valueOf(rs.getString("theme")),
                        rs.getString("date"),
                        rs.getString("lieu"),
                        new ArrayList<ParticipationEvenement>()
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Evenement> findAll() {
        List<Evenement> evenements = new ArrayList<>();
        String req = "SELECT * FROM Evenement";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                evenements.add(new Evenement(
                        rs.getInt("id"),
                        EThemeEvenement.valueOf(rs.getString("theme")),
                        rs.getString("date"),
                        rs.getString("lieu"),
                        new ArrayList<ParticipationEvenement>()
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements;
    }
}
