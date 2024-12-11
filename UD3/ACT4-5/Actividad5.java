import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Actividad5 {
    private static void VisualizarConexión() {
        try {
            System.out.println("Conexión con www.vitoria-gasteiz.com");
            System.out.println("==========================");

            // crear cliente
            HttpClient cliente = HttpClient.newHttpClient();

            // crear peticion
            HttpRequest peticion = HttpRequest.newBuilder(new URI("http://www.vitoria-gasteiz.com")).build();

            // enviar peticion y recivirla
            HttpResponse<String> response = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

            // print info
            System.out.println("\tMétodo toString():" + response.toString());

            // print status
            System.out.println("\tMétodo getStatusCode():" + response.statusCode());

            // print headers
            System.out.println("\tMétodo getContentType():" +
                    response.headers().firstValue("Content-Type").orElse("N/A"));
        } catch (Exception e) {
            // manage errors
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        VisualizarConexión();
    }
}