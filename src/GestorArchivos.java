
/**
 * @author Alejandro Rey Tostado y Alberto Garcia Izquierdo
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GestorArchivos {

    public String carpetaSeleccionada = "";
    public String archivoSeleccionado = "";

    private List<Map<String, String>> datos = new ArrayList<>();

    public void mostrarContenidoCarpeta() {
        if (carpetaSeleccionada == null || carpetaSeleccionada.isEmpty()) {
            System.out.println("No se ha seleccionado ninguna carpeta.");
            return;
        }

        File carpeta = new File(carpetaSeleccionada);
        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("La carpeta seleccionada no es válida.");
            return;
        }

        String[] archivos = carpeta.list();
        if (archivos != null && archivos.length > 0) {
            for (String archivo : archivos) {
                System.out.println(" - " + archivo);
            }
        } else {
            System.out.println("La carpeta está vacía.");
        }
    }

    public void seleccionarCarpeta(Scanner te) {
        System.out.print("Introduzca la ruta de la carpeta: ");
        String ruta = te.nextLine();
        File carpeta = new File(ruta);

        if (carpeta.exists() && carpeta.isDirectory()) {
            carpetaSeleccionada = ruta;
            System.out.println("La carpeta ha sido seleccionada: " + carpetaSeleccionada);
        } else {
            System.out.println("La ruta ingresada no es válida.");
        }
    }

    public void leerFichero(Scanner sc) {
        if (carpetaSeleccionada == null || carpetaSeleccionada.isEmpty()) {
            System.out.println("Primero debe seleccionar una carpeta.");
            return;
        }

        System.out.print("Introduzca el nombre del archivo a leer: ");
        String nombreArchivo = sc.nextLine();
        File archivo = new File(carpetaSeleccionada, nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        String extensionArchivo = obtenerExtension(archivo.getName());
        switch (extensionArchivo) {
            case "xml" -> ArchivoXML.LeerXML(archivo);
            case "csv" -> ArchivoCSV.leerCSV(archivo);
            case "json" -> ArchivoJSON.leerJSON(archivo);
            default -> System.out.println("Extensión de archivo no aceptada.");
        }
    }

    public String obtenerExtension(String nombreArchivo) {
        // Buscamos la ultima posicion del indice
        int indice = nombreArchivo.lastIndexOf('.');
        // Verificamos que el indice es mayor a 0
        return (indice > 0) ? nombreArchivo.substring(indice + 1).toLowerCase() : "";
    }

    public void convertirArchivo(Scanner te) {
        if (carpetaSeleccionada == null || carpetaSeleccionada.isEmpty()) {
            System.out.println("Primero debe seleccionar una carpeta.");
            return;
        }
    
        System.out.print("Introduzca el nombre del archivo que desea convertir: ");
        String nombreArchivo = te.nextLine();
        File archivo = new File(carpetaSeleccionada, nombreArchivo);
    
        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }
    
        String extensionArchivo = obtenerExtension(archivo.getName());
        if (!extensionArchivo.equals("csv") && !extensionArchivo.equals("json") && !extensionArchivo.equals("xml")) {
            System.out.println("El archivo no tiene una extensión válida para convertir.");
            return;
        }
    
        System.out.print("Seleccione el formato de salida (csv, json, xml): ");
        String formato = te.nextLine().toLowerCase();
    
        if (!formato.equals("csv") && !formato.equals("json") && !formato.equals("xml")) {
            System.out.println("Formato no válido.");
            return;
        }
    
        System.out.print("Introduzca el nombre del archivo de salida (sin extensión): ");
        String nombreSalida = te.nextLine();
        File archivoSalida = new File(carpetaSeleccionada, nombreSalida + "." + formato);
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            if (formato.equals("csv")) {
                for (Map<String, String> fila : datos) {
                    bw.write(String.join(",", fila.values()));
                    bw.newLine();
                }
            } else if (formato.equals("json")) {
                bw.write("[\n");
                for (int i = 0; i < datos.size(); i++) {
                    bw.write("  {\n");
                    int j = 0;
                    for (Map.Entry<String, String> entry : datos.get(i).entrySet()) {
                        bw.write("    \"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                        if (j < datos.get(i).size() - 1)
                            bw.write(",");
                        bw.newLine();
                        j++;
                    }
                    bw.write("  }");
                    if (i < datos.size() - 1)
                        bw.write(",");
                    bw.newLine();
                }
                bw.write("]");
            } else if (formato.equals("xml")) {
                bw.write("<datos>\n");
                for (Map<String, String> fila : datos) {
                    bw.write("  <registro>\n");
                    for (Map.Entry<String, String> entry : fila.entrySet()) {
                        bw.write("    <" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">\n");
                    }
                    bw.write("  </registro>\n");
                }
                bw.write("</datos>");
            }
            System.out.println("Archivo convertido con éxito: " + archivoSalida.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

}
