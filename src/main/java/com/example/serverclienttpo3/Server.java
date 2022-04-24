package com.example.serverclienttpo3;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {

        try {

            ServerSocket serverSocket = new ServerSocket(80);


            ExecutorService threadService = Executors.newFixedThreadPool(3);

            while(true){
                threadService.submit(new ServerHandler(serverSocket.accept()));
            }
        } catch (IOException e){
            e.printStackTrace();
        }



    }
}
