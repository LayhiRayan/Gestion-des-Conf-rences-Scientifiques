
package services;
import beans.Ressource;
import beans.Utilisateur;
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class RessourceService implements IDao<Ressource> {
    private Connexion connexion;

    public RessourceService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Ressource o) {
        String req = "INSERT INTO Ressource (titreRessource, urlRessource, idEvenement) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getTitreRessource());
            ps.setString(2, o.getUrlRessource());
            ps.setInt(3, o.getEvenement().getIdEvenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RessourceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Ressource o) {
        String req = "DELETE FROM Ressource WHERE idRessource = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getIdRessource());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RessourceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Ressource o) {
        String req = "UPDATE Ressource SET titreRessource = ?, urlRessource = ?, idEvenement = ? WHERE idRessource = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getTitreRessource());
            ps.setString(2, o.getUrlRessource());
            ps.setInt(3, o.getEvenement().getIdEvenement());
            ps.setInt(4, o.getIdRessource());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RessourceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Ressource findById(int id) {
        String req = "SELECT * FROM Ressource WHERE idRessource = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ressource(
                        rs.getInt("idRessource"),
                        rs.getString("titreRessource"),
                        rs.getString("urlRessource"),
                        new EvenementService().findById(rs.getInt("idEvenement"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(RessourceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Ressource> findAll() {
        List<Ressource> ressources = new ArrayList<>();
        String req = "SELECT * FROM Ressource";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ressources.add(new Ressource(
                        rs.getInt("idRessource"),
                        rs.getString("titreRessource"),
                        rs.getString("urlRessource"),
                        new EvenementService().findById(rs.getInt("idEvenement"))
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RessourceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ressources;
    }
}