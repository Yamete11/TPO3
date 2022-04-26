package com.example.serverclienttpo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ProxyServerFR {

    private static Socket socket, socket2;
    private static ServerSocket serverSocket;
    private static BufferedReader in;
    private static PrintWriter out;
    private static Map<String, String> map;
    private static String answer;




    public static void main(String[] args) {
        map = new HashMap<>();
        map.put("planeta", "planète");
        map.put("lodowka", "frigo");
        map.put("szpital", "hospitalier");
        map.put("jedzenie", "aliments");
        map.put("pieniądze", "de l'argent");
        map.put("stół", "table");
        map.put("samolot", "avion");
        map.put("krzesło", "chaise");
        map.put("światło", "légère");
        map.put("ptak", "oiseau");
        try{
            while(true){
                serverSocket = new ServerSocket(83);
                socket = serverSocket.accept();
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String [] line = in.readLine().split(",");


                answer = map.get(line[0]);
                if(answer == null){
                    answer = "*There is no such word. Try again!*";
                }
                socket2 = new Socket("localhost", Integer.parseInt(line[2]));

                out = new PrintWriter(socket2.getOutputStream(), true);
                out.println("Translated word : " + answer);
                out.flush();
                socket2.close();
                out.close();


                in.close();
                serverSocket.close();
                socket.close();

            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
