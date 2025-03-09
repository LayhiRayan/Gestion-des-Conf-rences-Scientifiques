package services;

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

public class EvenementService implements IDao<Evenement> {

    private Connexion connexion;

    public EvenementService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Evenement o) {
        String req = "INSERT INTO Evenement (themeEvenement, dateEvenement, lieuEvenement, descriptionEvenement) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getThemeEvenement());
            ps.setString(2, o.getDateEvenement());
            ps.setString(3, o.getLieuEvenement());
            ps.setString(4, o.getDescriptionEvenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Evenement o) {
        String req = "DELETE FROM Evenement WHERE idEvenement = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getIdEvenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Evenement o) {
        String req = "UPDATE Evenement SET themeEvenement = ?, dateEvenement = ?, lieuEvenement = ?, descriptionEvenement = ? WHERE idEvenement = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getThemeEvenement());
            ps.setString(2, o.getDateEvenement());
            ps.setString(3, o.getLieuEvenement());
            ps.setString(4, o.getDescriptionEvenement());
            ps.setInt(5, o.getIdEvenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Evenement findById(int id) {
        String req = "SELECT * FROM Evenement WHERE idEvenement = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Evenement(
                        rs.getInt("idEvenement"),
                        rs.getString("themeEvenement"),
                        rs.getString("dateEvenement"),
                        rs.getString("lieuEvenement"),
                        rs.getString("descriptionEvenement")
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
                        rs.getInt("idEvenement"),
                        rs.getString("themeEvenement"),
                        rs.getString("dateEvenement"),
                        rs.getString("lieuEvenement"),
                        rs.getString("descriptionEvenement")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements;
    }
}
