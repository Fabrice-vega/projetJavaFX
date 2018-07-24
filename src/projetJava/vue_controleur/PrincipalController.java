/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.vue_controleur;

import javafx.fxml.FXML;
import projetJava.ProjetJava;
import projetJava.modele.Modele;
import projetJava.modele.ModeleJDBC;

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
    public void screenAccueilListe() {
        Modele modele = Modele.getInstance();
        modele.populate();
        this.controleurParent.setModForControl(modele);
        this.controleurParent.setScreen(ProjetJava.screenAccueil);
    }

    @FXML
    public void screenAccueilJDBC() {

        ModeleJDBC modele = ModeleJDBC.getInstance();
        modele.setController(controleurParent);
        this.controleurParent.setModForControl(modele);
        this.controleurParent.setScreen(ProjetJava.screenAccueil);
    }


    
}
