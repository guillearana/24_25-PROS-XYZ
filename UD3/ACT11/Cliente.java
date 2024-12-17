
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int port = 6000;          // Puerto de conexión

        System.out.println("PROGRAMA CLIENTE INICIANDO");

        try (Socket socket = new Socket(host, port); // Conexión al servidor
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Para leer datos del servidor
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // Para enviar datos al servidor
             Scanner scanner = new Scanner(System.in)) { // Para leer entrada del usuario

            String userInput;
            do {
                System.out.print("Introduce una cadena: "); // Solicita al usuario una entrada
                userInput = scanner.nextLine(); // Lee la entrada del usuario

                // Envía la entrada al servidor
                output.println(userInput);

                // Recibe y muestra la respuesta del servidor
                String response = input.readLine();
                System.out.println("=>Respuesta:" + response);

            } while (!"*".equals(userInput)); // Termina cuando el usuario ingresa "*"

            System.out.println("Fin del envío....");

        } catch (IOException e) { // Manejo de errores de conexión o comunicación
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
