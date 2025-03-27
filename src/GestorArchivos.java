
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GestorArchivos {

    private static String carpetaSeleccionada = "";
    private static String archivoSeleccionado = "";

    private static List<Map<String, String>> datos = new ArrayList<>();

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

    public void leerArchivo(Scanner sc) {
        if (carpetaSeleccionada == null) {
            System.out.println("Tienes que seleccionar una carpeta");
        }

        System.out.print("Introduzca el nombre del archivo a leer: ");
        String nombreArchivo = sc.nextLine();
        File archivo = new File(carpetaSeleccionada, nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe");
        }

        String extensionArchivo = obtenerExtension(archivo.getName());
        switch (extensionArchivo) {
            case "xml" ->
                ArchivoXML.LeerXML(archivo);
            case "csv" ->
                ArchivoCSV.leerCSV(archivo);
            case "json" ->
                ArchivoJSON.leerJSON(archivo);
            default ->
                System.out.println("Extension archivo no aceptada");
        }
    }

    public String obtenerExtension(String nombreArchivo) {
        // Buscamos la ultima posicion del indice
        int indice = nombreArchivo.lastIndexOf('.');
        //Verificamos que el indice es mayor a 0
        return (indice > 0) ? nombreArchivo.substring(indice + 1).toLowerCase() : "";
    }
}
