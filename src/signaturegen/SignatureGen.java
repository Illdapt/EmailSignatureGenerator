package signaturegen;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author nicktuttle
 */
public class SignatureGen extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SignatureGenFXMLDoc.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Email Signature Generator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("sigIcon.png")));
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
