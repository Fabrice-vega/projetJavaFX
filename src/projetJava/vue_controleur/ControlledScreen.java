
package projetJava.vue_controleur;

import projetJava.modele.Modele;

/**
 *
 * @author Angie
 */
public interface ControlledScreen {
    
    //This method will allow the injection of the Parent ScreenPane
    public void setScreenParent(ScreensController screenPage);
    
    public void setModele(Modele modele);
}
