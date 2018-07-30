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
import javafx.stage.Stage;
import projetJava.ProjetJava;
import projetJava.classesmetier.Enseignant;
import projetJava.modele.Modele;
import projetJava.modele.ModeleJDBC;

/**
 * FXML Controller class
 *
 * @author Fabrice
 */
public class EnseignantController implements ControlledScreen {

    private ScreensController controleurParent;
    private Modele modele;

    private ObservableList<Enseignant> enseignantObservablelist = FXCollections.observableArrayList();

    @FXML
    TextField txtPrenom, txtNom, txtPrenomModif, txtNomModif;

    @FXML
    TableView<Enseignant> enseignantTable;

    @FXML
    TableColumn<Enseignant, String> id_profColonne, nomColonne, prenomColonne, posteColonne;

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
        stage.setTitle("ProjetJavaFX - Accueil- " + modele);
        controleurParent.setScreen(projetJava.ProjetJava.screenAccueil);
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
    public void screenListe() {
        String modele = this.modele instanceof ModeleJDBC ? "Modèle JDBC" : "Modèle liste";
        Stage stage = (Stage) controleurParent.getScene().getWindow();
        stage.setTitle("ProjetJavaFX - Listes - " + modele);
        this.controleurParent.setScreen(ProjetJava.screenListe);
    }

    @FXML
    public void ajout() {
        String nom = txtNom.getText().toUpperCase();
        String prenom = txtPrenom.getText().toUpperCase();

        try {
            String id_prof = nom.substring(0, 2) + prenom.substring(0, 2);
            Enseignant.EnseignantBuilder enseignantBuilder = new Enseignant.EnseignantBuilder().setId_prof(id_prof).setNom(nom).setPrenom(prenom);
            Enseignant enseignant = enseignantBuilder.build();
            Boolean ajout = modele.ajoutEnseignants(enseignant);
            if (ajout) {
                enseignant.add(controleurParent);
                Alert alertBon = new Alert(Alert.AlertType.INFORMATION, modele.getMesEnseignants().toString());
                alertBon.setTitle("Liste");
                alertBon.show();
                Alert alertInt = new Alert(Alert.AlertType.INFORMATION, "Ajout effectué");
                alertInt.setTitle("Ajout !");
                alertInt.show();
                annuler();
            } else {
                Alert alertDoublon = new Alert(Alert.AlertType.ERROR, "Enseignant déjà créée");
                alertDoublon.setTitle("Erreur...");
                alertDoublon.show();
            }
        } catch (Exception e) {
            Alert alertInt = new Alert(Alert.AlertType.ERROR, "Erreur lors de la création");
            alertInt.setHeaderText("Erreur de création");
            alertInt.setTitle("Erreur");
            alertInt.show();
            annuler();
        }
    }

    @FXML
    public void modification() {
        if (enseignantTable.getSelectionModel().getSelectedItem() != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Modifier : " + enseignantTable.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO);
            confirmation.setHeaderText("Demande de modification");
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.YES) {
                String nom = txtNomModif.getText().toUpperCase();
                String prenom = txtPrenomModif.getText().toUpperCase();
                try {
                    String id_prof = nom.substring(0, 2) + prenom.substring(0, 2);
                    Enseignant.EnseignantBuilder enseignantBuilder = new Enseignant.EnseignantBuilder().setId_prof(id_prof).setNom(nom).setPrenom(prenom);
                    Enseignant enseignantModif = enseignantBuilder.build();
                    Boolean modif = modele.modifEnseignant(enseignantTable.getSelectionModel().getSelectedItem(), enseignantModif);
                    if (modif) {
                        Alert alertModif = new Alert(Alert.AlertType.INFORMATION, "Modification effectué");
                        alertModif.setTitle("Modification !");
                        alertModif.show();
                        annuler();
                        actualiser();
                    }
                } catch (Exception e) {
                    Alert alertInt = new Alert(Alert.AlertType.ERROR, "Erreur lors de la modification");
                    alertInt.setHeaderText("Erreur de modification");
                    alertInt.setTitle("Erreur");
                    alertInt.show();
                    annuler();
                }
            }
        }
    }

    @FXML
    public void suppression(KeyEvent keyevent) {
        if (keyevent.getCode() == KeyCode.DELETE && enseignantTable.getSelectionModel().getSelectedItem() != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer : " + enseignantTable.getSelectionModel().getSelectedItem() + " ?", ButtonType.YES, ButtonType.NO);
            confirmation.setHeaderText("Demande de suppression");
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.YES) {
                Alert suppression = new Alert(Alert.AlertType.INFORMATION);
                suppression.setHeaderText("suppression");
                Enseignant enseignant = enseignantTable.getSelectionModel().getSelectedItem();
                Boolean sup = modele.supEnseignants(enseignant);
                if (sup) {
                    suppression.show();
                }
                actualiser();
            }
        }
    }

    @FXML
    public void delTot() {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Souhaitez vous tout supprimer ?", ButtonType.YES, ButtonType.NO);
        confirmation.setHeaderText("Demande de suppression");
        confirmation.showAndWait();
        if (confirmation.getResult() == ButtonType.YES) {
            confirmation = new Alert(Alert.AlertType.CONFIRMATION, "êtes vous vraiment sûr ?", ButtonType.YES, ButtonType.NO);
            confirmation.setHeaderText("Sûr et certain ?");
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.YES) {
                Alert suppression = new Alert(Alert.AlertType.INFORMATION);
                suppression.setHeaderText("suppression");
                modele.supEnseignantsTot();
                actualiser();
            }
        }
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
    }

    @FXML
    public void annuler() {
        txtNom.setText("");
        txtNomModif.setText("");
        txtPrenom.setText("");
        txtPrenomModif.setText("");
    }
}
