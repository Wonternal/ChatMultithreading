package server.threads;

import server.ServerApp;
import shared.ChatData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {

            String msg = "La conexión se ha realizado con ";
            msg += clientSocket.getInetAddress().toString();
            System.out.println(msg);

            // ObjectOutputStream toClientStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream fromClientStream = new ObjectInputStream(clientSocket.getInputStream());


            while (true) {
                ChatData clientData = (ChatData) fromClientStream.readObject();
                //ServerApp.addMessage(clientData.getName() + " dice: " + clientData.getMessage() + " a las " + clientData.getDate());
                System.out.println("El cliente " + clientData.getName() + " dice: " + clientData.getMessage() + " a las " + clientData.getDate());
            }

        } catch (IOException | ClassNotFoundException err) {
            err.printStackTrace();
        }

    }
}
