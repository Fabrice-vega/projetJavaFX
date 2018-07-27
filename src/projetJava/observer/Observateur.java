package projetJava.observer;

import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;


public interface Observateur {

    /**
     * Méthode qui actualise la notification (devra est override)
     *
     * @param notification la notification a actualiser
     */
    void actualise (String notification, Classes classe, Enseignant enseignant);
}
