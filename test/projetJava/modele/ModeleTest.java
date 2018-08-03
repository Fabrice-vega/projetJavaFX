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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
        Modele instance = new Modele();
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
        Modele instance = new Modele();
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
        Modele instance = new Modele();
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
        Enseignant enseignant = new Enseignant("ZIZI", "ZIOU", "ZIU");
        Modele instance = new Modele();
        Boolean expResult = true;
        Boolean result = instance.ajoutEnseignants(enseignant);
        assertEquals("Ajout", expResult, result);
        expResult = false;
        result = instance.ajoutEnseignants(enseignant);
        assertEquals("Existe déjà", expResult, result);
        instance.supEnseignants(enseignant);
    }

    /**
     * Test of modifEnseignant method, of class Modele.
     */
    @Test
    public void testModifEnseignant() {
        System.out.println("modifEnseignant");
        Enseignant ancEnseignant = new Enseignant("ZIZI", "ZIOU", "ZIU");
        Enseignant nouvEnseignant = new Enseignant("ZUZU", "ZUOU", "ZUU");
        Modele instance = new Modele();
        Boolean expResult = true;
        instance.ajoutEnseignants(ancEnseignant);
        Boolean result = instance.modifEnseignant(ancEnseignant, nouvEnseignant);
        assertEquals("modification", expResult, result);
        Enseignant autreEnseignant = new Enseignant("ZOZO", "ZOTA", "ZOYA");
        instance.ajoutEnseignants(autreEnseignant);
        expResult = false;
        result = instance.modifEnseignant(autreEnseignant, nouvEnseignant);
        assertEquals("id_prof existe déjà", expResult, result);
        instance.supEnseignantsTot();
    }

    /**
     * Test of supEnseignants method, of class Modele.
     */
    @Test
    public void testSupEnseignants() {
        System.out.println("supEnseignants");
        Enseignant enseignant = new Enseignant("ZIZI", "ZIOU", "ZIU");
        Modele instance = new Modele();
        Boolean expResult = true;
        instance.ajoutEnseignants(enseignant);
        Boolean result = instance.supEnseignants(enseignant);
        assertEquals("Suppression", expResult, result);
        instance.ajoutEnseignants(enseignant);
        Classes classe = new Classes("9Z", "ZETA", 9);
        enseignant.setTitulaire(classe);
        instance.ajoutClasses(classe);
        expResult = false;
        result = instance.supEnseignants(enseignant);
        assertEquals("Enseignant attribuée", expResult, result);
        instance.supEnseignants(enseignant);
        instance.supClasses(classe);
    }

    /**
     * Test of ajoutAttribution method, of class Modele.
     */
    @Test
    public void testAjoutAttribution() {
        System.out.println("ajoutAttribution");
        Classes classe = new Classes("9O", "OBLIVION", 9);
        Enseignant enseignant = new Enseignant("TOPA", "TOUCHE", "PAS");
        Attribution attribution = new Attribution(classe, enseignant);
        Modele instance = new Modele();
        instance.ajoutClasses(classe);
        enseignant.setRemplacant(classe);
        instance.ajoutAttribution(attribution);
        Attribution expResult = attribution;
        List<Attribution> mesAttributions = instance.getMesAttributions();
        int index = mesAttributions.indexOf(attribution);
        Attribution result = mesAttributions.get(index);
        assertEquals("Attribution", expResult, result);
        instance.supAttribution(attribution);
        instance.supClasses(classe);
    }

    /**
     * Test of supAttribution method, of class Modele.
     */
    @Test
    public void testSupAttribution() {
        System.out.println("supAttribution");
        Classes classe = new Classes("9O", "OBLIVION", 9);
        Enseignant enseignant = new Enseignant("TOPA", "TOUCHE", "PAS");
        Attribution attribution = new Attribution(classe, enseignant);
        Modele instance = new Modele();
        instance.ajoutClasses(classe);
        enseignant.setRemplacant(classe);
        instance.ajoutEnseignants(enseignant);
        instance.ajoutAttribution(attribution);
        int expResult = -1;
        instance.supAttribution(attribution);
        List<Attribution> mesAttributions = instance.getMesAttributions();
        int result = mesAttributions.indexOf(attribution);
        assertEquals("supprimer", expResult, result);
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
