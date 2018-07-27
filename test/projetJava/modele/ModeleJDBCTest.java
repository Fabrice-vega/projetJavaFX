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
     * Test of getClasses method, of class ModeleJDBC.
     */
    @Test
    public void testGetClasses() {
        System.out.println("getClasses");
        ModeleJDBC instance = null;
        List<Classes> expResult = null;
        List<Classes> result = instance.getClasses();
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
        int idClasse = 0;
        ModeleJDBC instance = null;
        Classes expResult = null;
        Classes result = instance.getClasse(idClasse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of getMesEnseignants method, of class ModeleJDBC.
     */
    @Test
    public void testGetMesEnseignants() {
        System.out.println("getMesEnseignants");
        ModeleJDBC instance = null;
        List<Enseignant> expResult = null;
        List<Enseignant> result = instance.getMesEnseignants();
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajoutAttribution method, of class ModeleJDBC.
     */
    @Test
    public void testAjoutAttribution() {
        System.out.println("ajoutAttribution");
        Attribution attribution = null;
        ModeleJDBC instance = null;
        instance.ajoutAttribution(attribution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMesAttributions method, of class ModeleJDBC.
     */
    @Test
    public void testGetMesAttributions() {
        System.out.println("getMesAttributions");
        ModeleJDBC instance = null;
        List<Attribution> expResult = null;
        List<Attribution> result = instance.getMesAttributions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

    /**
     * Test of supAttributionTot method, of class ModeleJDBC.
     */
    @Test
    public void testSupAttributionTot() {
        System.out.println("supAttributionTot");
        ModeleJDBC instance = null;
        instance.supAttributionTot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
