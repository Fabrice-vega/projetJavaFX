/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;
import projetJava.connexions.ConnexionProjet;
import projetJava.vue_controleur.ScreensController;

/**
 *
 * @author Fabrice
 */
public class ModeleJDBC extends Modele {

    private Connection connection;

    private static ModeleJDBC instance = null;

    private ScreensController controlerParent;

    private ModeleJDBC() {
        connection = ConnexionProjet.getInstance();
        if (connection == null) {
            System.err.println("Erreur de connexion : Arrêt du programme.");
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
            System.err.println("Erreur lors de la fermeture " + e);
        }
    }

    public void setController(ScreensController controleurParent) {
        this.controlerParent = controleurParent;
    }

    @Override
    public void populate() {

    }

    @Override
    public Boolean ajoutClasses(Classes classe) {
        String query = "CALL PROJ_AJOUTCLASSE(?, ?, ?)";
        try (CallableStatement cs = connection.prepareCall(query)) {
            cs.setString(1, classe.getSigle());
            cs.setInt(2, classe.getAnnee());
            cs.setString(3, classe.getOrientation());
            cs.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.err.println("Erreur d'ajout de classe " + sqle);
            return false;
        }
    }

    @Override
    public void supClasses(Classes classe) {
        String query = "SELECT ID_CLASSE FROM PROJ_ATTRIBUTION WHERE ID_CLASSE = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, classe.getSigle());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //peut pas supprimer
            }
        } catch (SQLException sqle) {
            if (sqle.getErrorCode() == 1722) {
                query = "CALL PROJ_SUPCLASSE(?)";
                try(CallableStatement cs = connection.prepareCall(query)) {
                    cs.setString(1, classe.getSigle());
                    cs.executeUpdate();
                }catch(SQLException sqle2) {
                    System.err.println("Erreur de suppression " + sqle2);
                }
            }
        }
    }

    @Override
    public void supClassesTot() {
    }

    @Override
    public List<Classes> getClasses() {
        List<Classes> mesClasses = new ArrayList<>();
        String query = "SELECT SIGLE, ORIENTATION, ANNEE FROM PROJ_CLASSES ORDER BY SIGLE ASC";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String sigle = rs.getString("SIGLE");
                String orientation = rs.getString("ORIENTATION");
                int annee = rs.getInt("ANNEE");
                Classes.ClasseBuilder classeBuilder = new Classes.ClasseBuilder().setSigle(sigle).setAnnee(annee).setOrientation(orientation);
                try {
                    Classes classe = classeBuilder.build();
                    mesClasses.add(classe);
                } catch (Exception e) {
                    System.err.println("Erreur de création " + e);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur de recherche de classe " + e);
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
    public void ajoutAttribution(Attribution attribution) {

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
