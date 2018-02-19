import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import sun.java2d.opengl.OGLRenderQueue;
/**
 * Lector de graficas desde archivos de texto
 * @author Kike
 */
public class Lector{
  
  // Atributos de Lector
  String[] vertices;
  ArrayList<String[]> aristas;
  
  /**
   * Constructor del lector
   * Recibe el nombre de un archivo de texto y almacena el arreglo de vertices y aristas en el objeto.
   * @param filename Nombre del archivo de texto a procesar
   */
  public Lector(String filename){
    aristas = new ArrayList<>();
    leerArchivo(filename);
  }
  /**
   * Metodo que dado un nombre de archivo almacena en el objeto los vertices y las aristas del archivo de texto
   * @param filename Nombre del archivo de texto a procesar
   */
  public void leerArchivo(String filename){
    try(BufferedReader br = new BufferedReader(new FileReader(filename))){
      String line;
      for(int i = -1; (line = br.readLine()) != null; i++){
        if(i == -1){
          vertices = line.trim().split("\\s*,\\s*");
        }else{
          String[] lineaAristas = line.trim().split("\\s*,\\s*");
          aristas.add(lineaAristas);
        }
      }
    }catch(Exception e){
        System.out.println("no existe el archivo o el directorio");
        System.exit(0);
    }
  }
  
  
  /**
   * Ejemplo de uso de la clase Lector
   */
  public static void main(String[] args){
    Lector lector = new Lector(".csv");
    String[] vertices = lector.vertices;
    ArrayList<String[]> aristas = lector.aristas;    
    
    for(int i = 0; i < vertices.length; i++)
      System.out.print("'"+vertices[i]+"' ");
    System.out.println();
    for(int i = 0; i < aristas.size(); i++){
      System.out.print("'"+aristas.get(i)[0]+"'-");
      System.out.println("'"+aristas.get(i)[1]+"'");
    }
  }
  
}