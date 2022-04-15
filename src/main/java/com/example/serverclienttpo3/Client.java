package com.example.serverclienttpo3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 90;
    private static Socket socket;
    private static ServerSocket serverSocket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void  startConnection() throws IOException {
        serverSocket = new ServerSocket(PORT);
        socket = new Socket("localhost", PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }


    public static void sendMessage(String message){
        System.out.println(message);
        out.println(message + PORT);
        out.flush();
    }

    public static String readMessage() throws IOException {
        return in.readLine();
    }
}
