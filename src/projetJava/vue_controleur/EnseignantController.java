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
import projetJava.classesmetier.Enseignant;
import projetJava.modele.Modele;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class EnseignantController implements ControlledScreen {

    private ScreensController controleurParent;
    private Modele modele;
    
    @FXML
    TextField txtPrenom,txtNom;

    @Override
    public void setScreenParent(ScreensController screenPage) {
        this.controleurParent = screenPage;
    }

    @Override
    public void setModele(Modele modele) {
        this.modele = modele;
    }
    
    @FXML
    public void annuler() {
        txtNom.setText("");
        txtPrenom.setText("");
    }
    
    @FXML
    public void screenAccueil() {
        controleurParent.setScreen(ProjetJava.screenAccueil);
    }
    
    @FXML
    public void screenClasse() {
        Modele modele = Modele.getInstance();
        this.controleurParent.setModForControl(modele);
        this.controleurParent.setScreen(ProjetJava.screenClasse);
    }
    
    @FXML
    public void ajout() {
        String nom = txtNom.getText().toUpperCase();
        String prenom = txtPrenom.getText().toUpperCase();
        
        try {
            String id_prof = nom.substring(0,2) + prenom.substring(0,2);
            Enseignant.EnseignantBuilder enseignantBuilder = new Enseignant.EnseignantBuilder().setId_prof(id_prof).setNom(nom).setPrenom(prenom);
            Enseignant enseignant = enseignantBuilder.build();
            modele.ajoutEnseignants(enseignant);
            Alert alertBon = new Alert (Alert.AlertType.INFORMATION,modele.getMesEnseignants().toString());
            alertBon.setTitle("Liste");
            alertBon.show();
            Alert alertInt = new Alert(Alert.AlertType.INFORMATION,"Ajout effectué");
            alertInt.setTitle("Ajout !");
            alertInt.show();
        }catch (Exception e) {
            Alert alertInt = new Alert(Alert.AlertType.ERROR,"Erreur lors de la création");
            alertInt.setHeaderText("Erreur de création");
            alertInt.setTitle("Erreur");
            alertInt.show();
            annuler();
        }
    }
}
