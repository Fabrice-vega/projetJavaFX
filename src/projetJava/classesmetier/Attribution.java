/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.classesmetier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Fabrice
 */
public class Attribution {
    
    private Classes classe;
    private Enseignant enseignant;
    
    private final SimpleStringProperty sigle;
    private final SimpleStringProperty orientation;
    private final SimpleIntegerProperty annee;
    
    private final SimpleStringProperty id_prof;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    
    private final SimpleStringProperty poste;
    
    public Attribution(Classes classe, Enseignant enseignant) {
        this.classe = classe;
        this.enseignant = enseignant;
        this.sigle = new SimpleStringProperty(classe.getSigle());
        this.orientation = new SimpleStringProperty(classe.getOrientation());
        this.annee = new SimpleIntegerProperty(classe.getAnnee());
        this.id_prof = new SimpleStringProperty(enseignant.getId_prof());
        this.prenom = new SimpleStringProperty(enseignant.getPrenom());
        this.nom = new SimpleStringProperty(enseignant.getNom());
        if (enseignant.getTitulaire() != null) {
            this.poste = new SimpleStringProperty("Titulaire");
        } else {
            this.poste = new SimpleStringProperty("Remplacant");
        }
    }
    
    @Override
    public String toString() {
        return "Attribution : \n"+"classe : "+classe+", enseignant : "+enseignant;
    }

    public Classes getClasse() {
        return classe;
    }

    public void setClasse(Classes classe) {
        this.classe = classe;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }
    
    public String getSigle() {
        return sigle.get();
    }
    
    public SimpleStringProperty sigleProperty() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle.set(sigle);
    }

    public String getOrientation() {
        return orientation.get();
    }
    
    public SimpleStringProperty orientationProperty() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation.set(orientation);
    }

    public int getAnnee() {
        return annee.get();
    }
    
    public SimpleIntegerProperty anneeProperty() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee.set(annee);
    }
    
    public String getId_prof() {
        return id_prof.get();
    }
    
    public SimpleStringProperty id_profProperty() {
        return id_prof;
    }

    public void setId_prof(String id_prof) {
        this.id_prof.set(id_prof);
    }

    public String getNom() {
        return nom.get();
    }
    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public SimpleStringProperty prenomProperty() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }
    
    public String getPoste() {
        return this.poste.get();
    }
    
    public SimpleStringProperty posteProperty() {
        return poste;
    }
    
    public void setPoste(String poste) {
        this.poste.set(poste);
    }
    
    
}
