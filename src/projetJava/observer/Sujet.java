package projetJava.observer;

import projetJava.classesmetier.Classes;
import projetJava.classesmetier.Enseignant;

import java.util.ArrayList;
import java.util.List;


public abstract class Sujet {

    /**
     * liste des observateurs
     */
    private List<Observateur> observateurs = new ArrayList<>();

    /**
     * méthode d'ajout d'un observateur
     *
     * @param observateur l'observateur à ajouter
     */
    public void add (Observateur observateur) {

        observateurs.add(observateur);
    }

    /**
     * méthode qui retire un observateur
     *
     * @param observateur l'observateur à retirer
     */
    public void remove (Observateur observateur) {

        observateurs.remove(observateur);
    }

    /**
     * méthode qui notifie les observateurs
     *
     * @param notification la notification
     */
    public void notify (String notification, Classes classe, Enseignant enseignant) {

        observateurs.forEach((observateur) -> {
            observateur.actualise(notification, classe, enseignant);
        });
    }
}
