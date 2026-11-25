package org.example.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CsvDataService implements DataService {

    private String archivo;

    // variable utilitaria para guardar el ultimo id
    // por defecto es -1 (no hay nada)
    private static Integer lastId = -1;

    private static Logger logger = Logger.getLogger(CsvDataService.class.getName());

    public CsvDataService(String csvFile) {
        archivo = csvFile;
    }

    @Override
    public List<Juego> findAll() {
        var salida = new ArrayList<Juego>();

        logger.info("Abriendo archivo");
        try( BufferedReader br = new BufferedReader(new FileReader( new File(archivo) ))){
            var contenido = br.lines();

            contenido.forEach(line -> {
                String[] lineArray = line.split(",");
                if(lineArray.length < 12) {
                    logger.severe("Linea mal formada");
                } else {
                    Juego juego = new Juego();
                    juego.setId(Integer.parseInt(lineArray[0]));
                    juego.setNombre(lineArray[1]);
                    juego.setAño(Integer.parseInt(lineArray[2]));
                    juego.setPlataforma(lineArray[3]);
                    juego.setDesarrollador(lineArray[5]);
                    juego.setValoracion(Integer.parseInt(lineArray[6]));
                    juego.setTipo(lineArray[7]);
                    juego.setDescripcion(lineArray[8]);
                    juego.setImageUrl(lineArray[9]);
                    juego.setVideoUrl(lineArray[10]);
                    juego.setOwnerId(Integer.parseInt(lineArray[11]));
                    juego.setStatus(lineArray[12]);
                    salida.add(juego);
                }
            });
            lastId = salida.size();
            logger.info("Actualizo tamaño: " + lastId);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return salida;
    }

    @Override
    public Optional<Juego> save(Juego juego) {

        logger.info("Abriendo el archivo para escribir");
        try(var bfw = new BufferedWriter(new FileWriter(new File(archivo),true))){

            // actualizo el nuevo id
            lastId++;
            juego.setId( lastId);
            logger.info("Actualizando id: "+lastId);

            String salida = new StringBuilder()
                    .append(juego.getId()).append(",")
                    .append(juego.getNombre()).append(",")
                    .append(juego.getAño()).append(",")
                    .append(juego.getPlataforma()).append(",")
                    .append("-").append(",")
                    .append(juego.getDesarrollador()).append(",")
                    .append(juego.getValoracion()).append(",")
                    .append(juego.getTipo()).append(",")
                    .append(juego.getDescripcion()).append(",")
                    .append(juego.getImageUrl()).append(",")
                    .append(juego.getVideoUrl()).append(",")
                    .append(juego.getOwnerId()).append(",")
                    .append(juego.getStatus()).toString();
            logger.info("Nuevo juego creado");
            bfw.write( salida );
            bfw.newLine();
            logger.info("Juego guardado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(juego);
    }
}
