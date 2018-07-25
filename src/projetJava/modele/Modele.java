/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.modele;

import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabrice
 */
public class Modele {

    private List<Classes> mesClasses;
    private List<Enseignant> mesEnseignants;
    private List<Attribution> mesAttributions;

    private static Modele instance = null;

    public static Modele getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new Modele();
            return instance;
        }
    }

    protected Modele() {
        this.mesClasses = new ArrayList<>();
        this.mesEnseignants = new ArrayList<>();
        this.mesAttributions = new ArrayList<>();
    }

    public void populate() {
        mesClasses.add(new Classes("1P", "PRESCOLAIRE", 1));
        mesClasses.add(new Classes("2P", "PRESCOLAIRE", 2));
        mesClasses.add(new Classes("1I", "INFORMATIQUE", 1));
        mesClasses.add(new Classes("2I", "INFORMATIQUE", 2));
        mesClasses.add(new Classes("2D", "DROIT", 2));
        mesClasses.add(new Classes("3D", "DROIT", 3));
        mesClasses.add(new Classes("2C", "COMPTABILITE", 2));
        mesEnseignants.add(new Enseignant("VEFA", "VEGA", "FABRICE"));
        mesEnseignants.add(new Enseignant("GALA", "GALLET", "LAURA"));
        mesEnseignants.add(new Enseignant("SOGA", "SOUDANT", "GAETAN"));
        mesEnseignants.add(new Enseignant("GLTH", "GLIBERT", "THOMAS"));
        mesEnseignants.add(new Enseignant("DUGA", "DUFRANE", "GABRIEL"));
        mesEnseignants.add(new Enseignant("LIAL", "LIBERT", "ALEXANDRE"));
    }

    public Boolean ajoutClasses(Classes classe) {
        if (mesClasses.contains(classe)) {
            return false;
        }
        mesClasses.add(classe);
        return true;
    }

    public Boolean supClasses(Classes classe) {
        int index = mesClasses.indexOf(classe);
        Classes classeExist = mesClasses.get(index);
        boolean flag = false;
        for (Enseignant mesEnseignant : mesEnseignants) {
            Classes classEnseignantTitulaire = mesEnseignant.getTitulaire();
            Classes classEnseignantRemplacant = mesEnseignant.getRemplacant();
            if (classeExist.equals(classEnseignantTitulaire) || classeExist.equals(classEnseignantRemplacant)) {
                flag = true;
            }
        }
        if (!flag) {
            mesClasses.remove(index);
            return true;
        } else {
            return false;
        }
    }

    public void supClassesTot() {
        List<Classes> classeAGarder = new ArrayList<>();
        mesClasses.forEach((classe -> {
            mesEnseignants.forEach((enseignant -> {
                if (classe.equals(enseignant.getTitulaire()) || classe.equals(enseignant.getRemplacant())) {
                    classeAGarder.add(classe);
                }
            }));
        }));
        mesClasses.removeAll(mesClasses);
        mesClasses.addAll(classeAGarder);
    }

    public List<Classes> getClasses() {
        return mesClasses;
    }

    /*public Classes getClasse(Classes classe) {
        int index = mesClasses.indexOf(classe);
        if (index < 0) {
            return null;
        } else {
            return mesClasses.get(index);
        }
    }*/

    public void modifClasse(Classes ancClasse, Classes nouvClasse) {
        int index = mesClasses.indexOf(ancClasse);
        mesClasses.set(index, nouvClasse);
    }

    public void ajoutEnseignants(Enseignant enseignant) {
        mesEnseignants.add(enseignant);
    }

    public Boolean supEnseignants(Enseignant enseignant) {
        int index = mesEnseignants.indexOf(enseignant);
        if (enseignant.getTitulaire() != null || enseignant.getRemplacant() != null) {
            return false;
        }
        mesEnseignants.remove(index);
        return true;
    }

    public void supEnseignantsTot() {
        List<Enseignant> enseignantAGarder = new ArrayList<>();

        mesEnseignants.forEach((enseignant -> {
            if (enseignant.getTitulaire() != null || enseignant.getRemplacant() != null) {
                enseignantAGarder.add(enseignant);
            }
        }));

        mesEnseignants.removeAll(mesEnseignants);
        mesEnseignants.addAll(enseignantAGarder);
    }

    public List<Enseignant> getMesEnseignants() {
        return mesEnseignants;
    }

    public void modifEnseignant(Enseignant ancEnseignant, Enseignant nouvEnseignant) {
        int index = mesEnseignants.indexOf(ancEnseignant);
        mesEnseignants.set(index, nouvEnseignant);
    }

    public void ajoutAttribution(Attribution attribution) {
        this.mesAttributions.add(attribution);
    }

    public List<Attribution> getMesAttributions() {
        return mesAttributions;
    }

    public void supAttribution(Attribution attribution) {
        mesAttributions.remove(attribution);
    }

    public void supAttributionTot() {
        mesAttributions.removeAll(mesAttributions);
    }

}
