package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvDataService implements DataService {

    private String archivo;

    public CsvDataService(String csvFile) {
        archivo = csvFile;
    }

    @Override
    public List<Pelicula> findAll() {
        var salida = new ArrayList<Pelicula>();
        try{ BufferedReader br = new BufferedReader(new FileReader(archivo));
            var contenido = br.lines();
            contenido.forEach(line -> {
                String[] lineArray = line.split(",");
                if(lineArray.length < 5) {
                    System.err.println("Linea mal formada");
                } else {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setId(Integer.parseInt(lineArray[0]));
                    System.out.println(lineArray[0]);
                    pelicula.setTitulo(lineArray[1]);
                    System.out.println(lineArray[1]);
                    pelicula.setAño(Integer.parseInt(lineArray[2]));
                    System.out.println(lineArray[2]);
                    pelicula.setDirector(lineArray[3]);
                    System.out.println(lineArray[3]);
                    pelicula.setGenero(lineArray[4]);
                    System.out.println(lineArray[4]);
                    salida.add(pelicula);
                }
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    @Override
    public Optional<Pelicula> save(Pelicula pelicula, String destino) {
        try{ var bfw = new BufferedWriter(new FileWriter(new File(destino),true));

            Path destinoruta = Path.of(destino);
            Path parent = destinoruta.getParent();
            if (parent != null) Files.createDirectories(parent);

            String salida = new StringBuilder()
                    .append(pelicula.getId()).append(",")
                    .append(pelicula.getTitulo()).append(",")
                    .append(pelicula.getDirector()).append(",")
                    .append(pelicula.getAño()).append(",")
                    .append(pelicula.getGenero()).toString();
            bfw.write(salida);
            bfw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.of(pelicula);
    }
}
