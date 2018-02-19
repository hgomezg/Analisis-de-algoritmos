
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
//import sun.java2d.opengl.OGLRenderQueue;

/**
 * Lector de graficas desde archivos de texto
 *
 * @author Kike
 */
public class Lector {

    // Atributos de Lector
    Vertice[] arrVertices;
    String[] vertices;
    ArrayList<Arista> listaArista;
    ArrayList<String[]> aristas;

    /**
     * Constructor del lector Recibe el nombre de un archivo de texto y almacena
     * el arreglo de vertices y aristas en el objeto.
     *
     * @param filename Nombre del archivo de texto a procesar
     */
    public Lector(String filename) {

        String[] sep = filename.split("\\.");

        if (sep[1].equals("graph")) {

            listaArista = new ArrayList<>();
            aristas = new ArrayList<>();
            leerArchivo(filename);
        }else{
            System.out.println("La extencion del archivo es incorrecta :(");
            System.exit(0);
        }
    }

    /**
     * Metodo que dado un nombre de archivo almacena en el objeto los vertices y
     * las aristas del archivo de texto
     *
     * @param filename Nombre del archivo de texto a procesar
     */
    public void leerArchivo(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            for (int i = -1; (line = br.readLine()) != null; i++) {
                if (i == -1) {
                    vertices = line.trim().split("\\s*,\\s*");

                } else {

                    String[] lineaAristas = line.trim().split("\\s*,\\s*");
                    listaArista.add(new Arista(new Vertice(lineaAristas[0]), new Vertice(lineaAristas[1])));
                    //aristas.add(lineaAristas);
                }
            }
        } catch (Exception e) {
            System.out.println("no existe el archivo o el directorio  " + e);
            System.exit(0);
        }

        arrVertices = new Vertice[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            arrVertices[i] = new Vertice(vertices[i]);

        }
    }
}
