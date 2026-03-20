import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {
    public static void main(String[] args) {
        // Configuration du serveur HTTP
        URI baseUri = URI.create("http://localhost:8080/");
        ResourceConfig config = new ResourceConfig().packages("com.example.resources"); 

        // Démarrage 
        JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Serveur démarré sur " + baseUri);
    }
}