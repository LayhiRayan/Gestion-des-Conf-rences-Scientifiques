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

public class IntervenantService implements IDao<Intervenant> {
    private Connexion connexion;

    public IntervenantService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Intervenant o) {
        String req = "INSERT INTO Intervenant (nom, prenom, specialite) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getSpecialite());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Intervenant o) {
        String req = "DELETE FROM Intervenant WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Intervenant o) {
        String req = "UPDATE Intervenant SET nom = ?, prenom = ?, specialite = ? WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getSpecialite());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Intervenant findById(int id) {
        String req = "SELECT * FROM Intervenant WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Intervenant(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("specialite")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(IntervenantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Intervenant> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
