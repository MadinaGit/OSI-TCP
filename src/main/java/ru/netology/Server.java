package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static Integer port = 8099;

    public static void main(String[] args) {


        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    if (serverSocket.isBound()) {
                        System.out.println("New connection accepted");

                        final String name = in.readLine();

                        out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}