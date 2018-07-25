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
import javafx.stage.Stage;
import projetJava.ProjetJava;
import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;
import projetJava.modele.Modele;
import projetJava.modele.ModeleJDBC;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class ListeController implements ControlledScreen {    

    private Modele modele;
    private ScreensController controleurParent;
    
    private ObservableList<Enseignant> enseignantObservablelist = FXCollections.observableArrayList();
    private ObservableList<Classes> classesObservableList = FXCollections.observableArrayList();
    private ObservableList<Attribution> attributionObservableList = FXCollections.observableArrayList();
    
    @FXML
    TableColumn<Enseignant, String> id_profColonne, nomColonne, prenomColonne, posteColonne;
    
    @FXML
    TableColumn<Classes, String> sigleColonne, orientationColonne;
    
    @FXML
    TableColumn<Classes, Integer> anneeColonne;
    
    @FXML
    TableColumn<Attribution, String> attribId_profColonne, attribNomColonne, attribPrenomColonne, attribPosteColonne, attribSigleColonne, attribOrientationColonne;
    
    @FXML
    TableColumn<Attribution, Integer> attribAnneeColonne;
    
    @FXML
    TableView<Classes> classeTable;
    
    @FXML
    TableView<Enseignant> enseignantTable;
    
    @FXML
    TableView<Attribution> attributionTable;

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
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Accueil - " + modele);
        controleurParent.setScreen(ProjetJava.screenAccueil);
    }
    
    @FXML
    public void screenEnseignant() {
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Enseignants - " + modele);
        this.controleurParent.setScreen(ProjetJava.screenEnseignant);
    }
    
    @FXML
    public void screenClasse() {
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Classes - " + modele);
        this.controleurParent.setScreen(ProjetJava.screenClasse);
    }
    
    @FXML
    public void screenAttribution() {
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Attributions - " + modele);
        this.controleurParent.setScreen(ProjetJava.screenAttribution);
    }
    
    @FXML
    public void actualiser() {
        enseignantObservablelist.clear();
        enseignantObservablelist.addAll(modele.getMesEnseignants());
        id_profColonne.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("id_prof"));
        nomColonne.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("nom"));
        prenomColonne.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("prenom"));
        posteColonne.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("poste"));
        enseignantTable.setItems(enseignantObservablelist);
        
        classesObservableList.clear();
        classesObservableList.addAll(modele.getClasses());
        sigleColonne.setCellValueFactory(new PropertyValueFactory<Classes, String>("sigle"));
        orientationColonne.setCellValueFactory(new PropertyValueFactory<Classes, String>("orientation"));
        anneeColonne.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("annee"));
        classeTable.setItems(classesObservableList);
        
        attributionObservableList.clear();
        attributionObservableList.addAll(modele.getMesAttributions());
        attribId_profColonne.setCellValueFactory(new PropertyValueFactory<Attribution, String>("id_prof"));
        attribNomColonne.setCellValueFactory(new PropertyValueFactory<Attribution, String>("nom"));
        attribPrenomColonne.setCellValueFactory(new PropertyValueFactory<Attribution, String>("prenom"));
        attribSigleColonne.setCellValueFactory(new PropertyValueFactory<Attribution, String>("sigle"));
        attribPosteColonne.setCellValueFactory(new PropertyValueFactory<Attribution, String>("poste"));
        attribOrientationColonne.setCellValueFactory(new PropertyValueFactory<Attribution, String>("orientation"));
        attribAnneeColonne.setCellValueFactory(new PropertyValueFactory<Attribution, Integer>("annee"));
        attributionTable.setItems(attributionObservableList);
    }
    
}
