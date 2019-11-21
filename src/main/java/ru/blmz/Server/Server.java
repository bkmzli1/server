package ru.blmz.Server;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Runnable {
    static public ListView log;
    static private ServerSocket serverSocket;
    static private Socket socket;
    static private ObjectOutputStream outputStream;
    static private ObjectInputStream inputStream;
    static public ObservableList langs;

    public Server(ObservableList langs, ListView log) {
        Server.langs = langs;
        Server.log = log;
    }

    public static void add(String text) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    Text t = new Text(text);
                    langs.add(t);
                    log.setItems(langs);
                    System.out.println(text);
                } catch (Exception e) {

                }
            }
        });


    }

    int port = 7777;

    @Override
    public void run() {
        add("Server Start");

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            add("Address:" + serverSocket.getLocalSocketAddress());
            while (true) {
                socket = serverSocket.accept();
                try {
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    Object readObject = inputStream.readObject();
                    add("in:" + readObject);
                    JSONObject obj = (JSONObject) readObject;
                    String s = "имя: " + obj.get("name") + "\nвозрост: " + obj.get("age");
                    outputStream.writeObject(s);
                    add("out:" + s);
                } catch (SocketException se) {

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
            add(e.getMessage());

        }
        add("Server stop");
    }
}
