package ru.blmz.Server.util.stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.blmz.Server.Main;


import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class StageNotification {
    public StageNotification(String name, Modality applicationModal){
        Stage newWindow = new Stage();
        newWindow.initModality(applicationModal);
        newWindow.initOwner(Main.stage);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml\\" + name + ".fxml")));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        InputStream inputStream = ClassLoader.class.getResourceAsStream("/img/fon1.png");
        try {
            Image image = new Image(inputStream);
            newWindow.getIcons().add(image);
        } catch (NullPointerException e) {
            System.out.println("icon null");
        }
        newWindow.setScene(new Scene(root));
        newWindow.setResizable(false);
        newWindow.show();
    }


}
