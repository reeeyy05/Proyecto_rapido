import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestorArchivos {
    private  static String carpetaSeleccionada = "";
    private static String archivoSeleccionado = "";
    private static Map<String, String> datos = new HashMap<>();

    public static void mostrarContenidoCarpeta () {
        File carpeta = new File(carpetaSeleccionada);
        String [] archivos = carpeta.list(); // devuelve un array con el nombre de los archivos

        if (archivos != null) {
            System.out.println("Mostrando el contenido de la carpeta");
            for(String archivo : archivos) {
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
        } else System.out.println("La ruta ingresada no es valida");        
    }
}
