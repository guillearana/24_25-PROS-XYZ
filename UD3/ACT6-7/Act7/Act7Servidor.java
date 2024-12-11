import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Act7Servidor {
    public static void main (String[] args) throws IOException{
        // Se define el puerto en el que el servidor escuchará conexiones
        int puerto = 6013;
        // Se crea un objeto ServerSocket que escucha en el puerto 6013, permitiendo hasta 3 conexiones en espera
        ServerSocket servidor = new ServerSocket(puerto, 3);
        System.out.println("Esperando a los clientes...");

        // Bucle que acepta hasta 3 conexiones de clientes
        for(int contSockets = 0; contSockets < 3; contSockets++){
            // El servidor acepta una nueva conexión de un cliente
            Socket cli = servidor.accept();
            // Se obtiene el flujo de salida del socket para enviar datos al cliente
            OutputStream os = cli.getOutputStream();
            // Se crea un DataOutputStream para enviar datos con formato adecuado (en este caso, un mensaje UTF)
            DataOutputStream dos = new DataOutputStream(os);
            // Se envía un mensaje al cliente con el número de cliente (1, 2 o 3)
            dos.writeUTF("Hola cliente " + (contSockets + 1));
            // Se cierran los flujos de salida y el socket
            os.close();
            dos.close();
        }
        // Se cierra el servidor después de atender las 3 conexiones
        servidor.close();
    }
}
