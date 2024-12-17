import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int port = 6000; // Puerto en el que el servidor escuchará

        try (ServerSocket serverSocket = new ServerSocket(port)) { // Crea un ServerSocket para aceptar conexiones en el puerto especificado
            System.out.println("Server iniciado. Esperando clientes...");

            // Bucle infinito para manejar múltiples clientes
            while (true) {
                Socket clientSocket = serverSocket.accept(); // Acepta una nueva conexión de cliente
                System.out.println("Cliente Conectado.....");
                // Maneja la conexión con el cliente en un hilo separado
                new HiloServidor(clientSocket).start(); // Inicia un nuevo hilo para manejar al cliente
            }
        } catch (IOException e) { // Manejo de errores de entrada/salida
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
