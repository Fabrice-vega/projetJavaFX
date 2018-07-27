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

    public Enseignant(String id_prof, String nom, String prenom) {
        this.id_prof = new SimpleStringProperty(id_prof);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.poste = new SimpleStringProperty("AUCUNE ATTRIBUTION");
    }

    public Enseignant(String id_prof) {
        this.id_prof = new SimpleStringProperty(id_prof);
        this.nom = null;
        this.prenom = null;
        this.poste = null;
    }
    
    
    public Enseignant (EnseignantBuilder enseignantBuilder) {
        this.id_prof = enseignantBuilder.id_prof;
        this.prenom = enseignantBuilder.prenom;
        this.nom = enseignantBuilder.nom;
        this.poste = new SimpleStringProperty("AUCUNE ATTRIBUTION");
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
    
    public String getPoste() {
        return this.poste.get();
    }
    
    public SimpleStringProperty posteProperty() {
        return poste;
    }
    
    public void setPoste(String poste) {
        this.poste.set(poste);
    }
    
    public Classes getTitulaire() {
        return titulaire;
    }
    
    public Classes getRemplacant() {
        return remplacant;
    }

    public void setTitulaire(Classes titulaire) {
        Classes oldClasse = null;
        if(titulaire != null) {
            this.titulaire = titulaire;
        }else {
            oldClasse = this.titulaire;
            this.titulaire = null;
        }
        if(titulaire == null) {
            notify(this.getNom() + " " + this.getPrenom() + " n'est plus titulaire de la " + oldClasse, oldClasse, this);
            this.poste.set("AUCUNE ATTRIBUTION");
        } else{
            notify(this.getNom() + " " + this.getPrenom() + " est le nouveau titulaire de la " + titulaire, titulaire, this);
            this.poste.set("TITULAIRE");
        }
        
    }

    public void setRemplacant(Classes remplacant) {
        this.remplacant = remplacant;
        
        if(remplacant == null) {
            this.poste.set("AUCUNE ATTRIBUTION");
        } else{
            this.poste.set("REMPLACANT");
        }
    }
    
    @Override
    public String toString() {
        return "Enseignant{" + "id_prof=" + id_prof.get() + ", nom=" + 
               ( nom != null ? nom.get() : null)
                + ", prenom=" + 
                ( prenom != null ? prenom.get() : null )
                +", "+  poste.get() + '}';
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
