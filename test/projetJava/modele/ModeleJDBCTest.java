/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.modele;

import org.junit.Test;
import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author Fabrice
 */
public class ModeleJDBCTest {

    /**
     * Test of ajoutClasses method, of class ModeleJDBC.
     */
    @Test
    public void testAjoutClasses() {
        System.out.println("ajoutClasses");
        Classes classe = new Classes("0Z", "ZAZA", 0);
        ModeleJDBC instance = ModeleJDBC.getInstance();
        Boolean expResult = true;
        Boolean result = instance.ajoutClasses(classe);
        assertEquals("Ajout", expResult, result);
        expResult = false;
        result = instance.ajoutClasses(classe);
        assertEquals("Existe déjà", expResult, result);
        instance.supClasses(classe);
    }

    /**
     * Test of supClasses method, of class ModeleJDBC.
     */
    @Test
    public void testSupClasses() {
        System.out.println("supClasses");
        Classes classe = new Classes("9Z", "ZETA", 9);
        ModeleJDBC instance = ModeleJDBC.getInstance();
        Boolean expResult = true;
        instance.ajoutClasses(classe);
        Boolean result = instance.supClasses(classe);
        assertEquals("Suppression", expResult, result);
        instance.ajoutClasses(classe);
        Enseignant enseignant = new Enseignant("BOTO", "BOBO", "TOTO");
        enseignant.setTitulaire(classe);
        instance.ajoutEnseignants(enseignant);
        Attribution attribution = new Attribution(classe, enseignant);
        instance.ajoutAttribution(attribution);
        expResult = false;
        result = instance.supClasses(classe);
        assertEquals("Classe attribuée", expResult, result);
        instance.supAttribution(attribution);
        enseignant.setTitulaire(null);
        instance.supEnseignants(enseignant);
        instance.supClasses(classe);
    }

    /**
     * Test of getClasse method, of class ModeleJDBC.
     */
    @Test
    public void testGetClasse() {
        System.out.println("getClasse");
        int idClasse = 1;
        ModeleJDBC instance = ModeleJDBC.getInstance();
        Classes expResult = new Classes("1I", "INFO", 1);
        Classes result = instance.getClasse(idClasse);
        assertEquals("Récupération", expResult, result);
    }

    /**
     * Test of modifClasse method, of class ModeleJDBC.
     */
    @Test
    public void testModifClasse() {
        System.out.println("modifClasse");
        Classes ancClasse = new Classes("4D", "DROIT", 4);
        Classes nouvClasse = new Classes("6D", "DROIT", 6);
        ModeleJDBC instance = ModeleJDBC.getInstance();
        Boolean expResult = true;
        instance.ajoutClasses(ancClasse);
        Boolean result = instance.modifClasse(ancClasse, nouvClasse);
        assertEquals("modification", expResult, result);
        Classes autreClasse = new Classes("9Z", "ZETA", 9);
        instance.ajoutClasses(autreClasse);
        expResult = false;
        result = instance.modifClasse(autreClasse, nouvClasse);
        assertEquals("sigle existe déjà", expResult, result);
        instance.supClasses(ancClasse);
        instance.supClasses(nouvClasse);
        instance.supClasses(autreClasse);
    }

    /**
     * Test of ajoutEnseignants method, of class ModeleJDBC.
     */
    @Test
    public void testAjoutEnseignants() {
        System.out.println("ajoutEnseignants");
        Enseignant enseignant = new Enseignant("ZIZI", "ZIOU", "ZIU");
        ModeleJDBC instance = ModeleJDBC.getInstance();
        Boolean expResult = true;
        Boolean result = instance.ajoutEnseignants(enseignant);
        assertEquals("Ajout", expResult, result);
        expResult = false;
        result = instance.ajoutEnseignants(enseignant);
        assertEquals("Existe déjà", expResult, result);
        instance.supEnseignants(enseignant);
    }

    /**
     * Test of supEnseignants method, of class ModeleJDBC.
     */
    @Test
    public void testSupEnseignants() {
        System.out.println("supEnseignants");
        Enseignant enseignant = new Enseignant("ZIZI", "ZIOU", "ZIU");
        ModeleJDBC instance = ModeleJDBC.getInstance();
        Boolean expResult = true;
        instance.ajoutEnseignants(enseignant);
        Boolean result = instance.supEnseignants(enseignant);
        assertEquals("Suppression", expResult, result);
        Classes classe = new Classes("9Z", "ZETA", 9);
        instance.ajoutClasses(classe);
        enseignant.setTitulaire(classe);
        instance.ajoutEnseignants(enseignant);
        Attribution attribution = new Attribution(classe, enseignant);
        instance.ajoutAttribution(attribution);
        expResult = false;
        result = instance.supEnseignants(enseignant);
        assertEquals("Enseignant attribuée", expResult, result);
        instance.supAttribution(attribution);
        instance.supEnseignants(enseignant);
        instance.supClasses(classe);
    }

    /**
     * Test of modifEnseignant method, of class ModeleJDBC.
     */
    @Test
    public void testModifEnseignant() {
        System.out.println("modifEnseignant");
        Enseignant ancEnseignant = new Enseignant("DUPA", "DURAND", "PAUL");
        Enseignant nouvEnseignant = new Enseignant("ZUZU", "ZUOU", "ZUU");
        ModeleJDBC instance = ModeleJDBC.getInstance();
        Boolean expResult = true;
        Boolean result = instance.modifEnseignant(ancEnseignant, nouvEnseignant);
        assertEquals("modification", expResult, result);
        Enseignant autreEnseignant = new Enseignant("TOPA", "TOUCHE", "PAS");
        instance.ajoutEnseignants(autreEnseignant);
        expResult = false;
        result = instance.modifEnseignant(autreEnseignant, nouvEnseignant);
        assertEquals("id_prof existe déjà", expResult, result);
        instance.modifEnseignant(nouvEnseignant, new Enseignant("DUPA", "DURAND", "PAUL"));
    }

    /**
     * Test of getEnseignant method, of class ModeleJDBC.
     */
    @Test
    public void testGetEnseignant() {
        System.out.println("getEnseignant");
        int idEnseignant = 1;
        ModeleJDBC instance = ModeleJDBC.getInstance();
        Enseignant expResult = new Enseignant("DUPA", "DURAND", "PAUL");
        Enseignant result = instance.getEnseignant(idEnseignant);
        assertEquals("Récupération", expResult, result);
    }

    /**
     * Test of ajoutAttribution method, of class ModeleJDBC.
     */
    @Test
    public void testAjoutAttribution() {
        System.out.println("ajoutAttribution");
        Classes classe = new Classes("9O", "OBLIVION", 9);
        Enseignant enseignant = new Enseignant("TOPA", "TOUCHE", "PAS");
        Attribution attribution = new Attribution(classe, enseignant);
        ModeleJDBC instance = ModeleJDBC.getInstance();
        instance.ajoutClasses(classe);
        enseignant.setRemplacant(classe);
        instance.ajoutAttribution(attribution);
        Classes expResult = classe;
        Classes result = instance.getEnseignant(4).getRemplacant();
        assertEquals("Attribution", expResult, result);
        instance.supAttribution(attribution);
        instance.supClasses(classe);
        //on vérifie que la classe a bien été attribué à l'enseignant via la variable d'instance remplacant.
    }

    /**
     * Test of modifAttribution method, of class ModeleJDBC.
     */
    @Test
    public void testModifAttribution() {
        System.out.println("modifAttribution");
        Attribution attribution = null;
        boolean titulaire = false;
        ModeleJDBC instance = null;
        instance.modifAttribution(attribution, titulaire);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supAttribution method, of class ModeleJDBC.
     */
    @Test
    public void testSupAttribution() {
        System.out.println("supAttribution");
        Attribution attribution = null;
        ModeleJDBC instance = null;
        instance.supAttribution(attribution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
