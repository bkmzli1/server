package ru.blmz.Server;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        Text t = new Text(text);
        langs.add(t);
        log.setItems(langs);
        System.out.println(text);
    }

    @Override
    public void run() {
        add("Server Start");

        try {
            serverSocket = new ServerSocket(80);
            //erverSocket.setReuseAddress(true);
            add("port:"+serverSocket.getLocalPort());
            while (true) {
                socket = serverSocket.accept();
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream.writeObject(inputStream.readObject());
                add(inputStream.read() + "");

            }
        } catch (Exception e) {
            add(e.getMessage());
            e.printStackTrace();
        }
        add("Server stop");
    }
}
