package autop;

import javafx.application.Application;
import javafx.stage.Stage;

public class AutoP extends Application {
    public static Stage window;
    @Override
    public void start(Stage window){
        window.setTitle("Test AutoP");
        window.setScene(new Populate().populate());
        window.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
