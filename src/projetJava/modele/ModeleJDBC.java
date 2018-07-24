package projetJava.modele;

import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;
import projetJava.connexions.ConnexionProjet;
import projetJava.vue_controleur.ScreensController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeleJDBC extends Modele {

    private Connection connection;

    private static ModeleJDBC instance = null;

    private ScreensController controllerParent;

    private ModeleJDBC () {

        connection = ConnexionProjet.getInstance();
        if ( connection == null ) {
            System.err.println("Erreur de connexion ==> arrêt du programme");
            System.exit(1);
        }
    }

    public static ModeleJDBC getInstance () {

        if ( instance != null ) {
            return instance;
        } else {
            instance = new ModeleJDBC();
            return instance;
        }
    }

    public void close () {

        try {
            connection.close();
        } catch ( Exception e ) {
            System.err.println("Erreur lors de la fermeture de la connexion " + e);
        }
    }

    public void setController (ScreensController controleurParent) {

        this.controllerParent = controleurParent;
    }

    @Override
    public void populate () {

    }

    @Override
    public Boolean ajoutClasses (Classes classe) {
        String query = "CALL PROJ_AJOUTCLASSE(?, ?, ?)";
        try( CallableStatement callableStatement = connection.prepareCall(query) ) {
            callableStatement.setString(1, classe.getSigle());
            callableStatement.setInt(2, classe.getAnnee());
            callableStatement.setString(3, classe.getOrientation());
            callableStatement.executeUpdate();
            return true;
        } catch ( SQLException sqle ) {
            System.err.println("Errur d'ajout de classe " + sqle);
            return false;
        }
    }

    @Override
    public void supClasses(Classes classe) {
        String query = "SELECT ID_CLASSE FROM PROJ_ATTRIBUTION JOIN PROJ_CLASSES ON (ID_CLASSE = PROJ_CLASSES.ID) WHERE PROJ_CLASSES.SIGLE = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, classe.getSigle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                //peut pas supprimer
            } else {
                query = "CALL PROJ_SUPCLASSE(?)";
                try(CallableStatement callableStatement = connection.prepareCall(query)) {
                    callableStatement.setString(1, classe.getSigle());
                    callableStatement.executeUpdate();
                } catch ( SQLException sqle2 ) {
                    System.err.println("SQLe2 => Erreur de suppression " + sqle2);
                }
            }
        }catch ( SQLException sqle ) {
            System.err.println(sqle.getMessage());

        }
    }

    @Override
    public void supClassesTot() {
    }

    @Override
    public List<Classes> getClasses() {

        List<Classes> mesClasses = new ArrayList<>();
        String query = "SELECT SIGLE, ORIENTATION, ANNEE FROM PROJ_CLASSES ORDER BY SIGLE ASC";
        try( PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery() ) {

            while ( resultSet.next() ) {
                String sigle = resultSet.getString("SIGLE");
                int anne = resultSet.getInt("ANNEE");
                String orientation = resultSet.getString("ORIENTATION");
                Classes.ClasseBuilder classeBuilder = new Classes.ClasseBuilder().setSigle(sigle).setAnnee(anne).setOrientation(orientation);
                try {
                    Classes classe = classeBuilder.build();
                    mesClasses.add(classe);
                } catch ( Exception e ) {
                    System.err.println("Erreur de création " + e);
                }
            }
        }catch ( Exception e ) {
            System.err.println("Erreur de recherches des classes " + e);
        }
        return mesClasses;
    }

    @Override
    public Classes getClasse(Classes classe) {
        return null;
    }

    @Override
    public void modifClasse(Classes ancClasse, Classes nouvClasse) {
    }

    @Override
    public void ajoutEnseignants(Enseignant enseignant) {
    }

    @Override
    public void supEnseignants(Enseignant enseignant) {
    }

    @Override
    public void supEnseignantsTot() {
    }

    @Override
    public List<Enseignant> getMesEnseignants() {
        return null;
    }

    @Override
    public void modifEnseignant(Enseignant ancEnseignant, Enseignant nouvEnseignant) {
    }

    @Override
    public void ajoutAttribution (Attribution attribution) {
    }

    @Override
    public List<Attribution> getMesAttributions() {
        return null;
    }

    @Override
    public void supAttribution(Attribution attribution) {
    }

    @Override
    public void supAttributionTot() {
    }

}
