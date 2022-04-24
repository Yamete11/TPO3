package com.example.serverclienttpo3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 80;
    private static final int SERVER_PORT = 90;
    private static Socket socket, socket2;
    private static ServerSocket serverSocket;
    private static BufferedReader in, inFromServer;
    private static PrintWriter out;

    public static String  startConnection(String message) throws IOException {

        socket = new Socket("localhost", PORT);
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println(message + "," + SERVER_PORT);
        out.flush();
        String answer = inFromServer.readLine();
        if(answer.equals("nothing")){
            out.close();
            inFromServer.close();
            socket.close();
            return "nothing";
        }

        serverSocket = new ServerSocket(SERVER_PORT);
        socket2 = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        String line = in.readLine();
        System.out.println(line);
        socket.close();
        socket2.close();
        serverSocket.close();

        return line;
    }
}
