package projetJava.classesmetier;

import javafx.beans.property.SimpleStringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fabrice
 */
public class Enseignant {
    private final SimpleStringProperty id_prof;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;

    public Enseignant(String id_prof, String nom, String prenom) {
        this.id_prof = new SimpleStringProperty(id_prof);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
    }

    public Enseignant(String id_prof) {
        this.id_prof = new SimpleStringProperty(id_prof);
        this.nom = null;
        this.prenom = null;
    }
    
    
    public Enseignant (EnseignantBuilder enseignantBuilder) {
        this.id_prof = enseignantBuilder.id_prof;
        this.prenom = enseignantBuilder.prenom;
        this.nom = enseignantBuilder.nom;
    }
    
    public static class EnseignantBuilder {
        private SimpleStringProperty id_prof;
        private SimpleStringProperty nom;
        private SimpleStringProperty prenom;

        public EnseignantBuilder setId_prof(String id_prof) {
            this.id_prof = new SimpleStringProperty(id_prof);
            return this;
        }

        public EnseignantBuilder setNom(String nom) {
            this.nom = new SimpleStringProperty(nom);
            return this;
        }

        public EnseignantBuilder setPrenom(String prenom) {
            this.prenom = new SimpleStringProperty(prenom);
            return this;
        }
        
        public Enseignant build() throws Exception{
            if( id_prof == null || nom == null || prenom == null ) {
                throw new Exception ("Information manquantes");
            }
            else if( id_prof.get().trim().equals("") || nom.get().trim().equals("") || prenom.get().trim().equals("")) {
                throw new Exception ("Information vide");
            }
            return new Enseignant(this);
        }
        
        
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
    
    @Override
    public String toString() {
        return "Enseignant{" + "id_prof=" + id_prof.get() + ", nom=" + 
               ( nom != null ? nom.get() : null)
                + ", prenom=" + 
                ( prenom != null ? prenom.get() : null )
                + '}';
    }
}
