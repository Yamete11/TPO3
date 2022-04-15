package com.example.serverclienttpo3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void  startConnection() throws IOException {
        socket = new Socket("localhost", 80);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }


    public static void sendMessage(String message){
        System.out.println(message);
        out.println(message);
        out.flush();
    }

    public static String readMessage() throws IOException {
        return in.readLine();
    }
}
