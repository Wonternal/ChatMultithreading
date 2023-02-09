package server;

import server.threads.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8500);
            while (true) {

                System.out.println("===============");
                System.out.println("Esperando por cliente");
                Socket clientSocket = serverSocket.accept();
                ClientHandler newClient = new ClientHandler(clientSocket);
                newClient.start();

            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
