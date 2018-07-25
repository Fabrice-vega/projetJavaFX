/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.vue_controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import projetJava.ProjetJava;
import projetJava.modele.Modele;
import projetJava.modele.ModeleJDBC;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class AccueilController implements ControlledScreen {

    private ScreensController controleurParent;
    private Modele modele;
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.controleurParent = screenPage;
    }

    @Override
    public void setModele(Modele modele) {
        this.modele = modele;
    }
    
    @FXML
    public void screenClasse() {
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Classes - " + modele);
        this.controleurParent.setScreen(ProjetJava.screenClasse);
    }
    
    @FXML
    public void screenEnseignant() {
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Enseignants - " + modele);
        this.controleurParent.setScreen(ProjetJava.screenEnseignant);
    }
    
    @FXML
    public void screenPrincipal() {
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX");
        this.controleurParent.setScreen(ProjetJava.screenPrincipal);
    }
    
    @FXML
    public void screenAttribution() {
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Attributions - " + modele);
        this.controleurParent.setScreen(ProjetJava.screenAttribution);
    }
    
    @FXML
    public void screenListe() {
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Listes - " + modele);
        this.controleurParent.setScreen(ProjetJava.screenListe);
    }
    
    @FXML
    public void fermetureProgramme() {
        Platform.exit();
    }
}
