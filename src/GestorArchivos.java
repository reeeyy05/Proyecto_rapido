
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
}