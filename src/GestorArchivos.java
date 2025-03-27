/**
 * @author Alejandro Rey Tostado y Alberto Garcia Izquierdo
 */

import java.io.File;
import java.util.Scanner;

public class GestorArchivos {

    private static String carpetaSeleccionada = "";
    private static String archivoSeleccionado = "";
  //  private static List<Coches> coches = new HashMap<>();

    public static void mostrarContenidoCarpeta() {
        File carpeta = new File(carpetaSeleccionada);
        String[] archivos = carpeta.list(); // devuelve un array con el nombre de los archivos

        if (archivos != null) {
            System.out.println("Mostrando el contenido de la carpeta");
            for (String archivo : archivos) {
                System.out.println(" - " + archivo); // lista los archivos de la carpeta
            }
        }
    }

    public static void seleccionarCarpeta(Scanner te) {
        System.out.print("Introduzca la ruta de la carpeta: ");
        String ruta = te.nextLine();
        File carpeta = new File(ruta);

        if (carpeta.exists() && carpeta.isDirectory()) {
            carpetaSeleccionada = ruta;
            System.out.println("La carpeta ha sido seleccionada");
            mostrarContenidoCarpeta();
        } else {
            System.out.println("La ruta ingresada no es valida");
        }
    }

    public static void leerFichero(Scanner te) {
        // Verificamos si hemos seleccionado una carpeta
        if (carpetaSeleccionada == null) {
            System.out.println("Primero debe seleccionar una carpeta");
        }

        System.out.print("Introduzca el nombre del fichero");
        String nombreFichero = te.nextLine();
        File fichero = new File(carpetaSeleccionada, nombreFichero);

        if (!fichero.exists()) {
            System.out.println("El fichero no existe");
        }

       /* try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea == lector.readLine()) != null) {

            }

        } catch (IOException e) {
            System.out.println("Error leyendo o escribiendo el archivo: " + e.getMessage());
        }*/

    }

    /*public static void convertirArchivo(Scanner te) {
        if (archivoSeleccionado.isEmpty()) {
            System.out.println("Primero debe seleccionar un fichero para convertir");
            return;
        }

        System.out.print("Seleccione el formato de salida (csv, json, xml): ");
        String formato = te.nextLine().toLowerCase();
        
        if (!formato.equals("csv") && !formato.equals("json") && !formato.equals("xml")) {
            System.out.println("Formato no válido");
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
                        if (j < datos.get(i).size() - 1) bw.write(",");
                        bw.newLine();
                        j++;
                    }
                    bw.write("  }");
                    if (i < datos.size() - 1) bw.write(",");
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
    } */
}
    
