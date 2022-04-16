package com.example.serverclienttpo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer{


    public static void main(String[] args) {
        try{

            ServerSocket serverSocket = new ServerSocket(81);
            Socket socket = serverSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = inFromClient.readLine();
            System.out.println(line);
            String [] arr = line.split(",");
            if(arr[0].equals("ass")){
                arr[0] = "badAss";
            }
            line = arr[0] + "," + arr[1] + "," + arr[2];
            Socket socket1 = new Socket("localhost", 90);
            PrintWriter outToClient = new PrintWriter(socket1.getOutputStream(), true);
            outToClient.println(line);
            outToClient.flush();

        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
