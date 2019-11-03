
package campeonato_brasileiro;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Campeonato_Brasileiro extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Campeonato_Brasileiro.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Campeonato Brasileiro"); //Insere título
        stage.getIcons().add(new Image("file:images/soccer.png")); //Insere ícone
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
