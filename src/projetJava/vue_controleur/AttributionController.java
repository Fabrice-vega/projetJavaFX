/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.vue_controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projetJava.ProjetJava;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;
import projetJava.modele.Modele;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class AttributionController implements ControlledScreen {   

    private Modele modele;
    private ScreensController controleurParent;
    
    private ObservableList<Enseignant> enseignantObservablelist = FXCollections.observableArrayList();
    private ObservableList<Classes> classesObservableList = FXCollections.observableArrayList();
    
    @FXML
    TableColumn<Enseignant, String> id_profColonne, nomColonne, prenomColonne;
    
    @FXML
    TableColumn<Classes, String> sigleColonne, orientationColonne;
    
    @FXML
    TableColumn<Classes, Integer> anneeColonne;
    
    @FXML
    TableView<Classes> classeTable;
    
    @FXML
    TableView<Enseignant> enseignantTable;

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
        controleurParent.setScreen(ProjetJava.screenAccueil);
    }
    
    @FXML
    public void screenEnseignant() {
        this.controleurParent.setScreen(ProjetJava.screenEnseignant);
    }
    
    @FXML
    public void screenClasse() {
        this.controleurParent.setScreen(ProjetJava.screenClasse);
    }
    
    @FXML
    public void actualiser() {
        enseignantObservablelist.clear();
        enseignantObservablelist.addAll(modele.getMesEnseignants());
        id_profColonne.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("id_prof"));
        nomColonne.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("nom"));
        prenomColonne.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("prenom"));
        enseignantTable.setItems(enseignantObservablelist);
        
        classesObservableList.clear();
        classesObservableList.addAll(modele.getClasses());
        sigleColonne.setCellValueFactory(new PropertyValueFactory<Classes, String>("sigle"));
        orientationColonne.setCellValueFactory(new PropertyValueFactory<Classes, String>("orientation"));
        anneeColonne.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("annee"));
        classeTable.setItems(classesObservableList);
    }
    
}
