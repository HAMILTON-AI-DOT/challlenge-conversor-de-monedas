import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conversor.RespuestaApi;
import conversor.ResultadoConversion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class ConversorPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lector = new Scanner(System.in);

        Map<Integer, String> monedasDisponibles = new HashMap<>();
        monedasDisponibles.put(1, "ARS"); // beso argentino
        monedasDisponibles.put(2, "BOB"); // peso boliviano
        monedasDisponibles.put(3, "BRL"); // real brasileño
        monedasDisponibles.put(4, "CLP"); // peso chileno
        monedasDisponibles.put(5, "COP"); // peso colombiano
        monedasDisponibles.put(6, "USD"); // dólar estadounidense

        System.out.println("=== Conversor de Monedas con API  ===");

        mostrarOpcionesMoneda(monedasDisponibles);
        System.out.print("Seleccione el número de la moneda de origen: ");
        int opcionOrigen = lector.nextInt();

        mostrarOpcionesMoneda(monedasDisponibles);
        System.out.print("Seleccione el número de la moneda destino: ");
        int opcionDestino = lector.nextInt();

        System.out.print("Ingrese el monto a convertir: ");
        double monto = lector.nextDouble();

        String monedaBase = monedasDisponibles.get(opcionOrigen);
        String monedaDestino = monedasDisponibles.get(opcionDestino);

        if (monedaBase == null || monedaDestino == null) {
            System.out.println("Opción inválida. Finalizando programa.");
            return;
        }

        String enlace = "https://v6.exchangerate-api.com/v6/242b1e409618888a1c7b8543/latest/" + monedaBase;

        HttpResponse<String> respuesta;
        try (HttpClient clienteHttp = HttpClient.newHttpClient()) {
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(enlace))
                    .build();

            respuesta = clienteHttp.send(solicitud, HttpResponse.BodyHandlers.ofString());
        }
        String cuerpoJson = respuesta.body();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RespuestaApi datos = gson.fromJson(cuerpoJson, RespuestaApi.class);

       if (!"success".equals(datos.getResult())) {
            System.out.println("La API devolvió un error.");
            return;
        }

        Double tasa = datos.getConversion_rates().get(monedaDestino);

        if (tasa == null) {
            System.out.println("La moneda destino no está disponible en la API.");
            return;
        }

        ResultadoConversion resultado = new ResultadoConversion(tasa, monto);
        System.out.println("\n" + resultado);

        System.out.println("\nFinalizó la ejecución del programa.");
    }

    private static void mostrarOpcionesMoneda(Map<Integer, String> monedas) {
        System.out.println("Monedas disponibles:");
        for (Map.Entry<Integer, String> entrada : monedas.entrySet()) {
            System.out.println(entrada.getKey() + " - " + entrada.getValue());
        }
    }
}
