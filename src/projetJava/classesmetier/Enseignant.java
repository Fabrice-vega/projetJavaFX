package projetJava.classesmetier;

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
    private String id_prof;
    private String nom;
    private String prenom;

    public Enseignant(String id_prof, String nom, String prenom) {
        this.id_prof = id_prof;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Enseignant{" + "id_prof=" + id_prof + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    public Enseignant (EnseignantBuilder enseignantBuilder) {
        this.id_prof = enseignantBuilder.id_prof;
        this.prenom = enseignantBuilder.prenom;
        this.nom = enseignantBuilder.nom;
    }
    
    public static class EnseignantBuilder {
        private String id_prof;
        private String nom;
        private String prenom;

        public EnseignantBuilder setId_prof(String id_prof) {
            this.id_prof = id_prof;
            return this;
        }

        public EnseignantBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public EnseignantBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }
        
        public Enseignant build() throws Exception{
            if( id_prof == null || nom == null || prenom == null ) {
                throw new Exception ("Information manquantes");
            }
            else if( id_prof.trim().equals("") || nom.trim().equals("") || prenom.trim().equals("")) {
                throw new Exception ("Information vide");
            }
            return new Enseignant(this);
        }
        
        
    }

    public String getId_prof() {
        return id_prof;
    }

    public void setId_prof(String id_prof) {
        this.id_prof = id_prof;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    
}
