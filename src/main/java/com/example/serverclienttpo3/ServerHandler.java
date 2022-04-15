package com.example.serverclienttpo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable{

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter outToClient = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = inFromClient.readLine();
            String [] arr = line.split(",");




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
