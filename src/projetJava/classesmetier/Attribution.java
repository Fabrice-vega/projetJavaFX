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

    /**
     * Constructeur de Attribution
     *
     * @param classe la classe
     * @param enseignant l'enseignant
     */
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
            this.poste = new SimpleStringProperty("TITULAIRE");
        } else {
            this.poste = new SimpleStringProperty("REMPLACANT");
        }
    }

    @Override
    public String toString() {
        return "Attribution : \n" + "classe : " + classe + ", enseignant : " + enseignant;
    }
    
    @Override
    public boolean equals (final Object o) {

        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Attribution that = ( Attribution ) o;

        if ( classe != null ? !classe.equals(that.classe) : that.classe != null ) return false;
        return enseignant != null ? enseignant.equals(that.enseignant) : that.enseignant == null;
    }

    /**
     * Méthode get de la classe
     *
     * @return la classe
     */
    public Classes getClasse() {
        return classe;
    }

    /**
     * Méthode set de la classe
     *
     * @param classe la classe
     */
    public void setClasse(Classes classe) {
        this.classe = classe;
    }

    /**
     * Méthode get de l'enseignant
     *
     * @return l'enseignant
     */
    public Enseignant getEnseignant() {
        return enseignant;
    }

    /**
     * Méthode set de l'enseignant
     *
     * @param enseignant l'enseignant
     */
    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    /**
     * Méthode get du sigle
     *
     * @return le sigle
     */
    public String getSigle() {
        return sigle.get();
    }

    /**
     * Méthode property de sigle
     *
     * @return le sigle
     */
    public SimpleStringProperty sigleProperty() {
        return sigle;
    }

    /**
     * Méthode de set du sigle
     *
     * @param sigle le sigle
     */
    public void setSigle(String sigle) {
        this.sigle.set(sigle);
    }

    /**
     * Méthode de get de l'orientation
     *
     * @return l'orientation
     */
    public String getOrientation() {
        return orientation.get();
    }

    /**
     * Méthode property de orientation
     *
     * @return l'orientation
     */
    public SimpleStringProperty orientationProperty() {
        return orientation;
    }

    /**
     * Méthode set de l'orientation
     *
     * @param orientation l'orienation
     */
    public void setOrientation(String orientation) {
        this.orientation.set(orientation);
    }

    /**
     * Méthode get de annee
     *
     * @return l'année
     */
    public int getAnnee() {
        return annee.get();
    }

    /**
     * Méthode property de annee
     *
     * @return l'année
     */
    public SimpleIntegerProperty anneeProperty() {
        return annee;
    }

    /**
     * Méthode set de l'année
     *
     * @param annee
     */
    public void setAnnee(int annee) {
        this.annee.set(annee);
    }

    /**
     * Méthode get de l'id
     *
     * @return l'id
     */
    public String getId_prof() {
        return id_prof.get();
    }

    /**
     * Méthode property de l'id
     *
     * @return l'id
     */
    public SimpleStringProperty id_profProperty() {
        return id_prof;
    }

    /**
     * Méthode set de l'id
     *
     * @param id_prof l'id
     */
    public void setId_prof(String id_prof) {
        this.id_prof.set(id_prof);
    }

    /**
     * Méthode get du nom
     *
     * @return le nom
     */
    public String getNom() {
        return nom.get();
    }

    /**
     * Méthode property du nom
     *
     * @return le nom
     */
    public SimpleStringProperty nomProperty() {
        return nom;
    }

    /**
     * Méthode set du nom
     *
     * @param nom le nom
     */
    public void setNom(String nom) {
        this.nom.set(nom);
    }

    /**
     * Méthode get du prénom
     *
     * @return le prénom
     */
    public String getPrenom() {
        return prenom.get();
    }

    /**
     * Méthode property du prénom
     *
     * @return le prénom
     */
    public SimpleStringProperty prenomProperty() {
        return prenom;
    }

    /**
     * Méthode set du prénom
     *
     * @param prenom le prénom
     */
    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    /**
     * Méthode get du poste
     *
     * @return le poste
     */
    public String getPoste() {
        return this.poste.get();
    }

    /**
     * Méthode property du poste
     *
     * @return le poste
     */
    public SimpleStringProperty posteProperty() {
        return poste;
    }

    /**
     * Méthode set du poste
     *
     * @param poste le poste
     */
    public void setPoste(String poste) {
        this.poste.set(poste);
    }

}
