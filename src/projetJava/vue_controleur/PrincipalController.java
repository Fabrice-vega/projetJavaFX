/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.vue_controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import projetJava.ProjetJava;
import projetJava.modele.Modele;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class PrincipalController implements ControlledScreen {

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
    public void screenAccueil() {
        Modele modele = Modele.getInstance();
        this.controleurParent.setModForControl(modele);
        this.controleurParent.setScreen(ProjetJava.screenAccueil);
    }
    
}