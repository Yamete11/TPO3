package com.example.serverclienttpo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer implements Runnable{

    private ServerSocket serverSocket = new ServerSocket(81);
    private Socket socket;

    public ProxyServer() throws IOException {
        socket = serverSocket.accept();

    }

    @Override
    public void run() {
        try{
            PrintWriter outToClient = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
