package ping;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class Actividad2 {

    public static void main(String[] args) {
        // Dirección IP del servidor FTP
        String server = "10.0.2.15"; // Reemplaza por la IP de tu servidor
        String user = "dinux";
        String pass = "dm2";
        int port = 21; // Puerto predeterminado para FTP
        // Nombre del directorio con el primer apellido
        String apellido = "arana";  // Personaliza con tu apellido
        String baseDir = "aplicacionweb" + apellido;
        
        // Crear los subdirectorios
        String[] subDirs = {"html", "imagenes", "css"};
        
        FTPClient ftpClient = new FTPClient();
        
        try {
            // Conexión al servidor FTP
            System.out.println("Nos conectamos a " + server);
            ftpClient.connect(server, port);
            if (ftpClient.login(user, pass)) {
                System.out.println("Login correcto");

                // Imprimir el directorio actual
                System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());

                // Crear estructura de directorios
                if (createDirectories(ftpClient, "aplicacionweb" + apellido)) {
                    System.out.println("Directorios creados...");
                } else {
                    System.out.println("NO SE HAN PODIDO CREAR LOS DIRECTORIOS");
                }

                // Logout
                if (ftpClient.logout()) {
                    System.out.println("Logout del servidor FTP....");
                } else {
                    System.out.println("Error al hacer logout");
                }

                // Desconexión
                ftpClient.disconnect();
                System.out.println("Desconectado...");
            } else {
                System.out.println("Login incorrecto...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static boolean createDirectories(FTPClient ftpClient, String directorio) {
    	try {
            ftpClient.makeDirectory(directorio);
            ftpClient.changeWorkingDirectory(directorio);
            
            // Crear subdirectorios
            ftpClient.makeDirectory("html");
            ftpClient.makeDirectory("imagenes");
            ftpClient.makeDirectory("css");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
