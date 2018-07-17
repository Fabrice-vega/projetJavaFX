

package projetJava;

import projetJava.vue_controleur.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import projetJava.modele.Modele;

/**
 *
 * @author Angie
 */
public class ProjetJava extends Application {
    
    public static String screenPrincipal = "principal";
    public static String screenPrincipalFile = "principal.fxml";
    
    public static String screenClasse = "classe";
    public static String screenClasseFile = "classe.fxml";
    
    public static String screenAccueil = "accueil";
    public static String screenAccueilFile = "accueil.fxml";
    
    public static String screenEnseignant = "enseignant";
    public static String screenEnseignantFile = "enseignant.fxml";
    
    @Override
    public void start(Stage primaryStage) {
        
        ScreensController mainContainer = new ScreensController();
        
        
        //charge en mémoire les écrans
        mainContainer.loadScreen(ProjetJava.screenClasse, ProjetJava.screenClasseFile);
        mainContainer.loadScreen(ProjetJava.screenAccueil, ProjetJava.screenAccueilFile);
        mainContainer.loadScreen(ProjetJava.screenEnseignant, ProjetJava.screenEnseignantFile);
        mainContainer.loadScreen(ProjetJava.screenPrincipal, ProjetJava.screenPrincipalFile);
        
        //premier ecran
        mainContainer.setScreen(ProjetJava.screenPrincipal);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Projet Java");
        primaryStage.getIcons().add(new Image("https://thierrymarique.condorcet.be/img/logo_condorcet_transparent_80.png", 300, 300, false, false));
        primaryStage.setResizable(false);
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
