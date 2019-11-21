package ru.blmz.Server;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws IOException {
        int col = 1;//кол клиентов
        for (int i = 0; i < 1; i++) {
            final Socket[] socket = new Socket[1];//создание сокета
            final ObjectOutputStream[] outputStream = new ObjectOutputStream[1];//создание ввода
            final ObjectInputStream[] inputStream = new ObjectInputStream[1];//создание вывода
            new Thread(new Runnable() {//создаём поток
                @Override
                public void run() {
                    try {
                        while (true) {
                            socket[0] = new Socket(InetAddress.getByName("192.168.0.159"), 7777);//куда отправить данные
                            outputStream[0] = new ObjectOutputStream(socket[0].getOutputStream());//вход данных
                            inputStream[0] = new ObjectInputStream(socket[0].getInputStream());//выход данных
                            System.out.println(inputStream[0].readObject());//получить ответ
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);//задержка
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //while (true) {

                        try {
                            JSONObject obj = new JSONObject();//создаём json
                            //добовляем параметры
                            obj.put("name", "Иванов Михаил");
                            obj.put("age", new Integer(21));

                            outputStream[0].flush();//очитка данных
                            outputStream[0].writeObject(obj);//отправить данные
                            Thread.sleep(1000);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                   // }
                }
            }).start();
        }
    }

}
