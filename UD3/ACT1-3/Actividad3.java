import java.net.InetAddress;
import java.net.UnknownHostException;

public class Actividad3 {
    public static void main(String[] args) {
        // Se obtiene la dirección proporcionada como argumento desde la línea de comandos
        String direccion =  args[0];

        try {
            // Se declara un arreglo de tipo InetAddress que almacenará las direcciones IP
            InetAddress address[];
            // Se obtiene todas las direcciones IP asociadas al nombre de dominio pasado como argumento
            address = InetAddress.getAllByName(direccion);
            // Se imprime un mensaje indicando que las siguientes líneas mostrarán las direcciones asociadas
            System.out.println("Direcciones asociadas a " + direccion + " son:");

            // Se recorre el arreglo de direcciones IP y se imprime cada una
            for (int i = 0; i < address.length; i++) {
                System.out.println(address[i]);
            }

        } catch (UnknownHostException e) {            
            try {
                // Si ocurre una excepción al resolver el nombre de dominio, se obtiene la dirección IP local
                InetAddress local;
                InetAddress localAddresses[];
                // Se obtiene la dirección IP de la máquina local
                local = InetAddress.getLocalHost();
                // Se obtiene todas las direcciones asociadas al nombre de la máquina local
                localAddresses = InetAddress.getAllByName(local.getHostName());
                // Se imprime la dirección IP local y el nombre de la máquina
                System.out.println("Dirección IP: " + localAddresses[0].getHostAddress());
                System.out.println("Nombre: " + localAddresses[0].getHostName());
                // Se recorre el arreglo de direcciones locales y se imprime cada una
                for (int i = 0; i < localAddresses.length; i++) {
                    System.out.println(localAddresses[i]);
                }
            } catch (UnknownHostException e1) {
                // Si ocurre un error al obtener las direcciones locales, se imprime el stack trace de la excepción
                e1.printStackTrace();
            }
        }
    }
}
