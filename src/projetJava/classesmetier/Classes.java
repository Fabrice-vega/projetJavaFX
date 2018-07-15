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
public class Classes {
    private final SimpleStringProperty sigle;
    private final SimpleStringProperty orientation;
    private final SimpleIntegerProperty annee;

    public Classes(String sigle, String orientation, int annee) {
        this.sigle = new SimpleStringProperty(sigle);
        this.orientation = new SimpleStringProperty(orientation);
        this.annee = new SimpleIntegerProperty(annee);
    }

    // constructeur de recherche
    public Classes(String sigle) {
        this.sigle = new SimpleStringProperty(sigle);
        this.orientation = null;
        this.annee = null;
    }    
    
    public Classes (ClasseBuilder classeBuilder) {
        this.sigle = classeBuilder.sigle;
        this.annee = classeBuilder.annee;
        this.orientation = classeBuilder.orientation;
    }
    
    public static class ClasseBuilder {
        private SimpleStringProperty sigle;
        private SimpleStringProperty orientation;
        private SimpleIntegerProperty annee;

        public ClasseBuilder setSigle(String sigle) {
            this.sigle = new SimpleStringProperty(sigle);
            return this;
        }

        public ClasseBuilder setOrientation(String orientation) {
            this.orientation = new SimpleStringProperty(orientation);
            return this;
        }

        public ClasseBuilder setAnnee(int annee) {
            this.annee = new SimpleIntegerProperty(annee);
            return this;
        }
        
        public Classes build() throws Exception{
            if( sigle == null || orientation == null ) {
                throw new Exception ("Information manquantes");
            }
            else if( sigle.get().trim().equals("") || orientation.get().trim().equals("") ) {
                throw new Exception ("Information vide");
            }
            return new Classes(this);
        }
        
        
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
    
      @Override
    public String toString() {
        return "Classes{" + "sigle=" + sigle.get() + ", orientation=" +
                ( orientation != null ? orientation.get() : null)
                + ", annee=" +
                ( annee != null ? annee.get() : 0) 
                + '}';
    }
 
    
}
