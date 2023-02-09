package client;

import shared.ChatData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            InetAddress myIp = InetAddress.getLocalHost();
            Socket clientSocket = new Socket(myIp, 8500);
            ObjectOutputStream toServerStream = new ObjectOutputStream(clientSocket.getOutputStream());
            //ObjectInputStream fromServerStream = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Escribe tu nombre: ");
            String name = scanner.nextLine();

            ChatData data = new ChatData();
            data.setName(name);
            while (true) {
                System.out.println("Mensaje: ");
                String message = scanner.nextLine();
                data.setMessage(message);

                data.updateDate();
                System.out.println("ClientApp -> " + data);
                toServerStream.writeObject(data);
                if (message.equals("bye")){
                    break;
                }
            }


        } catch (IOException e) {
            System.out.println("Levanta el servidor");
        }
    }
}
