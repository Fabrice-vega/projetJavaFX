package projetJava.classesmetier;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import projetJava.observer.Sujet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fabrice
 */
public class Enseignant extends Sujet {

    private final SimpleStringProperty id_prof;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private Classes titulaire, remplacant;
    private final SimpleStringProperty poste;

    /**
     * Constructeur de Enseignant
     *
     * @param id_prof l'id
     * @param nom le nom
     * @param prenom le prénom
     */
    public Enseignant(String id_prof, String nom, String prenom) {
        this.id_prof = new SimpleStringProperty(id_prof);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.poste = new SimpleStringProperty("AUCUNE ATTRIBUTION");
    }

    /**
     * Constructeur de recherche
     *
     * @param id_prof l'id
     */
    public Enseignant(String id_prof) {
        this.id_prof = new SimpleStringProperty(id_prof);
        this.nom = null;
        this.prenom = null;
        this.poste = null;
    }

    /**
     * Constructeur de enseignantbuilder
     *
     * @param enseignantBuilder le builder
     */
    public Enseignant(EnseignantBuilder enseignantBuilder) {
        this.id_prof = enseignantBuilder.id_prof;
        this.prenom = enseignantBuilder.prenom;
        this.nom = enseignantBuilder.nom;
        this.poste = new SimpleStringProperty("AUCUNE ATTRIBUTION");
    }

    /**
     * les données du builder
     */
    public static class EnseignantBuilder {

        private SimpleStringProperty id_prof;
        private SimpleStringProperty nom;
        private SimpleStringProperty prenom;

        /**
         * Méthode set de l'id du builder
         *
         * @param id_prof l'id du builder
         * @return l'id
         */
        public EnseignantBuilder setId_prof(String id_prof) {
            this.id_prof = new SimpleStringProperty(id_prof);
            return this;
        }

        /**
         * Méthode set du nom du builder
         *
         * @param nom le nom du builder
         * @return le nom
         */
        public EnseignantBuilder setNom(String nom) {
            this.nom = new SimpleStringProperty(nom);
            return this;
        }

        /**
         * Méthode set du prénom du builder
         *
         * @param prenom le prénom du builder
         * @return le prénom
         */
        public EnseignantBuilder setPrenom(String prenom) {
            this.prenom = new SimpleStringProperty(prenom);
            return this;
        }

        /**
         * Méthode de construction du builder
         *
         * @return l'enseignant créée
         * @throws Exception
         */
        public Enseignant build() throws Exception {
            if (id_prof == null || nom == null || prenom == null) {
                throw new Exception("Information manquantes");
            } else if (id_prof.get().trim().equals("") || nom.get().trim().equals("") || prenom.get().trim().equals("")) {
                throw new Exception("Information vide");
            }
            return new Enseignant(this);
        }
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

    /**
     * Méthode get de titulaire
     *
     * @return le titulaire
     */
    public Classes getTitulaire() {
        return titulaire;
    }

    /**
     * Méthode get de remplaçant
     *
     * @return le remplaçant
     */
    public Classes getRemplacant() {
        return remplacant;
    }

    /**
     * Méthode set de titulaire
     *
     * @param titulaire le titulaire
     */
    public void setTitulaire(Classes titulaire) {
        Classes oldClasse = null;
        if (titulaire != null) {
            this.titulaire = titulaire;
        } else {
            oldClasse = this.titulaire;
            this.titulaire = null;
        }
        if (titulaire == null) {
            notify(this.getNom() + " " + this.getPrenom() + " n'est plus titulaire de la " + oldClasse, oldClasse, this);
            this.poste.set("AUCUNE ATTRIBUTION");
        } else {
            notify(this.getNom() + " " + this.getPrenom() + " est le nouveau titulaire de la " + titulaire, titulaire, this);
            this.poste.set("TITULAIRE");
        }

    }

    /**
     * Méthode set de remplaçant
     *
     * @param remplacant le remplaçant
     */
    public void setRemplacant(Classes remplacant) {
        this.remplacant = remplacant;

        if (remplacant == null) {
            this.poste.set("AUCUNE ATTRIBUTION");
        } else {
            this.poste.set("REMPLACANT");
        }
    }

    @Override
    public String toString() {
        return "Enseignant{" + "id_prof=" + id_prof.get() + ", nom="
                + (nom != null ? nom.get() : null)
                + ", prenom="
                + (prenom != null ? prenom.get() : null)
                + ", " + poste.get() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id_prof);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enseignant other = (Enseignant) obj;
        if (!Objects.equals(this.id_prof.get(), other.id_prof.get())) {
            return false;
        }
        return true;
    }

}
