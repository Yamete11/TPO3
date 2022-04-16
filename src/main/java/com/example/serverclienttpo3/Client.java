package com.example.serverclienttpo3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 80;
    private static Socket socket, socket2;
    private static ServerSocket serverSocket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void  startConnection(String message) throws IOException {

        socket = new Socket("localhost", PORT);
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.println(message + "," + PORT);
        out.flush();

        serverSocket = new ServerSocket(90);
        socket2 = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        String line = in.readLine();
        System.out.println(line);



    }


    public static void sendMessage(String message){
        //System.out.println(message);
        out.println(message + "," + PORT);
        out.flush();
    }

    public static String readMessage() throws IOException {
        return in.readLine();
    }
}
