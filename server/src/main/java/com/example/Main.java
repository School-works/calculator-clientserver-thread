package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        try {
            ServerSocket myServerSocket = new ServerSocket(3000);
            System.out.println("Server connesso...");
            
            while (true) {
                Socket clientSocket = myServerSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

/*
    1 = +
    2 = -
    3 = *
    4 = \
    0 = exit
 */


        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String receivedString = in.readLine();
                System.out.println(receivedString);

                clientSocket.close();
                System.out.println("Client disconnesso");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}