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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import projetJava.ProjetJava;
import projetJava.classesmetier.Classes;
import projetJava.modele.Modele;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class AjoutController implements ControlledScreen {

    private ScreensController controleurParent;
    private Modele modele;
    
    @FXML
    TextField txtOrientation, txtAnnee;
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.controleurParent = screenPage;
    }

    @Override
    public void setModele(Modele modele) {
        this.modele = modele;
    }
    
    @FXML
    public void ajout(){
        String orientation = txtOrientation.getText().toUpperCase();
        String anneeString = txtAnnee.getText();
        
        try {
            String sigle = anneeString + orientation.charAt(0);
            int annee = Integer.parseInt(anneeString);
            Classes.ClasseBuilder classeBuilder = new Classes.ClasseBuilder().setSigle(sigle).setOrientation(orientation).setAnnee(annee);
            Classes classe = classeBuilder.build();
            modele.ajoutClasses(classe);
            Alert alertBon = new Alert(Alert.AlertType.INFORMATION,modele.getClasses().toString());
            alertBon.setTitle("Liste");
            alertBon.show();
            Alert alertInt = new Alert(Alert.AlertType.INFORMATION,"Ajout effectué");
            alertInt.setTitle("Ajout !");
            alertInt.show();
            
            annuler();
            
        } catch ( NumberFormatException nfe) {
            Alert alertInt = new Alert(Alert.AlertType.ERROR,"Année doit être un chiffre");
            alertInt.setHeaderText("Erreur champ année");
            alertInt.setTitle("Erreur");
            alertInt.show();
            txtAnnee.setText("");
            
        } catch ( Exception e) {
            Alert alertInt = new Alert(Alert.AlertType.ERROR,"Erreur lors de la création");
            alertInt.setHeaderText("Erreur de création");
            alertInt.setTitle("Erreur");
            alertInt.show();
            annuler();
        }
    }
    
    @FXML
    public void annuler() {
        txtOrientation.setText("");
        txtAnnee.setText("");
    }

    @FXML
    public void screenAccueil() {
        controleurParent.setScreen(ProjetJava.screenAccueil);
    }
    
    @FXML
    public void screenEnseignant() {
        Modele modele = Modele.getInstance();
        this.controleurParent.setModForControl(modele);
        this.controleurParent.setScreen(ProjetJava.screenEnseignant);
    }
    
    }    