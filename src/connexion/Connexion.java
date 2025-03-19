package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private static Connexion instance = null;
    private Connection connexion = null;

    private final String urlBase = "jdbc:mysql://localhost:3306/gestionconferences?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String utilisateurBase = "root";
    private final String motDePasseBase = "";

    private Connexion() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connexion = DriverManager.getConnection(urlBase, utilisateurBase, motDePasseBase);
            System.out.println("Connexion réussie à la base de données !");
        } catch (ClassNotFoundException ex) {
            System.err.println("Pilote MySQL introuvable !");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.err.println("Erreur de connexion : " + ex.getMessage());
            ex.printStackTrace();
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

    public static void main(String[] args) {
        Connexion connexionTest = Connexion.getInstance();
        if (connexionTest.getConnexion() != null) {
            System.out.println("Connexion établie avec succès !");
        } else {
            System.err.println("Échec de la connexion !");
        }
    }
}
