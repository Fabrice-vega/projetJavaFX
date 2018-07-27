package projetJava.modele;

import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;
import projetJava.connexions.ConnexionProjet;
import projetJava.vue_controleur.ScreensController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
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
        } catch (SQLIntegrityConstraintViolationException doublon) {
            return false;
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
    public Boolean modifClasse(Classes ancClasse, Classes nouvClasse) {
        String query = "CALL PROJ_MAJCLASSE(?, ?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, nouvClasse.getSigle());
            cs.setInt(2, nouvClasse.getAnnee());
            cs.setString(3, nouvClasse.getOrientation());
            cs.setString(4, ancClasse.getSigle());
            cs.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException doublon) {
            Alert alertDoublon = new Alert(Alert.AlertType.ERROR, "Classe existant");
            alertDoublon.setTitle("Erreur...");
            alertDoublon.show();
            return false;
        } catch (SQLException sqle) {
            System.err.println("Erreur " + sqle);
            return false;
        }

    }

    @Override
    public Boolean ajoutEnseignants(Enseignant enseignant) {
        String query = "CALL PROJ_AJOUTENSEIGNANT(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, enseignant.getId_prof());
            cs.setString(2, enseignant.getNom());
            cs.setString(3, enseignant.getPrenom());
            cs.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException doublon) {
            Alert alertDoublon = new Alert(Alert.AlertType.ERROR, "Enseignant déjà créée");
            alertDoublon.setTitle("Erreur...");
            alertDoublon.show();
            return false;
        } catch (SQLException sqle) {
            System.err.println("Erreur d'ajout de l'enseignant " + sqle);
            return false;
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
                    enseignant.add(controllerParent);
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
                    if (idTitulaire != 0) {
                        enseignant.setTitulaire(getClasse(idTitulaire));
                    } else if (idRemplacant != 0) {
                        enseignant.setRemplacant(getClasse(idRemplacant));
                    }
                    enseignant.add(controllerParent);
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
    public Boolean modifEnseignant(Enseignant ancEnseignant, Enseignant nouvEnseignant) {
        String query = "CALL PROJ_MAJENSEIGNANT(?, ?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, nouvEnseignant.getId_prof());
            cs.setString(2, nouvEnseignant.getNom());
            cs.setString(3, nouvEnseignant.getPrenom());
            cs.setString(4, ancEnseignant.getId_prof());
            cs.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException doublon) {
            Alert alertDoublon = new Alert(Alert.AlertType.ERROR, "Enseignant existant");
            alertDoublon.setTitle("Erreur...");
            alertDoublon.show();
            return false;
        } catch (SQLException sqle) {
            System.err.println("Erreur " + sqle);
            return false;
        }
    }

    @Override
    public void ajoutAttribution(Attribution attribution) {
        String query = "CALL PROJ_AJOUTATTRIBUTION(?, ?)";
        String queryIdProf = "SELECT ID FROM PROJ_ENSEIGNANT WHERE ID_PROF = ?";
        String queryIdClasse = "SELECT ID FROM PROJ_CLASSES WHERE SIGLE = ?";
        try (CallableStatement cs = connection.prepareCall(query); PreparedStatement psProf = connection.prepareStatement(queryIdProf); PreparedStatement psClasse = connection.prepareStatement(queryIdClasse)) {
            psProf.setString(1, attribution.getId_prof());
            psClasse.setString(1, attribution.getSigle());
            ResultSet rsProf = psProf.executeQuery();
            ResultSet rsClasse = psClasse.executeQuery();
            if (rsProf.next() && rsClasse.next()) {
                int idProf = rsProf.getInt("ID");
                int idClasse = rsClasse.getInt("ID");
                cs.setInt(1, idProf);
                cs.setInt(2, idClasse);
                cs.executeUpdate();
                if (attribution.getEnseignant().getTitulaire() != null) {
                    PreparedStatement ps = connection.prepareStatement("UPDATE PROJ_ENSEIGNANT SET ID_TITULAIRE = ? WHERE ID = ?");
                    ps.setInt(1, idClasse);
                    ps.setInt(2, idProf);
                    ps.executeUpdate();
                } else {
                    PreparedStatement ps = connection.prepareStatement("UPDATE PROJ_ENSEIGNANT SET ID_REMPLACANT = ? WHERE ID = ?");
                    ps.setInt(1, idClasse);
                    ps.setInt(2, idProf);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException sqle) {
            System.err.println("Erreur d'ajout " + sqle);
        }
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
    public void modifAttribution(Attribution attribution, boolean titulaire) {
        if (titulaire) {
            String queryIdClasse = "SELECT ID FROM PROJ_CLASSES WHERE SIGLE = ?";
            String query = "UPDATE PROJ_ENSEIGNANT SET ID_REMPLACANT = NULL, ID_TITULAIRE = ? WHERE ID_PROF = ?";
            try (PreparedStatement psClasse = connection.prepareStatement(queryIdClasse); PreparedStatement ps = connection.prepareStatement(query)) {
                psClasse.setString(1, attribution.getSigle());
                ResultSet rsClasse = psClasse.executeQuery();
                if (rsClasse.next()) {
                    int idClasse = rsClasse.getInt("ID");
                    ps.setInt(1, idClasse);
                    ps.setString(2, attribution.getId_prof());
                    ps.executeUpdate();
                    attribution.getEnseignant().setRemplacant(null);
                    attribution.getEnseignant().setTitulaire(attribution.getClasse());
                }
            } catch (SQLException sqle) {
                System.err.println("Erreur de modification" + sqle);
            }
        } else {
            String queryIdClasse = "SELECT ID FROM PROJ_CLASSES WHERE SIGLE = ?";
            String query = "UPDATE PROJ_ENSEIGNANT SET ID_REMPLACANT = ?, ID_TITULAIRE = NULL WHERE ID_PROF = ?";
            try (PreparedStatement psClasse = connection.prepareStatement(queryIdClasse); PreparedStatement ps = connection.prepareStatement(query)) {
                psClasse.setString(1, attribution.getSigle());
                ResultSet rsClasse = psClasse.executeQuery();
                if (rsClasse.next()) {
                    int idClasse = rsClasse.getInt("ID");
                    ps.setInt(1, idClasse);
                    ps.setString(2, attribution.getId_prof());
                    ps.executeUpdate();
                    attribution.getEnseignant().setRemplacant(attribution.getClasse());
                    attribution.getEnseignant().setTitulaire(null);
                }
            } catch (SQLException sqle) {
                System.err.println("Erreur de modification" + sqle);
            }
        }
    }

    @Override
    public void supAttribution(Attribution attribution) {
        String query = "CALL PROJ_SUPATTRIBUTION(?, ?)";
        String queryIdProf = "SELECT ID FROM PROJ_ENSEIGNANT WHERE ID_PROF = ?";
        String queryIdClasse = "SELECT ID FROM PROJ_CLASSES WHERE SIGLE = ?";
        try (CallableStatement cs = connection.prepareCall(query); PreparedStatement psProf = connection.prepareStatement(queryIdProf); PreparedStatement psClasse = connection.prepareStatement(queryIdClasse)) {
            psProf.setString(1, attribution.getId_prof());
            psClasse.setString(1, attribution.getSigle());
            ResultSet rsProf = psProf.executeQuery();
            ResultSet rsClasse = psClasse.executeQuery();
            if (rsProf.next() && rsClasse.next()) {
                int idProf = rsProf.getInt("ID");
                int idClasse = rsClasse.getInt("ID");
                cs.setInt(1, idProf);
                cs.setInt(2, idClasse);
                cs.executeUpdate();

                PreparedStatement ps = connection.prepareStatement("UPDATE PROJ_ENSEIGNANT SET ID_TITULAIRE = NULL WHERE ID = ?");
                ps.setInt(1, idProf);
                ps.executeUpdate();
                ps = connection.prepareStatement("UPDATE PROJ_ENSEIGNANT SET ID_REMPLACANT = NULL WHERE ID = ?");
                ps.setInt(1, idProf);
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("Erreur d'ajout " + sqle);
        }
    }

    @Override
    public void supAttributionTot() {
        List<Attribution> mesAttributions = getMesAttributions();
        mesAttributions.forEach((this::supAttribution));
    }

}
