/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava.modele;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projetJava.classesmetier.Attribution;
import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;
import projetJava.vue_controleur.ScreensController;

/**
 *
 * @author Fabrice
 */
public class ModeleTest {

    /**
     * Test of ajoutClasses method, of class Modele.
     */
    @Test
    public void testAjoutClasses() {
        System.out.println("ajoutClasses");
        Classes classe = new Classes("9D", "DROIT", 9);
        Modele instance = Modele.getInstance();
        Boolean expResult = true;
        Boolean result = instance.ajoutClasses(classe);
        assertEquals("Ajout", expResult, result);
        expResult = false;
        result = instance.ajoutClasses(classe);
        assertEquals("Existe déjà", expResult, result);
        instance.supClasses(classe);
    }

    /**
     * Test of modifClasse method, of class Modele.
     */
    @Test
    public void testModifClasse() {
        System.out.println("modifClasse");
        Classes ancClasse = new Classes("4D", "DROIT", 4);
        Classes nouvClasse = new Classes("6D", "DROIT", 6);
        Modele instance = Modele.getInstance();
        Boolean expResult = true;
        instance.ajoutClasses(ancClasse);
        Boolean result = instance.modifClasse(ancClasse, nouvClasse);
        assertEquals("modification", expResult, result);
        Classes autreClasse = new Classes("9Z", "ZETA", 9);
        instance.ajoutClasses(autreClasse);
        expResult = false;
        result = instance.modifClasse(autreClasse, nouvClasse);
        assertEquals("sigle existe déjà", expResult, result);
        instance.supClassesTot();
    }

    /**
     * Test of supClasses method, of class Modele.
     */
    @Test
    public void testSupClasses() {
        System.out.println("supClasses");
        Classes classe = new Classes("9Z", "ZETA", 9);
        Modele instance = Modele.getInstance();
        Boolean expResult = true;
        instance.ajoutClasses(classe);
        Boolean result = instance.supClasses(classe);
        assertEquals("Suppression", expResult, result);
        instance.ajoutClasses(classe);
        Enseignant enseignant = new Enseignant("BOTO", "BOBO", "TOTO");
        enseignant.setTitulaire(classe);
        instance.ajoutEnseignants(enseignant);
        expResult = false;
        result = instance.supClasses(classe);
        assertEquals("Classe attribuée", expResult, result);
        instance.supEnseignants(enseignant);
        instance.supClasses(classe);
    }

    /**
     * Test of ajoutEnseignants method, of class Modele.
     */
    @Test
    public void testAjoutEnseignants() {
        System.out.println("ajoutEnseignants");
        Enseignant enseignant = null;
        Modele instance = new Modele();
        Boolean expResult = null;
        Boolean result = instance.ajoutEnseignants(enseignant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifEnseignant method, of class Modele.
     */
    @Test
    public void testModifEnseignant() {
        System.out.println("modifEnseignant");
        Enseignant ancEnseignant = null;
        Enseignant nouvEnseignant = null;
        Modele instance = new Modele();
        Boolean expResult = null;
        Boolean result = instance.modifEnseignant(ancEnseignant, nouvEnseignant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supEnseignants method, of class Modele.
     */
    @Test
    public void testSupEnseignants() {
        System.out.println("supEnseignants");
        Enseignant enseignant = null;
        Modele instance = new Modele();
        Boolean expResult = null;
        Boolean result = instance.supEnseignants(enseignant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajoutAttribution method, of class Modele.
     */
    @Test
    public void testAjoutAttribution() {
        System.out.println("ajoutAttribution");
        Attribution attribution = null;
        Modele instance = new Modele();
        instance.ajoutAttribution(attribution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supAttribution method, of class Modele.
     */
    @Test
    public void testSupAttribution() {
        System.out.println("supAttribution");
        Attribution attribution = null;
        Modele instance = new Modele();
        instance.supAttribution(attribution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifAttribution method, of class Modele.
     */
    @Test
    public void testModifAttribution() {
        System.out.println("modifAttribution");
        Attribution attribution = null;
        boolean titulaire = false;
        Modele instance = new Modele();
        instance.modifAttribution(attribution, titulaire);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
