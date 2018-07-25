package projetJava.modele;

import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;
import projetJava.connexions.ConnexionProjet;
import projetJava.vue_controleur.ScreensController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Arc;

public class ModeleJDBC extends Modele {

    private Connection connection;

    private static ModeleJDBC instance = null;

    private ScreensController controllerParent;

    private ModeleJDBC() {

        connection = ConnexionProjet.getInstance();
        if (connection == null) {
            System.err.println("Erreur de connexion ==> arrêt du programme");
            System.exit(1);
        }
    }

    public static ModeleJDBC getInstance() {

        if (instance != null) {
            return instance;
        } else {
            instance = new ModeleJDBC();
            return instance;
        }
    }

    public void close() {

        try {
            connection.close();
        } catch (Exception e) {
            System.err.println("Erreur lors de la fermeture de la connexion " + e);
        }
    }

    public void setController(ScreensController controleurParent) {

        this.controllerParent = controleurParent;
    }

    @Override
    public void populate() {

    }

    @Override
    public Boolean ajoutClasses(Classes classe) {
        String query = "CALL PROJ_AJOUTCLASSE(?, ?, ?)";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {
            callableStatement.setString(1, classe.getSigle());
            callableStatement.setInt(2, classe.getAnnee());
            callableStatement.setString(3, classe.getOrientation());
            callableStatement.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.err.println("Erreur d'ajout de classe " + sqle);
            return false;
        }
    }

    @Override
    public Boolean supClasses(Classes classe) {
        String query = "SELECT ID_CLASSE FROM PROJ_ATTRIBUTION JOIN PROJ_CLASSES ON (ID_CLASSE = PROJ_CLASSES.ID) WHERE PROJ_CLASSES.SIGLE = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, classe.getSigle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //peut pas supprimer
            } else {
                query = "CALL PROJ_SUPCLASSE(?)";
                try (CallableStatement callableStatement = connection.prepareCall(query)) {
                    callableStatement.setString(1, classe.getSigle());
                    callableStatement.executeUpdate();
                    return true;
                } catch (SQLException sqle2) {
                    System.err.println("SQLe2 => Erreur de suppression " + sqle2);
                }
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());

        }
        return false;
    }

    @Override
    public void supClassesTot() {
        List<Classes> mesClasses = new ArrayList<>();
        mesClasses = getClasses();
        mesClasses.forEach((this::supClasses));
    }

    @Override
    public List<Classes> getClasses() {

        List<Classes> mesClasses = new ArrayList<>();
        String query = "SELECT SIGLE, ORIENTATION, ANNEE FROM PROJ_CLASSES ORDER BY ORIENTATION, ANNEE ASC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String sigle = resultSet.getString("SIGLE");
                int anne = resultSet.getInt("ANNEE");
                String orientation = resultSet.getString("ORIENTATION");
                Classes.ClasseBuilder classeBuilder = new Classes.ClasseBuilder().setSigle(sigle).setAnnee(anne).setOrientation(orientation);
                try {
                    Classes classe = classeBuilder.build();
                    mesClasses.add(classe);
                } catch (Exception e) {
                    System.err.println("Erreur de création " + e);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur de recherches des classes " + e);
        }
        return mesClasses;
    }

    public Classes getClasse(int idClasse) {
        String query = "SELECT SIGLE, ORIENTATION, ANNEE FROM PROJ_CLASSES WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idClasse);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String sigle = rs.getString("SIGLE");
                int annee = rs.getInt("ANNEE");
                String orientation = rs.getString("ORIENTATION");
                Classes.ClasseBuilder classeBuilder = new Classes.ClasseBuilder().setSigle(sigle).setAnnee(annee).setOrientation(orientation);
                try {
                    Classes classe = classeBuilder.build();
                    return classe;
                } catch (Exception e) {
                    System.err.println("Erreur de création " + e);
                }
            }
        } catch (SQLException sqle) {
            System.err.println("Erreur blabla " + sqle);
        }
        return null;
    }

    @Override
    public void modifClasse(Classes ancClasse, Classes nouvClasse) {
        String query = "CALL PROJ_MAJCLASSE(?, ?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, nouvClasse.getSigle());
            cs.setInt(2, nouvClasse.getAnnee());
            cs.setString(3, nouvClasse.getOrientation());
            cs.setString(4, ancClasse.getSigle());
            cs.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("Erreur " + sqle);
        }

    }

    @Override
    public void ajoutEnseignants(Enseignant enseignant) {
        String query = "CALL PROJ_AJOUTENSEIGNANT(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, enseignant.getId_prof());
            cs.setString(2, enseignant.getNom());
            cs.setString(3, enseignant.getPrenom());
            cs.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("Erreur d'ajout de l'enseignant " + sqle);
        }
    }

    @Override
    public Boolean supEnseignants(Enseignant enseignant) {
        String query = "SELECT PROJ_ATTRIBUTION.ID_PROF FROM PROJ_ATTRIBUTION JOIN PROJ_ENSEIGNANT ON (PROJ_ATTRIBUTION.ID_PROF= PROJ_ENSEIGNANT.ID) WHERE PROJ_ENSEIGNANT.ID_PROF = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, enseignant.getId_prof());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //peut pas supprimer
            } else {
                query = "CALL PROJ_SUPENSEIGNANT(?)";
                try (CallableStatement callableStatement = connection.prepareCall(query)) {
                    callableStatement.setString(1, enseignant.getId_prof());
                    callableStatement.executeUpdate();
                    return true;
                } catch (SQLException sqle2) {
                    System.err.println("SQLe2 => Erreur de suppression " + sqle2);
                }
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());

        }
        return false;
    }

    @Override
    public void supEnseignantsTot() {
        List<Enseignant> mesEnseignants = new ArrayList<>();
        mesEnseignants = getMesEnseignants();
        mesEnseignants.forEach((this::supEnseignants));
    }

    @Override
    public List<Enseignant> getMesEnseignants() {
        List<Enseignant> mesEnseignants = new ArrayList<>();
        String query = "SELECT ID_PROF, ID_TITULAIRE, ID_REMPLACANT, NOM, PRENOM FROM PROJ_ENSEIGNANT ORDER BY NOM, PRENOM ASC";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String id_prof = rs.getString("ID_PROF");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                int titulaire = rs.getInt("ID_TITULAIRE");
                int remplacant = rs.getInt("ID_REMPLACANT");
                Enseignant.EnseignantBuilder enseignantBuilder = new Enseignant.EnseignantBuilder().setId_prof(id_prof).setNom(nom).setPrenom(prenom);
                try {
                    Enseignant enseignant = enseignantBuilder.build();
                    if (titulaire != 0) {
                        Classes classe = getClasse(titulaire);
                        enseignant.setTitulaire(classe);
                    } else if (remplacant != 0) {
                        Classes classe = getClasse(remplacant);
                        enseignant.setRemplacant(classe);
                    }
                    mesEnseignants.add(enseignant);
                } catch (Exception e) {
                    System.err.println("Erreur de création " + e);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur de recherche des enseignants " + e);
        }
        return mesEnseignants;
    }

    private Enseignant getEnseignant(int idProf) {
        String query = "SELECT ID_PROF, ID_TITULAIRE, ID_REMPLACANT, NOM, PRENOM FROM PROJ_ENSEIGNANT WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idProf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id_prof = rs.getString("ID_PROF");
                int idTitulaire = rs.getInt("ID_TITULAIRE");
                int idRemplacant = rs.getInt("ID_REMPLACANT");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                Enseignant.EnseignantBuilder enseignantBuilder = new Enseignant.EnseignantBuilder().setId_prof(id_prof).setNom(nom).setPrenom(prenom);
                try {
                    Enseignant enseignant = enseignantBuilder.build();
                    if(idTitulaire != 0) {
                        enseignant.setTitulaire(getClasse(idTitulaire));
                    } else if( idRemplacant != 0) {
                        enseignant.setRemplacant(getClasse(idRemplacant));
                    }
                    return enseignant;
                } catch (Exception e) {
                    System.err.println("Erreur de création " + e);
                }
            }
        } catch (SQLException sqle) {
            System.err.println("Erreur blabla " + sqle);
        }
        return null;
    }

    @Override
    public void modifEnseignant(Enseignant ancEnseignant, Enseignant nouvEnseignant) {
    }

    @Override
    public void ajoutAttribution(Attribution attribution) {
        String query = "CALL PROJ_AJOUTATTRIBUTION(?, ?)";
        
    }

    @Override
    public List<Attribution> getMesAttributions() {
        List<Attribution> mesAttributions = new ArrayList<>();
        String query = "SELECT ID_PROF, ID_CLASSE FROM PROJ_ATTRIBUTION ORDER BY ID ASC";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idProf = rs.getInt("ID_PROF");
                int idClasse = rs.getInt("ID_CLASSE");
                Enseignant enseignant = getEnseignant(idProf);
                Classes classe = getClasse(idClasse);
                Attribution attribution = new Attribution(classe, enseignant);
                mesAttributions.add(attribution);

            }
        } catch (Exception e) {
            System.err.println("Erreur de recherches des attributions " + e);
        }
        return mesAttributions;
    }

    @Override
    public void supAttribution(Attribution attribution) {
    }

    @Override
    public void supAttributionTot() {
    }

}
