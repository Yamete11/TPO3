package com.example.serverclienttpo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable{

    private Socket socket, clientSocket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = inFromClient.readLine();
            System.out.println(line);
            clientSocket = new Socket("localhost", 81);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(line);





        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeConnection(String message) throws IOException {
        clientSocket = new Socket("localhost", 81);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
        out.flush();
    }


}
