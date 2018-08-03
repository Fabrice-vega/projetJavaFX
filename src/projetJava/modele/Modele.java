/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.modele;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import projetJava.vue_controleur.ScreensController;

/**
 *
 * @author Fabrice
 */
public class Modele {

    private List<Classes> mesClasses;
    private List<Enseignant> mesEnseignants;
    private List<Attribution> mesAttributions;

    private static Modele instance = null;
    private String notification;

    /**
     * Méthode qui gère l'instance
     *
     * @return l'état de l'instance
     */
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

    /**
     * Méthode qui remplit les listes
     */
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

    /**
     * Méthode populate pour l'observer
     *
     * @param observer
     */
    public void populate(ScreensController observer) {
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
        mesEnseignants.get(0).add(observer);
        mesEnseignants.get(1).add(observer);
        mesEnseignants.get(2).add(observer);
        mesEnseignants.get(3).add(observer);
        mesEnseignants.get(4).add(observer);
        mesEnseignants.get(5).add(observer);
    }

    /**
     * Méthode d'ajout de classe
     *
     * @param classe la classe à ajouter
     * @return l'état de l'ajout de la classe
     */
    public Boolean ajoutClasses(Classes classe) {
        if (mesClasses.contains(classe)) {
            return false;
        }
        mesClasses.add(classe);
        return true;
    }

    /**
     * Méthode de modification d'une classe
     *
     * @param ancClasse la classe à modifier
     * @param nouvClasse la classe modifiée
     * @return l'état de la modification
     */
    public Boolean modifClasse(Classes ancClasse, Classes nouvClasse) {
        int index = mesClasses.indexOf(ancClasse);
        if (mesClasses.contains(nouvClasse) && !nouvClasse.getSigle().equals(ancClasse.getSigle())) {
            return false;
        }
        mesClasses.set(index, nouvClasse);
        return true;
    }

    /**
     * Méthode de suppression d'une classe
     *
     * @param classe la classe à supprimer
     * @return l'état de la classe
     */
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

    /**
     * Méthode de suppression de toutes les classe
     */
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

    /**
     * Méthode de récupération des classes
     *
     * @return la liste des classes
     */
    public List<Classes> getClasses() {
        return mesClasses;
    }

    /**
     * Méthode d'ajout d'un enseignant
     *
     * @param enseignant l'enseignant à ajouter
     * @return l'état de l'ajout de l'enseignant
     */
    public Boolean ajoutEnseignants(Enseignant enseignant) {
        if (mesEnseignants.contains(enseignant)) {
            return false;
        }
        mesEnseignants.add(enseignant);
        return true;
    }

    /**
     * Méthode de modification d'un enseignant
     *
     * @param ancEnseignant l'enseignant à modifier
     * @param nouvEnseignant l'enseignant modifié
     * @return l'état de la modification
     */
    public Boolean modifEnseignant(Enseignant ancEnseignant, Enseignant nouvEnseignant) {
        int index = mesEnseignants.indexOf(ancEnseignant);
        if (mesEnseignants.contains(nouvEnseignant) && !nouvEnseignant.getId_prof().equals(ancEnseignant.getId_prof())) {
            return false;
        }
        mesEnseignants.set(index, nouvEnseignant);
        return true;
    }

    /**
     * Méthode de suppression d'un enseignant
     *
     * @param enseignant l'enseignant à supprimer
     * @return l'état de suppression
     */
    public Boolean supEnseignants(Enseignant enseignant) {
        int index = mesEnseignants.indexOf(enseignant);
        if (enseignant.getTitulaire() != null || enseignant.getRemplacant() != null) {
            return false;
        }
        mesEnseignants.remove(index);
        return true;
    }

    /**
     * Méthode de suppression de tout les enseignants
     */
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

    /**
     * Méthode de récupération des enseignants
     *
     * @return la liste des enseignants
     */
    public List<Enseignant> getMesEnseignants() {
        return mesEnseignants;
    }

    /**
     * Méthode d'ajout d'une attribution
     *
     * @param attribution l'attribution à ajouter
     */
    public void ajoutAttribution(Attribution attribution) {
        this.mesAttributions.add(attribution);
    }

    /**
     * Méthode de récupération des attributions
     *
     * @return la liste des attributions
     */
    public List<Attribution> getMesAttributions() {
        return mesAttributions;
    }

    /**
     * Méthode de suppression d'une attribution
     *
     * @param attribution l'attribution à supprimer
     */
    public void supAttribution(Attribution attribution) {
        mesAttributions.remove(attribution);
    }

    /**
     * Méthode de suppression de toutes les attributions
     */
    public void supAttributionTot() {
        mesAttributions.removeAll(mesAttributions);
    }

    /**
     * Méthode de modification d'une attribution
     *
     * @param attribution l'attribution à modifier
     * @param titulaire le poste à assigner
     */
    public void modifAttribution(Attribution attribution, boolean titulaire) {
        if (titulaire) {
            attribution.getEnseignant().setRemplacant(null);
            attribution.getEnseignant().setTitulaire(attribution.getClasse());
            attribution.setPoste("TITULAIRE");
        } else {
            attribution.getEnseignant().setTitulaire(null);
            attribution.getEnseignant().setRemplacant(attribution.getClasse());
            attribution.setPoste("REMPLACANT");
        }
    }

    /**
     * Méthode d'actualisation de l'observer
     *
     * @param notification la notification modifier
     */
    public void actualise(String notification) {
        this.notification = notification;
    }

    /**
     * Méthode d'écriture fichier pour l'observer
     */
    public void sendMail() {
        File fichier;
        FileWriter fileWriter = null;
        try {
            fichier = new File("src/projetJava/resource/mail.txt");
            fileWriter = new FileWriter(fichier, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(this.notification);
        } catch (IOException e) {
            System.err.println("Problème d'accès " + e.getMessage());
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Erreur " + e.getMessage());
            }
        }
    }

}
