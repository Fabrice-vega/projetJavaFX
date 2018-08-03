/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.classesmetier;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Fabrice
 */
public class Classes {

    private final SimpleStringProperty sigle;
    private final SimpleStringProperty orientation;
    private final SimpleIntegerProperty annee;

    /**
     * Constructeur de Classes
     *
     * @param sigle le sigle
     * @param orientation l'orientation
     * @param annee l'année
     */
    public Classes(String sigle, String orientation, int annee) {
        this.sigle = new SimpleStringProperty(sigle);
        this.orientation = new SimpleStringProperty(orientation);
        this.annee = new SimpleIntegerProperty(annee);
    }

    /**
     * Constructeur de recherche
     *
     * @param sigle le sigle
     */
    public Classes(String sigle) {
        this.sigle = new SimpleStringProperty(sigle);
        this.orientation = null;
        this.annee = null;
    }

    /**
     * Constructeur de classebuilder
     *
     * @param classeBuilder le builder
     */
    public Classes(ClasseBuilder classeBuilder) {
        this.sigle = classeBuilder.sigle;
        this.annee = classeBuilder.annee;
        this.orientation = classeBuilder.orientation;
    }

    /**
     * les données du builder
     */
    public static class ClasseBuilder {

        private SimpleStringProperty sigle;
        private SimpleStringProperty orientation;
        private SimpleIntegerProperty annee;

        /**
         * Méthode set du sigle du builder
         *
         * @param sigle le sigle du builder
         * @return le sigle
         */
        public ClasseBuilder setSigle(String sigle) {
            this.sigle = new SimpleStringProperty(sigle);
            return this;
        }

        /**
         * Méthode set de l'orientation du builder
         *
         * @param orientation l'orientation du builder
         * @return l'orientation
         */
        public ClasseBuilder setOrientation(String orientation) {
            this.orientation = new SimpleStringProperty(orientation);
            return this;
        }

        /**
         * Méthode set de l'année du builder
         *
         * @param annee l'année du builder
         * @return l'année
         */
        public ClasseBuilder setAnnee(int annee) {
            this.annee = new SimpleIntegerProperty(annee);
            return this;
        }

        /**
         * Méthode de construction du builder
         *
         * @return la classe créée
         * @throws Exception
         */
        public Classes build() throws Exception {
            if (sigle == null || orientation == null) {
                throw new Exception("Information manquantes");
            } else if (sigle.get().trim().equals("") || orientation.get().trim().equals("")) {
                throw new Exception("Information vide");
            }
            return new Classes(this);
        }
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

    @Override
    public String toString() {
        return "Classes{" + "sigle=" + sigle.get() + ", orientation="
                + (orientation != null ? orientation.get() : null)
                + ", annee="
                + (annee != null ? annee.get() : 0)
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.sigle);
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
        final Classes other = (Classes) obj;
        if (!Objects.equals(this.sigle.get(), other.sigle.get())) {
            return false;
        }
        return true;
    }

}
