package junior.rafael.Literalura.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIRequest {
    public String request(String book) {
        String bookPesquisa = book.replace(" ", "+");
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://gutendex.com/books/?search=" + bookPesquisa))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            System.out.println("Erro na chamada de API");
            return "Erro";
        }

    }
}
