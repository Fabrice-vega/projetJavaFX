/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.modele;

import java.util.ArrayList;
import java.util.List;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;

/**
 *
 * @author Fabrice
 */
public class Modele {
    private List<Classes> mesClasses;
    private List<Enseignant> mesEnseignants;
    
    private static Modele instance = null;
    public static Modele getInstance() {
        if(instance != null){
            return instance;
        }
        else {
            instance = new Modele();
            return instance;
        }
    }

    private Modele() {
        this.mesClasses = new ArrayList<>();
        this.mesEnseignants = new ArrayList<>();
    }
    
    public void ajoutClasses(Classes classe) {
        mesClasses.add(classe);
    }
    
    public void supClasses(Classes classe) {
        mesClasses.remove(classe);
    }
    
    public void supClassesTot() {
    mesClasses.removeAll(mesClasses);
    }
    
    public List<Classes> getClasses() {
        return mesClasses;
    }
    
    public void modifClasse(Classes ancClasse, Classes nouvClasse) {
        int index = mesClasses.indexOf(ancClasse);
        mesClasses.set(index, nouvClasse);
    }
    
    public void ajoutEnseignants(Enseignant enseignant) {
        mesEnseignants.add(enseignant);
    }

    public void supEnseignants(Enseignant enseignant) {
        mesEnseignants.remove(enseignant);
    }
    
    public void supEnseignantsTot() {
    mesEnseignants.removeAll(mesEnseignants);
    }
    
    public List<Enseignant> getMesEnseignants() {
        return mesEnseignants;
    }

    public void modifEnseignant(Enseignant ancEnseignant, Enseignant nouvEnseignant) {
        int index = mesEnseignants.indexOf(ancEnseignant);
        mesEnseignants.set(index, nouvEnseignant);
    }

    
    
    
}
