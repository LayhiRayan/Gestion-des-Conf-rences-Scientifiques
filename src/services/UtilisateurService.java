package services;

import beans.ERoleUtilisateur;
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

public class UtilisateurService implements IDao<Utilisateur> {

    private Connexion connexion;

    public UtilisateurService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Utilisateur o) {
        String req = "INSERT INTO Utilisateur (nomUtilisateur, prenomUtilisateur, emailUtilisateur, motDePasseUtilisateur, roleUtilisateur) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getNomUtilisateur());
            ps.setString(2, o.getPrenomUtilisateur());
            ps.setString(3, o.getEmailUtilisateur());
            ps.setString(4, o.getMotDePasseUtilisateur());
            ps.setString(5, o.getRoleUtilisateur().toString());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Utilisateur o) {
        String req = "DELETE FROM Utilisateur WHERE idUtilisateur = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getIdUtilisateur());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Utilisateur o) {
        String req = "UPDATE Utilisateur SET nomUtilisateur = ?, prenomUtilisateur = ?, emailUtilisateur = ?, motDePasseUtilisateur = ?, roleUtilisateur = ? WHERE idUtilisateur = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getNomUtilisateur());
            ps.setString(2, o.getPrenomUtilisateur());
            ps.setString(3, o.getEmailUtilisateur());
            ps.setString(4, o.getMotDePasseUtilisateur());
            ps.setString(5, o.getRoleUtilisateur().toString());
            ps.setInt(6, o.getIdUtilisateur());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Utilisateur findById(int id) {
        String req = "SELECT * FROM Utilisateur WHERE idUtilisateur = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("idUtilisateur"),
                        rs.getString("nomUtilisateur"),
                        rs.getString("prenomUtilisateur"),
                        rs.getString("emailUtilisateur"),
                        rs.getString("motDePasseUtilisateur"),
                        ERoleUtilisateur.valueOf(rs.getString("roleUtilisateur"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM Utilisateur";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                utilisateurs.add(new Utilisateur(
                        rs.getInt("idUtilisateur"),
                        rs.getString("nomUtilisateur"),
                        rs.getString("prenomUtilisateur"),
                        rs.getString("emailUtilisateur"),
                        rs.getString("motDePasseUtilisateur"),
                        ERoleUtilisateur.valueOf(rs.getString("roleUtilisateur"))
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return utilisateurs;
    }
}
