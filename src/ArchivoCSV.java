
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArchivoCSV {

    private static List<Map<String, String>> datos;

    public ArchivoCSV() {
        this.datos = new ArrayList<>();
    }

    public static void leerCSV(File archivo) {
        try (BufferedReader buffer = new BufferedReader(new FileReader(archivo))) {
            String linea;
            String[] encabezado = buffer.readLine().split(","); // leer la primera linea como encabezado 

            while ((linea = buffer.readLine()) != null) {
                String[] valores = linea.split(",");
                Map<String, String> fila = new HashMap<>();
                for (int i = 0; i < encabezado.length; i++) {
                    fila.put(encabezado[i], valores[i]);
                }
                datos.add(fila);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public void escribirCSV(File rutaArchivo) {
        if (datos.isEmpty()) {
            System.out.println("No hay datos para escribir en el archivo");            
        }

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            Set<String> encabezado = datos.get(0).keySet(); // obtiene los encabezados de la primera fila
            buffer.write(String.join(",", encabezado) + "\n"); // escribir encabezados
            buffer.newLine();

            // Escribimos cada fila de datos en el archivo
            for (Map<String, String> fila : datos) {
                List<String> valores = new ArrayList<>();

                for (String enc : encabezado) {
                    valores.add(fila.get(enc));
                }
                buffer.write(String.join(",", valores) + "\n");
                buffer.newLine();
            }
            
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo CSV: " + e.getMessage());
        }
    }

}
