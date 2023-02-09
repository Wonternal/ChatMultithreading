package server;

import server.threads.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerApp {
    private static ArrayList<String> mensajes = new ArrayList<>();
    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(8500);

            while (true) {
                System.out.println(mensajes.size());
                for (int i = 0; i < mensajes.size(); i++) {
                    System.out.println(mensajes.get(i));
                }
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

    public static void addMessage(String message) {
        mensajes.add(message);
    }
}
