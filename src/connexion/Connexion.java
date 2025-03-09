package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connexion instance = null;
    private Connection connexion = null;
    
    private final String urlBase = "jdbc:mysql://localhost:3306/GestionConferences";
    private final String utilisateurBase = "root";
    private final String motDePasseBase = "";

    private Connexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(urlBase, utilisateurBase, motDePasseBase);
        } catch (ClassNotFoundException ex) {
            System.out.println("Pilote introuvable");
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion : " + ex.getMessage());
        }
    }

    public static synchronized Connexion getInstance() {
        if (instance == null) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }
}
