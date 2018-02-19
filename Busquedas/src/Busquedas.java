import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author hugo
 */
public class Busquedas {
    
    private String leer(String direccion){
        
        try {
            
            FileReader fr = new FileReader(direccion);
            BufferedReader br = new BufferedReader(fr);
            
            String linea ="";
            String aux ="";
            
            while(true){
                aux=br.readLine();
                if(aux!=null)
                    linea=linea+aux+" ";
                else break;
            }
            return linea.trim();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo\nsaliendo...");
            System.exit(0);
        }
        return null;
    }
    
    public int[] pasarInt(String direccion){
        
        String[] arrS = leer(direccion).split(" ");
        
        int[] arr = new int[arrS.length];
        
        for(int i = 0; i<arr.length; i++){
            try {
                arr[i] = Integer.parseInt(arrS[i]);
            } catch (Exception e) {
                System.err.println("Error al copiar a arreglo de int\nsaliendo...");
                System.exit(0);
            }
        }
        return arr;
    }
    
    
    /**
     * Metodo de busqueda secuencial.
     * @param arr arreglo donde vamos a buscar el elemento
     * @param b elemento que vamos a buscar
     * @return indice donde se encuentra b en el arreglo
     */
    public int secuencias(int[] arr, int b){
        
        for(int i = 0; i<arr.length; i++){
            if(b == arr[i]){
                return i+1;
            }
        }
        return -1;
    }
    /**
     * Metodo de busqueda binaria.
     * @param arr arreglo donde vamos a buscar el elemento
     * @param b elemento que vamos a buscar
     * @return indice donde se encuentra b en el arreglo
     */
    public int binaria(int[] arr, int b){
        
        int n = arr.length;
        int centro, inf= 0, sup = n-1;
        
        while(inf<=sup){
            centro = (sup+inf)/2;
            if(arr[centro]==b){
                return centro+1;
            }else if(b < arr[centro]){
                sup = centro-1;
            }else{
                inf = centro+1;
            }
        }
        return -1;
    }
    
    /**
     * Metodo auxiliar para la busqueda exponencial. metodo busqueda binaria
     * recursiva
     * @param arr arreglo donde buscar
     * @param a el indice menor donde inicia la busqueda
     * @param b el indice mayor donde termina la busqueda
     * @param x el elemento que se busca
     * @return el indice donde se encuenta el elemento
     */
    private int binariaRecursiva(int[] arr, int a, int b, int x){
        
        int mid;
        if(a<b){
            mid = (a+b)/2;
            if(x==arr[mid]){
                return mid+1;
            }else if(x<arr[mid]){
                return binariaRecursiva(arr, a, mid-1, x);
            }else{
                return binariaRecursiva(arr, mid+1, b, x);
            }
        }
        return -1;
    }
    
    /**
     * Metodo de busqueda exponencial.
     * @param arr arreglo donde se buscara el elemento
     * @param b elemento a buscar
     * @return el indice donde se encuentra el elemento
     */
    public int exponencial(int[] arr, int b){
        
        if(arr[0]==b){
            return 1;
        }
        
        int i = 1;
        while(i<arr.length && arr[i]<=b){
            i = i*2;
        }
        //return binariaRecursiva(arr, i/2, Math.min(i, arr.length), b);
        return Arrays.binarySearch(arr, i/2, Math.min(i, arr.length), b)+1;
    }
    
    /**
     * Metodo de busqueda de interpolacion
     * @param arr arrglo donde se buscara el elemento
     * @param b elemento a buscar
     * @return el indice donde se encuentra el elemento
     */
    public int interpolacion(int[] arr, int b){
        
        int min = 0; 
        int max = arr.length-1;
        
        while(min<=max && arr[min]<=b && b<=arr[max]){
            
            int p = min + ( ((max-min)/(arr[max]-arr[min])) *(b-arr[min]) );
            
            if(arr[p]==b){
                return p+1;
            }
            
            if(arr[p]<b){
                min = p+1;
            }else{
                max=p-1;
            }
        }
        return -1;
    }
    
    
    public static void main(String[] args) {
        
        Busquedas b = new Busquedas();
        
        System.out.println();
        
        int[] arr = b.pasarInt(args[0]);
        
        int x = Integer.parseInt(args[1]);
        
        String algoritmo = args[2];
        
        switch(algoritmo){
            
            case "secuencial":
                
                int salida = b.secuencias(arr, x);
                if(salida != -1){
                    System.out.println("El indice del elemento buscado es: "+salida);
                }else{
                    System.out.println("No existe el elemento :(");
                }
                
                break;
            
            case "binario":
                
                int salida1 = b.binaria(arr, x);
                if(salida1 != -1){
                    System.out.println("El indice del elemento buscado es: "+salida1);
                }else{
                    System.out.println("No existe el elemento :(");
                }
                
                break;
                
            case "exponencial":
                
                if(b.exponencial(arr, x) > 0){
                    System.out.println("El indice del elemento buscado es: "+b.exponencial(arr, x));
                }else{
                    System.out.println("No existe el elemento :(");
                }
                
                break;
                
            case "interpolacion":
                
                int salida3 = b.interpolacion(arr, x);
                if(salida3 != -1){
                    System.out.println("El indice del elemento buscado es: "+salida3);
                }else{
                    System.out.println("No existe el elemento :(");
                }
                
                break;
            
            default:
                
                System.out.println("El metodo de busqueda no es el correcto :(");
                break;
            
        }
        
    }
    
}
