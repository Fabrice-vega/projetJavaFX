/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.classesmetier;

/**
 *
 * @author Fabrice
 */
public class Classes {
    private String sigle;
    private String orientation;
    private int annee;

    public Classes(String sigle, String orientation, int annee) {
        this.sigle = sigle;
        this.orientation = orientation;
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Classes{" + "sigle=" + sigle + ", orientation=" + orientation + ", annee=" + annee + '}';
    }
    
    public Classes (ClasseBuilder classeBuilder) {
        this.sigle = classeBuilder.sigle;
        this.annee = classeBuilder.annee;
        this.orientation = classeBuilder.orientation;
    }
    
    public static class ClasseBuilder {
        private String sigle;
        private String orientation;
        private int annee;

        public ClasseBuilder setSigle(String sigle) {
            this.sigle = sigle;
            return this;
        }

        public ClasseBuilder setOrientation(String orientation) {
            this.orientation = orientation;
            return this;
        }

        public ClasseBuilder setAnnee(int annee) {
            this.annee = annee;
            return this;
        }
        
        public Classes build() throws Exception{
            if( sigle == null || orientation == null ) {
                throw new Exception ("Information manquantes");
            }
            else if( sigle.trim().equals("") || orientation.trim().equals("") ) {
                throw new Exception ("Information vide");
            }
            return new Classes(this);
        }
        
        
    }
    
    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    
}
