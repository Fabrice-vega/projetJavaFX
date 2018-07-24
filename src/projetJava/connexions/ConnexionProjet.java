package projetJava.connexions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.PropertyResourceBundle;

/**
 * @Titre_Projet: ProjetJavaFX
 * @Titre_Class: ConnexionProjet
 * @Auteur: Gaetan
 * @Créé_le: 24/07/18
 */
public class ConnexionProjet {


    private static Connection connection = null;

    private ConnexionProjet () {

    }

    public static Connection getInstance() {
        if ( connection != null ) {
            return connection;
        }
        PropertyResourceBundle properties = (PropertyResourceBundle) PropertyResourceBundle.getBundle("projetJava.resource.properties");
        String server = properties.getString("server");
        String database = properties.getString("database");
        String login = properties.getString("login");
        String password = properties.getString("password");
        String port = properties.getString("port");
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@//" + server + ":" + port + "/" + database;
            connection = DriverManager.getConnection(url, login, password);
            return connection;
        } catch ( Exception e ) {
            System.err.println("Erreur de connexion " + e);
            e.printStackTrace();
            return null;
        }
    }

    public static void close () {

        try {
            connection.close();
        } catch ( Exception e ) {
            System.err.println("Erreur de fermeture de connexion " + e);
        }
        connection = null;
    }

}
