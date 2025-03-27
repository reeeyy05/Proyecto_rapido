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

public class ArchivoXML {

    private List<Map<String, String>> datos;

    public ArchivoXML() {
        this.datos = new ArrayList<>();
    }

    public static List<Map<String, String>> LeerXML(File archivo) {
        List<Map<String, String>> lista = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            Map<String, String> mapa = new HashMap<>();
            String clave = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("<coche>")) {
                    mapa = new HashMap<>();
                } else if (linea.startsWith("</coche>")) {
                    if (mapa != null) {
                        lista.add(mapa);
                    }
                } else if (mapa != null && linea.startsWith("<") && linea.endsWith(">")) {
                    clave = linea.substring(1, linea.indexOf(">"));
                } else if (clave != null) {
                    mapa.put(clave, linea);
                    clave = null;
                }
            }
        } catch (IOException e) {
            System.out.println("Eror al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    public static void escribirXML(File archivo, List<Map<String, String>> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write("<coches>\n");
            for (Map<String, String> mapa : lista) {
                bw.write("  <coche>\n");
                for (Map.Entry<String, String> entry : mapa.entrySet()) {
                    bw.write("    <" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">\n");
                }
                bw.write("  </coche>\n");
            }
            bw.write("</coches>");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
