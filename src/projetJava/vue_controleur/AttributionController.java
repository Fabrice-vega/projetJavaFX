/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.vue_controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import projetJava.ProjetJava;
import projetJava.classesmetier.Attribution;
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
    private List<Classes> classeTitulaire = new ArrayList<>();

    private ObservableList<Enseignant> enseignantObservablelist = FXCollections.observableArrayList();
    private ObservableList<Classes> classesObservableList = FXCollections.observableArrayList();
    private ObservableList<Attribution> attributionObservableList = FXCollections.observableArrayList();

    @FXML
    TableColumn<Enseignant, String> id_profColonne, nomColonne, prenomColonne;

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

    @FXML
    Button btnTitulaire, btnModif;

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
    public void screenListe() {
        this.controleurParent.setScreen(ProjetJava.screenListe);
    }

    @FXML
    public void ajoutTitulaire() {
        if (classeTable.getSelectionModel().getSelectedItem() != null && enseignantTable.getSelectionModel().getSelectedItem() != null) {
            Classes classe = classeTable.getSelectionModel().getSelectedItem();
            Enseignant enseignant = enseignantTable.getSelectionModel().getSelectedItem();
            enseignant.setTitulaire(classe);
            Attribution attribution = new Attribution(classe, enseignant);
            modele.ajoutAttribution(attribution);
            actualiser();
        }
    }

    @FXML
    public void ajoutRemplacant() {
        if (classeTable.getSelectionModel().getSelectedItem() != null && enseignantTable.getSelectionModel().getSelectedItem() != null) {
            Classes classe = classeTable.getSelectionModel().getSelectedItem();
            Enseignant enseignant = enseignantTable.getSelectionModel().getSelectedItem();
            enseignant.setRemplacant(classe);
            Attribution attribution = new Attribution(classe, enseignant);
            modele.ajoutAttribution(attribution);
            actualiser();
        }
    }

    @FXML
    public void modifTitulaire() {
        if (attributionTable.getSelectionModel().getSelectedItem() != null) {
            Classes classe = attributionTable.getSelectionModel().getSelectedItem().getClasse();
            attributionTable.getSelectionModel().getSelectedItem().getEnseignant().setRemplacant(null);
            attributionTable.getSelectionModel().getSelectedItem().getEnseignant().setTitulaire(classe);
            attributionTable.getSelectionModel().getSelectedItem().setPoste("TITULAIRE");
            actualiser();
        }
    }

    @FXML
    public void modifRemplacant() {
        if (attributionTable.getSelectionModel().getSelectedItem() != null) {
            Classes classe = attributionTable.getSelectionModel().getSelectedItem().getClasse();
            attributionTable.getSelectionModel().getSelectedItem().getEnseignant().setTitulaire(null);
            attributionTable.getSelectionModel().getSelectedItem().getEnseignant().setRemplacant(classe);
            attributionTable.getSelectionModel().getSelectedItem().setPoste("REMPLACANT");
            actualiser();
        }
    }

    @FXML
    public void suppression(KeyEvent keyevent) {
        if (keyevent.getCode() == KeyCode.DELETE && attributionTable.getSelectionModel().getSelectedItem() != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer : " + attributionTable.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO);
            confirmation.setHeaderText("Demande de suppression");
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.YES) {
                Alert suppression = new Alert(Alert.AlertType.INFORMATION);
                suppression.setHeaderText("suppression");
                Attribution attribution = attributionTable.getSelectionModel().getSelectedItem();
                attribution.getEnseignant().setTitulaire(null);
                attribution.getEnseignant().setRemplacant(null);
                modele.supAttribution(attribution);
                suppression.show();
                actualiser();
            }
        }
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
                modele.supAttributionTot();
                for (Enseignant enseignant : modele.getMesEnseignants()) {
                    enseignant.setTitulaire(null);
                    enseignant.setRemplacant(null);
                }
                
            }
        }
    }

    @FXML
    public void disableTitulaire() {
        if (classeTable.getSelectionModel().getSelectedItem() != null) {
            modele.getMesEnseignants().forEach((enseignant -> {
                if (enseignant.getTitulaire() != null) {
                    if (classeTable.getSelectionModel().getSelectedItem().equals(enseignant.getTitulaire())) {
                        if (!classeTitulaire.contains(classeTable.getSelectionModel().getSelectedItem())) {
                            classeTitulaire.add(classeTable.getSelectionModel().getSelectedItem());
                        }
                    }
                }
            }));
            for (Classes classe : classeTitulaire) {
                if (classe.equals(classeTable.getSelectionModel().getSelectedItem())) {
                    btnTitulaire.setDisable(true);
                } else {
                    btnTitulaire.setDisable(false);
                }
            }
        }
        if (attributionTable.getSelectionModel().getSelectedItem() != null) {
            modele.getMesEnseignants().forEach((enseignant -> {
                if (enseignant.getTitulaire() != null) {
                    if (attributionTable.getSelectionModel().getSelectedItem().getClasse().equals(enseignant.getTitulaire())) {
                        classeTitulaire.add(attributionTable.getSelectionModel().getSelectedItem().getClasse());
                    }
                }
            }));
            for (Classes classe : classeTitulaire) {
                if (classe.equals(attributionTable.getSelectionModel().getSelectedItem().getClasse())) {
                    btnModif.setDisable(true);
                } else {
                    btnModif.setDisable(false);
                }
            }
        }
    }

    @FXML
    public void actualiser() {
        classeTitulaire.removeAll(classeTitulaire);
        enseignantObservablelist.clear();
        modele.getMesEnseignants().forEach((enseignant -> {
            if (enseignant.getTitulaire() == null && enseignant.getRemplacant() == null) {
                enseignantObservablelist.add(enseignant);
            }
        }));
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

        btnTitulaire.setDisable(false);
        btnModif.setDisable(false);
    }

}
