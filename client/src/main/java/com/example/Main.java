package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserire indirizzo IP:");
        String ip = scanner.nextLine();
        System.out.println("Inserire porta:");
        int port = Integer.parseInt(scanner.nextLine());
        
        try (Socket socket = new Socket(ip, port)) {
            System.out.println("Connesso al server...");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            String string;
            String killerWord = "0";

            do {
                System.out.println("Inserire una stringa:");
                string = scanner.nextLine();
                out.println(string); 
                
                String transformed = in.readLine();
                System.out.println("Stringa trasformata: " + transformed);

            } while (!string.equalsIgnoreCase(killerWord));

            System.out.println("Programma terminato.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}