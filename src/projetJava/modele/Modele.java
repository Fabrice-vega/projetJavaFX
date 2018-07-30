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

    public Boolean ajoutClasses(Classes classe) {
        if (mesClasses.contains(classe)) {
            return false;
        }
        mesClasses.add(classe);
        return true;
    }

    public Boolean modifClasse(Classes ancClasse, Classes nouvClasse) {
        int index = mesClasses.indexOf(ancClasse);
        if (mesClasses.contains(nouvClasse) && !nouvClasse.getSigle().equals(ancClasse.getSigle())) {
            return false;
        }
        mesClasses.set(index, nouvClasse);
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

    public Boolean ajoutEnseignants(Enseignant enseignant) {
        if (mesEnseignants.contains(enseignant)) {
            Alert alertDoublon = new Alert(Alert.AlertType.ERROR, "Enseignant déjà créée");
            alertDoublon.setTitle("Erreur...");
            alertDoublon.show();
            return false;
        }
        mesEnseignants.add(enseignant);
        return true;
    }

    public Boolean modifEnseignant(Enseignant ancEnseignant, Enseignant nouvEnseignant) {
        int index = mesEnseignants.indexOf(ancEnseignant);
        if (mesEnseignants.contains(nouvEnseignant) && !nouvEnseignant.getId_prof().equals(ancEnseignant.getId_prof())) {
            Alert alertDoublon = new Alert(Alert.AlertType.ERROR, "Enseignant déjà existant");
            alertDoublon.setTitle("Erreur...");
            alertDoublon.show();
            return false;
        }
        mesEnseignants.set(index, nouvEnseignant);
        return true;
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

    public void actualise(String notification) {
        this.notification = notification;
    }

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
