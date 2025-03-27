
/**
 * @author Alejandro Rey Tostado y Alberto Garcia Izquierdo
 */

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

public class ArchivoJSON {
    private static  List<Map<String, String>> datos = new ArrayList<>();
    
    // Método para leer un archivo JSON
    public static List<Map<String, String>> leerJSON(File archivo) {

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            Map<String, String> mapa = null;
            String clave = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim().replace(",", ""); //Elimina las comas
                if (linea.equals("{")) {
                    mapa = new HashMap<>();
                } else if (linea.equals("}")) {
                    if (mapa != null) {
                        datos.add(mapa);
                    }
                } else if (mapa != null && linea.contains(":")) {
                    String[] partes = linea.replace("\"", "").split(":");
                    if (partes.length == 2) {
                        mapa.put(partes[0].trim(), partes[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
        return datos;
    }

    // Método para escribir un archivo JSON
    public static void escribirJSON(File archivo, List<Map<String, String>> datos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write("[\n");
            for (int i = 0; i < datos.size(); i++) {
                bw.write("  {\n");
                Map<String, String> mapa = datos.get(i);
                int j = 0;
                for (Map.Entry<String, String> entry : mapa.entrySet()) {
                    bw.write("    \"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                    if (++j < mapa.size()) {
                        bw.write(",");
                    }
                    bw.write("\n");
                }
                bw.write("  }");
                if (i < datos.size() - 1) {
                    bw.write(",");
                }
                bw.write("\n");
            }
            bw.write("]");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }
}
