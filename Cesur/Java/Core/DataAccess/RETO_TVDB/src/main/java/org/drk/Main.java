package org.drk;

import com.google.gson.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

public class Main {

    private static final String BASE_URL = "https://api4.thetvdb.com/v4";
    private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZ2UiOiIiLCJhcGlrZXkiOiIzMTI4ZWEzZS1mZmMzLTRkNzktYWI3MS00MDE2NDEyMjg1NjgiLCJjb21tdW5pdHlfc3VwcG9ydGVkIjpmYWxzZSwiZXhwIjoxNzcyNDE2NTEzLCJnZW5kZXIiOiIiLCJoaXRzX3Blcl9kYXkiOjEwMDAwMDAwMCwiaGl0c19wZXJfbW9udGgiOjEwMDAwMDAwMCwiaWQiOiIyOTQxMzY5IiwiaXNfbW9kIjpmYWxzZSwiaXNfc3lzdGVtX2tleSI6ZmFsc2UsImlzX3RydXN0ZWQiOmZhbHNlLCJwaW4iOm51bGwsInJvbGVzIjpbXSwidGVuYW50IjoidHZkYiIsInV1aWQiOiIifQ.Ux-PbBJlhQ9cAbYNpeprVXOSrnbpodYM1UFC0cbzWAVFZgHh2oaqc1qcMtExhJf58jE97uQvXjG_lDkfHFSl-I57GtnIPBVWQAhfNL89j1VbKH4kBzJOdzbGvETu_7aZ5LcZI3UQoAcKhCscmN-VqdfVZqL6sC1JuqQWkqVkwUyul7m2V9RR2939c2n07FdWTBXPf2iNLZ0yUzAOMcE0H_nx5DpYZSVUBCI3VSiLTN6f-Nq6MqBTR0P4olXf6Rc22y__c67YFyy2yfCUAtju_ldebPss0imvBGLBFSOMFUhpqUo2IvOrzWiOKLLijCvMdaLwKPAJG8Ix5Qz6NJuZyNa8z5kRUBmo5ijUBV2_axKluQlCBnQTM-7nylUxwL-neZasDY-DE030w_Yq5itR7-V-0AExlgEWEKni2_71yDzBAGdtgT4HYJ3FFO5dbZXyFL8JaLHl1Fc85tt987-hFAGmF-cq2Vk9V6I7DEr5mikFdgFW2T7tDml0p0QVZ3JwpaKu8oNst0kOimPghFXqx2L_-OcOyyZDuVELEulXYPuBUlmkbaeAsnqHdU1DqfFGT92ZrVYbo0wXf4hSF5MfjxHeL4MOUc15CodeBMebKWVJlycmH-FeTMUfQ9KbvZ6BLDrdYRGS2YHwU4snz8rzaoNRcF2_Unr83yB4Z_FhbLE";

    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    public static void main(String[] args) {
        try {
            System.out.println("╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║              CONSULTAS API TVDB - DRAGON BALL                  ║");
            System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

            List<JsonObject> seriesDragonBall = buscarSeriesDragonBall();
            JsonObject serieMasAntigua = encontrarSerieMasAntigua(seriesDragonBall);

            mostrarEjercicio1(seriesDragonBall);
            mostrarEjercicio2(serieMasAntigua);
            mostrarEjercicio3(serieMasAntigua);
            mostrarEjercicio4();
            mostrarEjercicio5();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // EJERCICIOS
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * EJERCICIO 1: Cuántas series contienen "Dragon Ball" en el título.
     * Muestra el total y la lista de series encontradas.
     */
    private static void mostrarEjercicio1(List<JsonObject> series) {
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 1. SERIES QUE CONTIENEN \"DRAGON BALL\" EN EL TÍTULO            │");
        System.out.println("└────────────────────────────────────────────────────────────────┘");
        System.out.println("  Total: " + series.size() + " series\n");

        for (int i = 0; i < series.size(); i++) {
            JsonObject s = series.get(i);
            String nombre = obtenerNombreDragonBall(s);
            String year = getString(s, "year");
            System.out.printf("  %2d. %s (%s)%n", i + 1, nombre, year != null ? year : "?");
        }
        System.out.println();
    }

    /**
     * EJERCICIO 2: URL del póster de la serie más antigua de Dragon Ball.
     */
    private static void mostrarEjercicio2(JsonObject masAntigua) {
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 2. PÓSTER DE LA SERIE MÁS ANTIGUA                              │");
        System.out.println("└────────────────────────────────────────────────────────────────┘");

        if (masAntigua != null) {
            String nombre = obtenerNombreDragonBall(masAntigua);
            String year = getString(masAntigua, "year");
            String imageUrl = getString(masAntigua, "image_url");

            System.out.println("  Serie: " + nombre + " (" + year + ")");
            System.out.println("  Póster: " + (imageUrl != null ? imageUrl : "No disponible"));
        } else {
            System.out.println("  No se encontró ninguna serie.");
        }
        System.out.println();
    }

    /**
     * EJERCICIO 3: Información del primer episodio de la 3ª temporada en español.
     * @endpoint GET /series/{id}/episodes/default/spa?season=3
     */
    private static void mostrarEjercicio3(JsonObject masAntigua) throws Exception {
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 3. PRIMER EPISODIO DE LA 3ª TEMPORADA (EN ESPAÑOL)             │");
        System.out.println("└────────────────────────────────────────────────────────────────┘");

        if (masAntigua == null) {
            System.out.println("  No hay serie base para consultar.");
            return;
        }

        String serieId = getString(masAntigua, "tvdb_id");
        String nombreSerie = obtenerNombreDragonBall(masAntigua);
        System.out.println("  Serie: " + nombreSerie + " (ID: " + serieId + ")");

        JsonObject response = get("/series/" + serieId + "/episodes/default/spa?season=3");
        JsonObject data = response.getAsJsonObject("data");
        JsonArray episodes = data != null ? data.getAsJsonArray("episodes") : null;

        if (episodes != null && episodes.size() > 0) {
            JsonObject primerEpisodio = encontrarPrimerEpisodio(episodes);

            System.out.println("  Temporada: 3, Episodio: " + getInt(primerEpisodio, "number"));
            System.out.println("  Fecha: " + getString(primerEpisodio, "aired"));
            System.out.println("  Nombre: " + valorODefault(getString(primerEpisodio, "name")));
            System.out.println("  Descripción: " + valorODefault(getString(primerEpisodio, "overview")));
        } else {
            System.out.println("  No se encontraron episodios para la temporada 3.");
        }
        System.out.println();
    }

    /**
     * EJERCICIO 4: Actor que interpreta a Goku en Dragonball Evolution (2009).
     * @endpoint GET /search?query=Dragonball+Evolution&type=movie
     * @endpoint GET /movies/{id}/extended
     * @endpoint GET /people/{id}/extended
     */
    private static void mostrarEjercicio4() throws Exception {
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 4. ACTOR DE GOKU EN DRAGONBALL EVOLUTION (2009)                │");
        System.out.println("└────────────────────────────────────────────────────────────────┘");

        JsonObject searchResult = get("/search?query=" + encode("Dragonball Evolution") + "&type=movie");
        JsonArray peliculas = searchResult.getAsJsonArray("data");

        String movieId = null;
        for (JsonElement e : peliculas) {
            JsonObject p = e.getAsJsonObject();
            String nombre = getString(p, "name");
            String year = getString(p, "year");
            if (nombre != null && nombre.toLowerCase().contains("dragonball evolution") && "2009".equals(year)) {
                movieId = getString(p, "tvdb_id");
                break;
            }
        }

        String actorName = null;
        int participaciones = 0;

        if (movieId != null) {
            JsonObject movieDetail = get("/movies/" + movieId + "/extended");
            JsonObject movieData = movieDetail.getAsJsonObject("data");
            JsonArray characters = movieData != null ? movieData.getAsJsonArray("characters") : null;

            Integer peopleId = null;
            if (characters != null) {
                for (JsonElement e : characters) {
                    JsonObject c = e.getAsJsonObject();
                    String charName = getString(c, "name");
                    if (charName != null && charName.toLowerCase().contains("goku")) {
                        actorName = getString(c, "personName");
                        peopleId = getInt(c, "peopleId");
                        break;
                    }
                }
            }

            if (peopleId != null) {
                JsonObject personDetail = get("/people/" + peopleId + "/extended");
                JsonObject personData = personDetail.getAsJsonObject("data");
                JsonArray personCharacters = personData != null ? personData.getAsJsonArray("characters") : null;
                participaciones = personCharacters != null ? personCharacters.size() : 0;
            }
        }

        System.out.println("  Actor: " + valorODefault(actorName));
        System.out.println("  Participaciones totales: " + participaciones);
        System.out.println();
    }

    /**
     * EJERCICIO 5: Mejor película de USA del mismo año que "La Guerra de los Mundos".
     * @endpoint GET /search?query=War+of+the+Worlds&type=movie
     * @endpoint GET /movies/filter?country=usa&year={year}&sort=score&sortType=desc
     */
    private static void mostrarEjercicio5() throws Exception {
        System.out.println("┌────────────────────────────────────────────────────────────────┐");
        System.out.println("│ 5. MEJOR PELÍCULA USA DEL AÑO DE \"LA GUERRA DE LOS MUNDOS\"     │");
        System.out.println("└────────────────────────────────────────────────────────────────┘");

        JsonObject warSearch = get("/search?query=" + encode("War of the Worlds") + "&type=movie");
        JsonArray warResults = warSearch.getAsJsonArray("data");

        Integer warYear = null;
        for (JsonElement e : warResults) {
            JsonObject m = e.getAsJsonObject();
            String nombre = getString(m, "name");
            if (nombre != null && nombre.equalsIgnoreCase("War of the Worlds")) {
                warYear = getInt(m, "year");
                break;
            }
        }

        if (warYear != null) {
            JsonObject filterResult = get("/movies/filter?country=usa&year=" + warYear + "&sort=score&sortType=desc");
            JsonArray movies = filterResult.getAsJsonArray("data");

            if (movies != null && movies.size() > 0) {
                JsonObject mejor = movies.get(0).getAsJsonObject();
                System.out.println("  Año: " + warYear);
                System.out.println("  Mejor película: " + getString(mejor, "name"));
                System.out.println("  Score: " + getString(mejor, "score"));
            }
        } else {
            System.out.println("  No se encontró 'War of the Worlds'.");
        }
        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // MÉTODOS DE BÚSQUEDA
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * Busca todas las series que contienen "Dragon Ball" en el título.
     * Busca en el nombre principal, traducciones y aliases. //usa las aliases porque las principales a veces estan en el idioma original (japonés)
     * @endpoint GET /search?query=dragon+ball&offset={offset}
     * @return Lista de series encontradas
     */
    private static List<JsonObject> buscarSeriesDragonBall() throws Exception {
        List<JsonObject> resultado = new ArrayList<>();
        int offset = 0;
        final int PAGE_SIZE = 50;

        while (true) {
            JsonObject response = get("/search?query=" + encode("dragon ball") + "&offset=" + offset);
            JsonArray data = response.getAsJsonArray("data");

            if (data == null || data.size() == 0) break;

            for (JsonElement e : data) {
                JsonObject item = e.getAsJsonObject();
                if (!"series".equals(getString(item, "type"))) continue;

                if (contieneDragonBall(item)) {
                    resultado.add(item);
                }
            }

            JsonObject links = response.getAsJsonObject("links");
            if (links == null || links.get("next").isJsonNull()) break;

            Integer totalItems = getInt(links, "total_items");
            if (totalItems == null || offset + PAGE_SIZE >= totalItems) break;

            offset += PAGE_SIZE;
        }

        return resultado;
    }

    /**
     * Verifica si un objeto contiene "Dragon Ball" en nombre, traducciones o aliases.
     */
    private static boolean contieneDragonBall(JsonObject item) {
        // Verificar nombre principal
        if (contieneTexto(getString(item, "name"), "dragon", "ball")) {
            return true;
        }

        // Verificar traducciones
        if (item.has("translations") && !item.get("translations").isJsonNull()) {
            JsonObject translations = item.getAsJsonObject("translations");
            for (String lang : translations.keySet()) {
                if (contieneTexto(getString(translations, lang), "dragon", "ball")) {
                    return true;
                }
            }
        }

        // Verificar aliases
        if (item.has("aliases") && !item.get("aliases").isJsonNull()) {
            JsonArray aliases = item.getAsJsonArray("aliases");
            for (JsonElement alias : aliases) {
                if (contieneTexto(alias.getAsString(), "dragon", "ball")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Obtiene el nombre que contiene "Dragon Ball" de un objeto (nombre, traducción o alias).
     */
    private static String obtenerNombreDragonBall(JsonObject item) {
        String nombre = getString(item, "name");
        if (contieneTexto(nombre, "dragon", "ball")) {
            return nombre;
        }

        if (item.has("translations") && !item.get("translations").isJsonNull()) {
            JsonObject translations = item.getAsJsonObject("translations");
            for (String lang : translations.keySet()) {
                String traduccion = getString(translations, lang);
                if (contieneTexto(traduccion, "dragon", "ball")) {
                    return traduccion;
                }
            }
        }

        return nombre;
    }

    /**
     * Encuentra la serie más antigua por año.
     */
    private static JsonObject encontrarSerieMasAntigua(List<JsonObject> series) {
        JsonObject masAntigua = null;
        int minYear = Integer.MAX_VALUE;

        for (JsonObject s : series) {
            Integer year = getInt(s, "year");
            if (year != null && year < minYear) {
                minYear = year;
                masAntigua = s;
            }
        }
        return masAntigua;
    }

    /**
     * Encuentra el primer episodio de una lista por número de episodio.
     */
    private static JsonObject encontrarPrimerEpisodio(JsonArray episodes) {
        JsonObject primero = null;
        int minNum = Integer.MAX_VALUE;

        for (JsonElement e : episodes) {
            JsonObject ep = e.getAsJsonObject();
            Integer num = getInt(ep, "number");
            if (num == null) num = getInt(ep, "episodeNumber");
            if (num != null && num < minNum) {
                minNum = num;
                primero = ep;
            }
        }
        return primero != null ? primero : episodes.get(0).getAsJsonObject();
    }

    // ═══════════════════════════════════════════════════════════════════════════════
    // UTILIDADES
    // ═══════════════════════════════════════════════════════════════════════════════

    /**
     * Realiza una petición GET a la API.
     * @endpoint GET {endpoint}
     */
    private static JsonObject get(String endpoint) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP " + response.statusCode() + ": " + response.body());
        }

        return JsonParser.parseString(response.body()).getAsJsonObject();
    }

    private static boolean contieneTexto(String texto, String... palabras) {
        if (texto == null) return false;
        String lower = texto.toLowerCase();
        for (String palabra : palabras) {
            if (!lower.contains(palabra.toLowerCase())) return false;
        }
        return true;
    }

    private static String getString(JsonObject obj, String key) {
        if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return null;
        JsonElement el = obj.get(key);
        return el.isJsonPrimitive() ? el.getAsString() : null;
    }

    private static Integer getInt(JsonObject obj, String key) {
        if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) return null;
        JsonElement el = obj.get(key);
        if (el.isJsonPrimitive()) {
            try {
                return el.getAsInt();
            } catch (NumberFormatException e) {
                try {
                    return Integer.parseInt(el.getAsString());
                } catch (Exception ignored) {}
            }
        }
        return null;
    }

    private static String encode(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private static String valorODefault(String valor) {
        return valor != null ? valor : "No disponible";
    }
}
