package services;

import beans.Avis;
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

public abstract class AvisService implements IDao<Avis> {
    protected Connexion connexion;
    protected UtilisateurService utilisateurService;
    protected EvenementService evenementService;

    public AvisService() {
        connexion = Connexion.getInstance();
        utilisateurService = new UtilisateurService();
        evenementService = new EvenementService();
    }

    @Override
    public boolean create(Avis o) {
        String req = "INSERT INTO Avis (idUtilisateur, idEvenement, commentaireAvis, noteAvis) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getUtilisateur().getIdUtilisateur());
            ps.setInt(2, o.getEvenement().getIdEvenement());
            ps.setString(3, o.getCommentaireAvis());
            ps.setInt(4, o.getNoteAvis());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Avis o) {
        String req = "DELETE FROM Avis WHERE idUtilisateur = ? AND idEvenement = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, o.getUtilisateur().getIdUtilisateur());
            ps.setInt(2, o.getEvenement().getIdEvenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Avis o) {
        String req = "UPDATE Avis SET commentaireAvis = ?, noteAvis = ? WHERE idUtilisateur = ? AND idEvenement = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setString(1, o.getCommentaireAvis());
            ps.setInt(2, o.getNoteAvis());
            ps.setInt(3, o.getUtilisateur().getIdUtilisateur());
            ps.setInt(4, o.getEvenement().getIdEvenement());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public abstract Avis findById(int id);

    public Avis findByUserAndEvent(int idUtilisateur, int idEvenement) {
        String req = "SELECT * FROM Avis WHERE idUtilisateur = ? AND idEvenement = ?";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ps.setInt(1, idUtilisateur);
            ps.setInt(2, idEvenement);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Avis(
                        utilisateurService.findById(rs.getInt("idUtilisateur")),
                        evenementService.findById(rs.getInt("idEvenement")),
                        rs.getString("commentaireAvis"),
                        rs.getInt("noteAvis")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Avis> findAll() {
        List<Avis> avisList = new ArrayList<>();
        String req = "SELECT * FROM Avis";
        try {
            PreparedStatement ps = connexion.getConnexion().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                avisList.add(new Avis(
                        utilisateurService.findById(rs.getInt("idUtilisateur")),
                        evenementService.findById(rs.getInt("idEvenement")),
                        rs.getString("commentaireAvis"),
                        rs.getInt("noteAvis")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return avisList;
    }
}