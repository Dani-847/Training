package org.drk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Aplicación para consultar la API REST de TheTVDB
 * Documentación API: https://github.com/thetvdb/v4-api
 * Base URL: https://api4.thetvdb.com/v4
 */
public class Main {

    private static final String BASE_URL = "https://api4.thetvdb.com/v4";
    private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZ2UiOiIiLCJhcGlrZXkiOiIzMTI4ZWEzZS1mZmMzLTRkNzktYWI3MS00MDE2NDEyMjg1NjgiLCJjb21tdW5pdHlfc3VwcG9ydGVkIjpmYWxzZSwiZXhwIjoxNzcyNDEyMjQ2LCJnZW5kZXIiOiIiLCJoaXRzX3Blcl9kYXkiOjEwMDAwMDAwMCwiaGl0c19wZXJfbW9udGgiOjEwMDAwMDAwMCwiaWQiOiIyOTQxMzY5IiwiaXNfbW9kIjpmYWxzZSwiaXNfc3lzdGVtX2tleSI6ZmFsc2UsImlzX3RydXN0ZWQiOmZhbHNlLCJwaW4iOm51bGwsInJvbGVzIjpbXSwidGVuYW50IjoidHZkYiIsInV1aWQiOiIifQ.J_CW6sb7gr_jMQ6kGcZpQdOp2PraICKXIdy-skPPXRyacxyqBJhgeKXgEtut0bsc7ezihg9PUSaIxARL9G1bQg_EgQQGXs8FZEQwh6DkWY6nlXxLKqxyqxv-jdxhBUSnP2STYBk5CxtS0s1gmyOc_57GjVn5-2pISfm0FheF8_m8SEE6ZNax-yBnF9bZovsmFDnIheq_A-lQFTMClY3-fwI0BT4CX7JnA1lDcw-mVopt8XrPRGx5NpX41ykWWzQKO-iK_SGOZQgizRIwXeQj-7X8uEudOpmA1CynbYk7DdONlpPchwW2jAP6hzWoizidKP7_ttqbeW_MTykMuiihORofxzhrneqHl8-9gYHRfnch3y-jBBfe2wDavYZW15Jqz_UMZYCNd4ASJWw6Xt7DVwvnLHjbYyFeuhasiKH-w9Ob48Krp7a-ukZoacxRfjXMqm6YmbRTCnwqWqVntNMASGBWkJcPBFO2nv6or3sGz3KiN1PkKgl0Afyltk36dnMyklB33OxRsv6KuwpR42fZIEXAOo5PVt3x6XCQXvCYwhPY8oXCYVgxCR4nZyhnp3jR3rf9Ma3NjwYvJruJcNgsUxrMPBhLj0m5gX0h3KqnIg0nKXsLMSFgSo7bOjYgl5vQL9miVE9CNTuGins7BSFwX8QY5nyyOG_JcwHB6Usj7jg";

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws Exception {
        System.out.println("=== 1. SERIES DE DRAGON BALL ===");
        buscarSeriesDragonBall();

        System.out.println("\n=== 2. POSTER SERIE MÁS ANTIGUA (1986) ===");
        obtenerPosterSerieAntigua();

        System.out.println("\n=== 3. PRIMER EPISODIO 3ª TEMPORADA ===");
        obtenerPrimerEpisodioTemporada3();

        System.out.println("\n=== 4. ACTOR DE GOKU EN DRAGONBALL EVOLUTION ===");
        buscarActorGoku();

        System.out.println("\n=== 5. MEJOR PELÍCULA USA 2009 ===");
        buscarMejorPeliculaUSA2009();
    }

    /**
     * Método auxiliar para realizar peticiones GET a la API
     * Todas las peticiones requieren el header Authorization con el Bearer token
     */
    private static String hacerPeticion(String endpoint) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("URL: " + BASE_URL + endpoint);
        return response.body();
    }

    /**
     * PREGUNTA 1: ¿Cuántas series de "Dragon Ball" hay registradas?
     *
     * ENDPOINT USADO: GET /search?query=Dragon+Ball&type=series
     *
     * EXPLICACIÓN:
     * - Usamos el endpoint de búsqueda /search con el parámetro query
     * - Filtramos por type=series para obtener solo series (no películas)
     * - Procesamos los resultados filtrando solo los que contengan "Dragon Ball" en el nombre
     *   ya que la API devuelve resultados que pueden no ser exactos
     */
    private static void buscarSeriesDragonBall() throws Exception {
        // Codificamos el query para URL (espacios = +)
        String query = URLEncoder.encode("Dragon Ball", StandardCharsets.UTF_8);
        String response = hacerPeticion("/search?query=" + query + "&type=series");

        JsonObject json = gson.fromJson(response, JsonObject.class);
        JsonArray data = json.getAsJsonArray("data");

        int count = 0;
        System.out.println("\nSeries que contienen 'Dragon Ball':");
        System.out.println("-".repeat(50));

        // Iteramos sobre todos los resultados
        for (int i = 0; i < data.size(); i++) {
            JsonObject serie = data.get(i).getAsJsonObject();
            String nombre = serie.get("name").getAsString();

            // Filtramos solo las que contengan "Dragon Ball" en el nombre
            // (la API puede devolver resultados parciales)
            if (nombre.toLowerCase().contains("dragon ball")) {
                count++;
                String year = serie.has("year") && !serie.get("year").isJsonNull()
                        ? serie.get("year").getAsString() : "N/A";
                System.out.printf("%d. %s (%s)%n", count, nombre, year);
            }
        }
        System.out.println("-".repeat(50));
        System.out.println("TOTAL series Dragon Ball: " + count);
    }

    /**
     * PREGUNTA 2: URL del poster de la serie más antigua (1986)
     *
     * ENDPOINT USADO: GET /search?query=Dragon+Ball&type=series
     *
     * EXPLICACIÓN:
     * - Buscamos en los resultados la serie que tenga nombre exacto "Dragon Ball" y año 1986
     * - El campo image_url contiene la URL del poster
     * - El tvdb_id es el identificador único de la serie en la plataforma
     */
    private static void obtenerPosterSerieAntigua() throws Exception {
        // Reutilizamos el endpoint de búsqueda
        String query = URLEncoder.encode("Dragon Ball", StandardCharsets.UTF_8);
        String searchResponse = hacerPeticion("/search?query=" + query + "&type=series");

        JsonObject searchJson = gson.fromJson(searchResponse, JsonObject.class);
        JsonArray searchData = searchJson.getAsJsonArray("data");

        String serieId = null;
        String imageUrl = null;
        String nombreSerie = null;
        String yearSerie = null;

        // Buscamos específicamente la serie de 1986 (la original)
        for (int i = 0; i < searchData.size(); i++) {
            JsonObject serie = searchData.get(i).getAsJsonObject();
            String nombre = serie.has("name") && !serie.get("name").isJsonNull()
                    ? serie.get("name").getAsString() : "";
            String year = serie.has("year") && !serie.get("year").isJsonNull()
                    ? serie.get("year").getAsString() : "";

            // Buscamos coincidencia exacta: nombre "Dragon Ball" y año 1986
            if (nombre.equals("Dragon Ball") && year.equals("1986")) {
                serieId = serie.has("tvdb_id") && !serie.get("tvdb_id").isJsonNull()
                        ? serie.get("tvdb_id").getAsString() : null;
                imageUrl = serie.has("image_url") && !serie.get("image_url").isJsonNull()
                        ? serie.get("image_url").getAsString() : "No disponible";
                nombreSerie = nombre;
                yearSerie = year;
                break;
            }
        }

        if (serieId != null) {
            System.out.println("\nSerie: " + nombreSerie);
            System.out.println("Año: " + yearSerie);
            System.out.println("ID: " + serieId);
            System.out.println("URL Poster: " + imageUrl);
        } else {
            System.out.println("No se encontró la serie Dragon Ball de 1986");
        }
    }

    /**
     * PREGUNTA 3: Primer episodio de la 3ª temporada (Saga del Ejército de la Patrulla Roja)
     *
     * ENDPOINT USADO: GET /series/{id}/episodes/default/spa?season=3
     *
     * EXPLICACIÓN:
     * - Usamos el ID de la serie obtenido en la pregunta anterior
     * - El parámetro "spa" indica que queremos las traducciones en español
     * - El parámetro season=3 filtra por la temporada 3
     * - "default" es el tipo de orden de episodios (orden por defecto de emisión)
     */
    private static void obtenerPrimerEpisodioTemporada3() throws Exception {
        // Primero necesitamos obtener el ID de la serie Dragon Ball 1986
        String query = URLEncoder.encode("Dragon Ball", StandardCharsets.UTF_8);
        String searchResponse = hacerPeticion("/search?query=" + query + "&type=series");

        JsonObject searchJson = gson.fromJson(searchResponse, JsonObject.class);
        JsonArray searchData = searchJson.getAsJsonArray("data");

        String serieId = null;

        // Buscamos el ID de Dragon Ball 1986
        for (int i = 0; i < searchData.size(); i++) {
            JsonObject serie = searchData.get(i).getAsJsonObject();
            String nombre = serie.has("name") && !serie.get("name").isJsonNull()
                    ? serie.get("name").getAsString() : "";
            String year = serie.has("year") && !serie.get("year").isJsonNull()
                    ? serie.get("year").getAsString() : "";

            if (nombre.equals("Dragon Ball") && year.equals("1986")) {
                serieId = serie.has("tvdb_id") && !serie.get("tvdb_id").isJsonNull()
                        ? serie.get("tvdb_id").getAsString() : null;
                break;
            }
        }

        if (serieId == null) {
            System.out.println("No se encontró el ID de la serie");
            return;
        }

        // Ahora obtenemos los episodios de la temporada 3 en español
        // Endpoint: /series/{id}/episodes/{season-type}/{lang}?season={num}
        String response = hacerPeticion("/series/" + serieId + "/episodes/default/spa?season=3");

        JsonObject json = gson.fromJson(response, JsonObject.class);

        if (!json.has("data") || json.get("data").isJsonNull()) {
            System.out.println("No se encontraron datos de episodios");
            return;
        }

        JsonObject data = json.getAsJsonObject("data");

        if (!data.has("episodes") || data.get("episodes").isJsonNull()) {
            System.out.println("No hay episodios disponibles");
            return;
        }

        JsonArray episodes = data.getAsJsonArray("episodes");

        if (episodes != null && episodes.size() > 0) {
            // El primer elemento del array es el primer episodio de la temporada
            JsonObject primerEpisodio = episodes.get(0).getAsJsonObject();

            String nombre = primerEpisodio.has("name") && !primerEpisodio.get("name").isJsonNull()
                    ? primerEpisodio.get("name").getAsString() : "N/A";
            String fecha = primerEpisodio.has("aired") && !primerEpisodio.get("aired").isJsonNull()
                    ? primerEpisodio.get("aired").getAsString() : "N/A";
            String descripcion = primerEpisodio.has("overview") && !primerEpisodio.get("overview").isJsonNull()
                    ? primerEpisodio.get("overview").getAsString() : "Sin descripción";

            System.out.println("\nPrimer episodio de la 3ª temporada (Saga Patrulla Roja):");
            System.out.println("-".repeat(50));
            System.out.println("Nombre: " + nombre);
            System.out.println("Fecha emisión: " + fecha);
            System.out.println("Descripción: " + descripcion);
        } else {
            System.out.println("No hay episodios en la temporada 3");
        }
    }

    /**
     * PREGUNTA 4: Actor que hacía de Goku en "Dragonball Evolution" (2009)
     *             y en cuántas películas/series ha participado
     *
     * ENDPOINTS USADOS:
     * 1. GET /search?query=Dragonball+Evolution&type=movie  -> Buscar la película
     * 2. GET /movies/{id}/extended                          -> Obtener detalles con personajes
     * 3. GET /people/{id}/extended                          -> Obtener filmografía del actor
     *
     * EXPLICACIÓN:
     * - Primero buscamos la película para obtener su ID
     * - Con el ID obtenemos los detalles extendidos que incluyen el cast (personajes)
     * - Buscamos el personaje "Goku" y obtenemos el peopleId del actor
     * - Con el peopleId consultamos los detalles del actor para ver sus participaciones
     */
    private static void buscarActorGoku() throws Exception {
        // PASO 1: Buscar la película "Dragonball Evolution"
        String query = URLEncoder.encode("Dragonball Evolution", StandardCharsets.UTF_8);
        String searchResponse = hacerPeticion("/search?query=" + query + "&type=movie");

        JsonObject searchJson = gson.fromJson(searchResponse, JsonObject.class);
        JsonArray searchData = searchJson.getAsJsonArray("data");

        String movieId = null;

        // Buscamos la película de 2009
        for (int i = 0; i < searchData.size(); i++) {
            JsonObject movie = searchData.get(i).getAsJsonObject();
            String nombre = movie.has("name") && !movie.get("name").isJsonNull()
                    ? movie.get("name").getAsString() : "";
            String year = movie.has("year") && !movie.get("year").isJsonNull()
                    ? movie.get("year").getAsString() : "";

            if (nombre.toLowerCase().contains("dragonball") && year.equals("2009")) {
                movieId = movie.has("tvdb_id") && !movie.get("tvdb_id").isJsonNull()
                        ? movie.get("tvdb_id").getAsString() : null;
                System.out.println("Película encontrada: " + nombre + " (" + year + ")");
                System.out.println("ID: " + movieId);
                break;
            }
        }

        if (movieId == null) {
            System.out.println("No se encontró Dragonball Evolution");
            return;
        }

        // PASO 2: Obtener detalles extendidos (incluye cast/personajes)
        String responseMovie = hacerPeticion("/movies/" + movieId + "/extended");
        JsonObject json = gson.fromJson(responseMovie, JsonObject.class);

        if (!json.has("data") || json.get("data").isJsonNull()) {
            System.out.println("No se encontraron datos de la película");
            return;
        }

        JsonObject data = json.getAsJsonObject("data");
        String peliculaNombre = data.has("name") && !data.get("name").isJsonNull()
                ? data.get("name").getAsString() : "N/A";
        System.out.println("\nPelícula: " + peliculaNombre);

        // PASO 3: Buscar al actor que interpreta a Goku
        if (data.has("characters") && !data.get("characters").isJsonNull()) {
            JsonArray characters = data.getAsJsonArray("characters");

            for (int i = 0; i < characters.size(); i++) {
                JsonObject character = characters.get(i).getAsJsonObject();
                String charName = character.has("name") && !character.get("name").isJsonNull()
                        ? character.get("name").getAsString() : "";

                // Buscamos el personaje Goku
                if (charName.toLowerCase().contains("goku")) {
                    String actorName = character.has("personName") && !character.get("personName").isJsonNull()
                            ? character.get("personName").getAsString() : "N/A";
                    long personId = character.has("peopleId") && !character.get("peopleId").isJsonNull()
                            ? character.get("peopleId").getAsLong() : 0;

                    System.out.println("-".repeat(50));
                    System.out.println("Personaje: " + charName);
                    System.out.println("Actor: " + actorName);

                    // PASO 4: Obtener filmografía del actor
                    if (personId > 0) {
                        String responsePeople = hacerPeticion("/people/" + personId + "/extended");
                        JsonObject personJson = gson.fromJson(responsePeople, JsonObject.class);

                        if (personJson.has("data") && !personJson.get("data").isJsonNull()) {
                            JsonObject personData = personJson.getAsJsonObject("data");

                            // El array "characters" contiene todas sus participaciones
                            int totalParticipaciones = 0;
                            if (personData.has("characters") && !personData.get("characters").isJsonNull()) {
                                totalParticipaciones = personData.getAsJsonArray("characters").size();
                            }
                            System.out.println("Participaciones en películas/series: " + totalParticipaciones);
                        }
                    }break;
                }
            }
        } else {
            System.out.println("No hay información de personajes disponible");
        }
    }

    /**
     * PREGUNTA 5: Mejor película USA 2009 valorada (mejor score)
     *
     * ENDPOINT USADO: GET /movies/filter?country=usa&year=2009&sort=score&sortType=desc
     *
     * EXPLICACIÓN:
     * - Usamos el endpoint de filtrado de películas /movies/filter
     * - Parámetros:
     *   - country=usa: Filtra por país de origen USA
     *   - year=2009: Filtra por año de estreno 2009
     *   - sort=score: Ordena por puntuación
     *   - sortType=desc: Orden descendente (mejor valorada primero)
     * - La primera película del resultado será la mejor valorada
     *
     * NOTA: El enunciado menciona que hay 2403 películas USA de 2009.
     *       El actor de la pregunta 4 participó en "La guerra de los mundos"
     *       junto con Tom Cruise (aunque esa película es de 2005, no 2009)
     */
    private static void buscarMejorPeliculaUSA2009() throws Exception {
        // Endpoint con filtros: país, año, ordenación por score descendente
        String response = hacerPeticion("/movies/filter?country=usa&year=2009&sort=score&sortType=desc");

        JsonObject json = gson.fromJson(response, JsonObject.class);
        JsonArray data = json.getAsJsonArray("data");

        if (data != null && data.size() > 0) {
            // La primera película es la mejor valorada (ordenación descendente)
            JsonObject mejorPelicula = data.get(0).getAsJsonObject();

            String nombre = mejorPelicula.has("name") && !mejorPelicula.get("name").isJsonNull()
                    ? mejorPelicula.get("name").getAsString() : "N/A";
            String score = mejorPelicula.has("score") && !mejorPelicula.get("score").isJsonNull()
                    ? mejorPelicula.get("score").getAsString() : "N/A";
            String id = mejorPelicula.has("id") && !mejorPelicula.get("id").isJsonNull()
                    ? mejorPelicula.get("id").getAsString() : "N/A";

            System.out.println("\nMejor película USA 2009 (por score):");
            System.out.println("-".repeat(50));
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Score: " + score);
        } else {
            System.out.println("No se encontraron películas USA de 2009");
        }
    }
}
