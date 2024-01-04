package ru.georgdeveloper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Connection {

    public static void main(String[] args) {
        System.out.println("Старт сервера");
        try(ServerSocket serverSocket = new ServerSocket(8081);
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        ){
            dataOutputStream.writeUTF("Сервер запущен");
            var request = dataInputStream.readUTF();
            while (!request.equals("exit")){
                System.out.println("Клиент: " + request);
                dataOutputStream.writeUTF("Ответ сервера: " + request + "!!!");
                request = dataInputStream.readUTF();
            }

        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }


}
