package ru.blmz.Server.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import ru.blmz.Server.Server;

public class MainController {
    public ListView log;
    ObservableList<Object> langs = FXCollections.observableArrayList();

    public void initialize() {
        Server server = new Server(langs,log);
        Thread threadServer = new Thread(server,"Server thread");
        threadServer.start();
    }

}
