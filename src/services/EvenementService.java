package services;

import beans.Evenement;
import beans.EThemeEvenement;
import beans.Intervenant;
import connexion.Connexion;
import dao.IDao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EvenementService implements IDao<Evenement> {

    private Connexion connexion;

    public EvenementService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Evenement o) {
        String req = "INSERT INTO Evenement (titre, theme, date, lieu, intervenant_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getTheme().name());
            ps.setDate(3, new Date(o.getDate().getTime()));
            ps.setString(4, o.getLieu());
            ps.setInt(5, o.getIntervenant().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(" Erreur lors de la création de l'événement: " + ex.getMessage());
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
            System.err.println("Erreur lors de la suppression de l'événement: " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Evenement o) {
        String req = "UPDATE Evenement SET titre = ?, theme = ?, date = ?, lieu = ?, intervenant_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getTheme().name());
            ps.setDate(3, new Date(o.getDate().getTime()));
            ps.setString(4, o.getLieu());
            ps.setInt(5, o.getIntervenant().getId());
            ps.setInt(6, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(" Erreur lors de la mise à jour de l'événement: " + ex.getMessage());
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
                IntervenantService is = new IntervenantService();
                return new Evenement(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        EThemeEvenement.valueOf(rs.getString("theme")),
                        rs.getDate("date"),
                        rs.getString("lieu"),
                        is.findById(rs.getInt("intervenant_id"))
                );
            }
        } catch (SQLException ex) {
            System.err.println(" Erreur lors de la récupération de l'événement: " + ex.getMessage());
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
            IntervenantService is = new IntervenantService();
            while (rs.next()) {
                evenements.add(new Evenement(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        EThemeEvenement.valueOf(rs.getString("theme")),
                        rs.getDate("date"),
                        rs.getString("lieu"),
                        is.findById(rs.getInt("intervenant_id"))
                ));
            }
        } catch (SQLException ex) {
            System.err.println(" Erreur lors de la récupération des événements: " + ex.getMessage());
        }
        return evenements;
    }

    public List<Evenement> findByDate(Date date) {
        List<Evenement> evenements = new ArrayList<>();
        String req = "SELECT * FROM Evenement WHERE date = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            IntervenantService is = new IntervenantService();
            while (rs.next()) {
                evenements.add(new Evenement(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        EThemeEvenement.valueOf(rs.getString("theme")),
                        rs.getDate("date"),
                        rs.getString("lieu"),
                        is.findById(rs.getInt("intervenant_id"))
                ));
            }
        } catch (SQLException ex) {
            System.err.println(" Erreur lors de la recherche des événements par date: " + ex.getMessage());
        }
        return evenements;
    }

    public List<Evenement> findByLieu(String lieu) {
        List<Evenement> evenements = new ArrayList<>();
        String req = "SELECT * FROM Evenement WHERE lieu LIKE ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, "%" + lieu + "%"); // Recherche partielle par lieu
            ResultSet rs = ps.executeQuery();
            IntervenantService is = new IntervenantService();
            while (rs.next()) {
                evenements.add(new Evenement(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        EThemeEvenement.valueOf(rs.getString("theme")),
                        rs.getDate("date"),
                        rs.getString("lieu"),
                        is.findById(rs.getInt("intervenant_id"))
                ));
            }
        } catch (SQLException ex) {
            System.err.println(" Erreur lors de la recherche des événements par lieu: " + ex.getMessage());
        }
        return evenements;
    }
}
