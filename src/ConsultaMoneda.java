import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    private static final String API_KEY = "409a3a6a7d60abb97fc884ab";

    public Moneda buscarMoneda(String base, String destino) {
        String direccion = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + base;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            if (!json.get("result").getAsString().equals("success")) {
                throw new RuntimeException("No se pudo obtener datos de la API.");
            }

            double valor = json.getAsJsonObject("conversion_rates").get(destino).getAsDouble();
            return new Moneda(base, destino, valor);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo realizar la conversión: " + e.getMessage());
        }
    }
}
