package ru.blmz.Server;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.blmz.Server.util.stage.StageStandart;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;


public class Main extends Application {

    public static Stage stage;

    public static void main(String[] args) throws Exception {
        for (String arg :
                args) {
            System.out.println(arg + ":true");
        }

            launch(args);


    }


    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        Main.stage.setMinWidth(854);
        Main.stage.setMinHeight(442);
        Main.stage.setWidth(854);
        Main.stage.setHeight(442);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        new StageStandart("main", false,
                stage, "Server");
    }
}