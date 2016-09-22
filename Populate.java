package autop;

import java.sql.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Populate {

    PreparedStatement pst;
    ResultSet rs;
    Connection conn = Database.makeConnection();

    Button add;

    public Scene populate() {
        VBox root = new VBox();
        Scene populate = new Scene(root, 350, 400);

        add = new Button("Generate");
        add.setOnAction(e -> {
            Generate.gatherColumnValues();
            Generate.numbs.clear();
            Generate.missing.clear();
        });

        root.getChildren().addAll(add);
        return populate;
    }

}
