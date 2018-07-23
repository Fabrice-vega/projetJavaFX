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
import projetJava.ProjetJava;
import projetJava.modele.Modele;

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
        this.controleurParent.setScreen(ProjetJava.screenClasse);
    }
    
    @FXML
    public void screenEnseignant() {
        this.controleurParent.setScreen(ProjetJava.screenEnseignant);
    }
    
    @FXML
    public void screenPrincipal() {
        this.controleurParent.setScreen(ProjetJava.screenPrincipal);
    }
    
    @FXML
    public void screenAttribution() {
        this.controleurParent.setScreen(ProjetJava.screenAttribution);
    }
    
    @FXML
    public void screenListe() {
        this.controleurParent.setScreen(ProjetJava.screenListe);
    }
    
    @FXML
    public void fermetureProgramme() {
        Platform.exit();
    }
}
