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
public class ModeleJDBCTest {

    /**
     * Test of ajoutClasses method, of class ModeleJDBC.
     */
    @Test
    public void testAjoutClasses() {
        System.out.println("ajoutClasses");
        Classes classe = new Classes("9Z", "DROIT", 8);
        Modele instance = ModeleJDBC.getInstance();
        Boolean expResult = true;
        Boolean result = instance.ajoutClasses(classe);
        assertEquals( "Ajout", expResult, result);
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
        Classes classe = null;
        ModeleJDBC instance = null;
        Boolean expResult = null;
        Boolean result = instance.supClasses(classe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        Classes ancClasse = null;
        Classes nouvClasse = null;
        ModeleJDBC instance = null;
        Boolean expResult = null;
        Boolean result = instance.modifClasse(ancClasse, nouvClasse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajoutEnseignants method, of class ModeleJDBC.
     */
    @Test
    public void testAjoutEnseignants() {
        System.out.println("ajoutEnseignants");
        Enseignant enseignant = null;
        ModeleJDBC instance = null;
        Boolean expResult = null;
        Boolean result = instance.ajoutEnseignants(enseignant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supEnseignants method, of class ModeleJDBC.
     */
    @Test
    public void testSupEnseignants() {
        System.out.println("supEnseignants");
        Enseignant enseignant = null;
        ModeleJDBC instance = null;
        Boolean expResult = null;
        Boolean result = instance.supEnseignants(enseignant);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of modifEnseignant method, of class ModeleJDBC.
     */
    @Test
    public void testModifEnseignant() {
        System.out.println("modifEnseignant");
        Enseignant ancEnseignant = null;
        Enseignant nouvEnseignant = null;
        ModeleJDBC instance = null;
        Boolean expResult = null;
        Boolean result = instance.modifEnseignant(ancEnseignant, nouvEnseignant);
        assertEquals(expResult, result);
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
