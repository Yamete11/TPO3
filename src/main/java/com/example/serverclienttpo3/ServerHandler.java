package com.example.serverclienttpo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable{

    private Socket socket, clientSocket;
    private int port;
    boolean check;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(socket.getOutputStream(), true);


            String line = inFromClient.readLine();
            String [] arr = line.split(",");
            String answer = "";

            if(arr[1].equals("EN")){
                port = 81;
            } else if(arr[1].equals("UA")){
                port = 82;
            } else if(arr[1].equals("FR")){
                port = 83;
            } else {
                answer = "nothing";
            }

            outToClient.println(answer);

            /*socket.close();
            inFromClient.close();
            outToClient.close();*/

            if(answer.equals("")){
                System.out.println(line);
                clientSocket = new Socket("localhost", port);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(line);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
