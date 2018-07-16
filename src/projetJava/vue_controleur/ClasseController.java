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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import projetJava.ProjetJava;
import projetJava.classesmetier.Classes;
import projetJava.modele.Modele;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class ClasseController implements ControlledScreen {

    private ScreensController controleurParent;
    private Modele modele;
    
    private ObservableList<Classes> classesObservableList = FXCollections.observableArrayList();
    
    @FXML
    TextField txtOrientation, txtAnnee;
    
    @FXML
    TableView<Classes> classeTable;
    
    @FXML
    TableColumn<Classes, String> sigleColonne, orientationColonne;
    
    @FXML
    TableColumn<Classes, Integer> anneeColonne;
    
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
    public void updateOrDelete(KeyEvent keyevent) {
        if (keyevent.getCode() == KeyCode.DELETE && classeTable.getSelectionModel().getSelectedItem() != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer : " + classeTable.getSelectionModel().getSelectedItem() + "?", ButtonType.YES, ButtonType.NO);
            confirmation.setHeaderText("Demande de suppression");
            confirmation.showAndWait();
            if( confirmation.getResult() == ButtonType.YES ) {
                Alert suppression = new Alert(Alert.AlertType.INFORMATION);
                suppression.setHeaderText("suppression");
                Classes classe = classeTable.getSelectionModel().getSelectedItem();
                modele.supClasses(classe);
                suppression.show();
                actualiser();
            }
        }
    }
    
    @FXML
    public void actualiser() {
        classesObservableList.clear();
        classesObservableList.addAll(modele.getClasses());
        sigleColonne.setCellValueFactory(new PropertyValueFactory<Classes, String>("sigle"));
        orientationColonne.setCellValueFactory(new PropertyValueFactory<Classes, String>("orientation"));
        anneeColonne.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("annee"));
        classeTable.setItems(classesObservableList);
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
    
    @FXML
    public void delTot() {
        
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Souhaitez vous tout supprimer ?", ButtonType.YES, ButtonType.NO);
        confirmation.setHeaderText("Demande de suppression");
        confirmation.showAndWait();
        if ( confirmation.getResult() == ButtonType.YES ) {
            confirmation = new Alert(Alert.AlertType.CONFIRMATION, "êtes vous vraiment sûr ?", ButtonType.YES, ButtonType.NO);
            confirmation.setHeaderText("Sûr et certain ?");
            confirmation.showAndWait();
            if ( confirmation.getResult() == ButtonType.YES ) {
                Alert suppression = new Alert(Alert.AlertType.INFORMATION);
                suppression.setHeaderText("suppression");
                modele.supClassesTot();
                classesObservableList.clear();
            }
        }
    }
    
    }    