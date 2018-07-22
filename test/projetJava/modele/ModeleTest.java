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

/**
 *
 * @author Fabrice
 */
public class ModeleTest {
    
    public ModeleTest() {
    }

    /**
     * Test of ajoutClasses method, of class Modele.
     */
    @Test
    public void testAjoutClasses() {
        System.out.println("ajoutClasses");
        Classes classe = new Classes("1E", "eco", 1);
        Modele instance = Modele.getInstance();
        Classes expResult = classe;
        instance.ajoutClasses(classe);
        Classes result = instance.getClasse(classe);
        assertEquals("ajout", expResult, result);
        
        Boolean expResult2 = false;
        Boolean Result2 = instance.ajoutClasses(classe);
        assertEquals("existe deja", expResult2, Result2);
       
    }

    /**
     * Test of supClasses method, of class Modele.
     */
    @Test
    public void testSupClasses() {
        System.out.println("supClasses");
        Classes classe = null;
        Modele instance = null;
        instance.supClasses(classe);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supClassesTot method, of class Modele.
     */
    @Test
    public void testSupClassesTot() {
        System.out.println("supClassesTot");
        Modele instance = null;
        instance.supClassesTot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClasses method, of class Modele.
     */
    @Test
    public void testGetClasses() {
        System.out.println("getClasses");
        Modele instance = null;
        List<Classes> expResult = null;
        List<Classes> result = instance.getClasses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifClasse method, of class Modele.
     */
    @Test
    public void testModifClasse() {
        System.out.println("modifClasse");
        Classes ancClasse = null;
        Classes nouvClasse = null;
        Modele instance = null;
        instance.modifClasse(ancClasse, nouvClasse);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajoutEnseignants method, of class Modele.
     */
    @Test
    public void testAjoutEnseignants() {
        System.out.println("ajoutEnseignants");
        Enseignant enseignant = null;
        Modele instance = null;
        instance.ajoutEnseignants(enseignant);
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
        Modele instance = null;
        instance.supEnseignants(enseignant);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supEnseignantsTot method, of class Modele.
     */
    @Test
    public void testSupEnseignantsTot() {
        System.out.println("supEnseignantsTot");
        Modele instance = null;
        instance.supEnseignantsTot();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMesEnseignants method, of class Modele.
     */
    @Test
    public void testGetMesEnseignants() {
        System.out.println("getMesEnseignants");
        Modele instance = null;
        List<Enseignant> expResult = null;
        List<Enseignant> result = instance.getMesEnseignants();
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
        Modele instance = null;
        instance.modifEnseignant(ancEnseignant, nouvEnseignant);
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
        Modele instance = null;
        instance.ajoutAttribution(attribution);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
