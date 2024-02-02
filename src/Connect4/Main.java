package Connect4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class Main extends Application {
    static ConnectFour game;
    
    @Override public void start(Stage primaryStage) throws Exception{ //Sets up stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Connect4");
        primaryStage.setScene(new Scene(root, 300, 275));
        fullscreenFunctionality(primaryStage);
        //Linking Main to Controller
        Controller controller = loader.getController();
        controller.setMainApp(this);
        game = new ConnectFour();
        
        primaryStage.show();
    }
    
    private void fullscreenFunctionality(Stage primaryStage) {
        primaryStage.setFullScreen(true);
        primaryStage.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
