import java.net.InetAddress;
import java.net.UnknownHostException;

public class Actividad1 {

    public static void main(String[] args) {
        // Se declara un arreglo de tipo InetAddress que almacenará las direcciones IP
        InetAddress address[];
        
        try {
            // Se obtiene todas las direcciones IP asociadas al nombre de dominio "www.spotify.com"
            address = InetAddress.getAllByName("www.spotify.com");
            // Se imprime un mensaje indicando que las siguientes líneas mostrarán las direcciones asociadas
            System.out.println("Direcciones asociadas a Spotify:");

            // Se recorre el arreglo de direcciones IP y se imprime cada una
            for (int i = 0; i < address.length; i++) {
                System.out.println(address[i]);
            }
            
        } catch (UnknownHostException e) {
            // Si ocurre una excepción al resolver el nombre de dominio, se imprime el mensaje de error
            System.out.println(e.getMessage());
        }
    }
}
