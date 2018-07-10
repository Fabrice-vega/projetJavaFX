

package projetJava;

import projetJava.vue_controleur.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projetJava.modele.Modele;

/**
 *
 * @author Angie
 */
public class ProjetJava extends Application {
    
   
    
    public static String screenAccueil = "accueil";
    public static String screenAccueilFile = "accueil.fxml";
    
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        
        
        //charge en mémoire les écrans
        
        mainContainer.loadScreen(ProjetJava.screenAccueil, ProjetJava.screenAccueilFile);
        
        
        //premier ecran
        mainContainer.setScreen(ProjetJava.screenAccueil);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
