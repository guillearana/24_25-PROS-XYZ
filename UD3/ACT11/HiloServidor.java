package UD3.ACT11;

import java.io.*;
import java.net.Socket;

public class HiloServidor extends Thread {
    private final Socket clientSocket; // Socket para la conexión con el cliente

    // Constructor que recibe el socket del cliente
    public HiloServidor(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Para leer datos del cliente
             PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) { // Para enviar datos al cliente

            String message;
            while ((message = input.readLine()) != null) { // Lee los mensajes del cliente
                System.out.println("Comunico con: " + clientSocket);

                if ("*".equals(message)) { // Si el mensaje es "*", termina la conexión
                    System.out.println("Fin de la conexión con: " + clientSocket);
                    break; // Sale del bucle si el mensaje es "*"
                }

                // Convierte el mensaje a mayúsculas y lo envía al cliente
                output.println(message.toUpperCase());
            }
        } catch (IOException e) { // Manejo de errores de comunicación
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close(); // Cierra el socket del cliente
            } catch (IOException e) { // Manejo de errores al cerrar el socket
                System.err.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}
