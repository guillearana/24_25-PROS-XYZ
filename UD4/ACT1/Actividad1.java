package ping;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class Actividad1 {

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        
        try {
            System.out.println("Nos conectamos a ftp.rediris.es");

            // Conectarse al servidor FTP
            ftpClient.connect("ftp.rediris.es");

            // Iniciar sesión con las credenciales proporcionadas
            boolean login = ftpClient.login("anonymous", "dm2");
            if (login) {
                System.out.println("Login correcto");

                // Obtener y mostrar el directorio actual
                String currentDir = ftpClient.printWorkingDirectory();
                System.out.println("Directorio actual: " + currentDir);

                // Intentar crear el directorio "DM2PROS"
                boolean dirCreated = ftpClient.makeDirectory("DM2PROS");
                if (dirCreated) {
                    System.out.println("Directorio creado....");
                } else {
                    System.out.println("NO SE HA PODIDO CREAR EL DIRECTORIO");
                }

                // Cerrar sesión
                boolean logout = ftpClient.logout();
                if (logout) {
                    System.out.println("Logout del servidor FTP...");
                } else {
                    System.out.println("Error al hacer logout...");
                }
            } else {
                System.out.println("Login incorrecto...");
            }

            // Desconectar del servidor
            ftpClient.disconnect();
            System.out.println("Desconectado...");
        } catch (IOException e) {
            System.out.println("Se ha producido un error de conexión o de comunicación con el servidor FTP.");
            e.printStackTrace();
        }
    }
}

