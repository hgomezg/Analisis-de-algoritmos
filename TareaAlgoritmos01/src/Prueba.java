
import java.util.LinkedList;

public class Prueba {
    
    
    

    public void ejercicio01(int a, int b) {

        /*   if (a == b) {
            System.out.println("son iguales");
            return;
        } else*/ if (a < 0 && b < 0) {

            a = a + b;
            b = a - b;
            a = a - b;
            System.out.println("a = " + a + " --- b = " + b);
        } else {

            a = a + b;
            b = (b - a) * -1;
            a = a - b;
            System.out.println("a = " + a + " --- b = " + b);
        }
    }

    public int ejercicio02A(int[] arr) {

        int tamArr = arr.length;

        int diferentes = 0;

        for (int i = 0; i < tamArr; i++) {

            int actual = arr[i];

            if (i == tamArr - 1) {
                return diferentes;
            } else {

                for (int j = i + 1; j < tamArr; j++) {

                    int temp = arr[j];

                    if (actual != temp) {
                        diferentes++;
                    }
                }
            }
        }
        return diferentes;
    }

    public void ejercicio02B(int[] arr) {

        int tamArr = arr.length;

        for (int i = 0; i < tamArr; i++) {

            int frecuencia = 0;
            boolean yaPaso = false;

            for (int j = 0; j < tamArr; j++) {

                if ((j < i) && (arr[i] == arr[j])) {
                    yaPaso = true;
                    break;
                }

                if (arr[i] == arr[j]) {
                    frecuencia++;
                }
            }

            if (!yaPaso) {
                System.out.println("la frecuencia de " + arr[i] + " es " + frecuencia);
            }
        }
    }

    public int ejercicio04(int[][] arr) {

        LinkedList listaFilas = new LinkedList();
        LinkedList listaColumnas = new LinkedList();
        int contador = 0;

        int i = 0;
        while (i < arr.length) {

            int j = 0;
            while (j < arr[i].length) {

                if (arr[i][j] == 1) {

                    boolean igual = false;
                    int m = 0;
                    while (m < listaFilas.size()) {

                        if (i == (Integer) listaFilas.get(m) && j == (Integer) listaColumnas.get(m)) {
                            igual = true;
                        }
                        //contador++;
                        m++;
                    }

                    if (!igual) {

                        int k = i;
                        while (k < arr.length) {

                            int l = j;
                            while (l < arr[i].length) {

                                if (arr[k][l] == 1) {
                                    System.out.println("k " + k + " l " + l);
                                    listaFilas.add(k);
                                    listaColumnas.add(l);
                                } else {

                                    break;
                                }

                                l++;
                            }

                            k++;
                        }

                    }//contador++;

                }

                j++;
            }

            i++;
        }

        return contador;
    }

    public int ejer04(int arr[][]) {

        int con = 0;
        
        
        boolean pasa = false;
        LinkedList filaI = new LinkedList();
        LinkedList filaT = new LinkedList();
        LinkedList colI = new LinkedList();
        LinkedList colT = new LinkedList();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                if (arr[i][j] == 1) {
                    for (int k = 0; k < filaI.size(); k++) {
                        //System.out.println(i+">="+filaI.get(k)+"|"+i+"<="+filaT.get(k)+"-----");
                        if (i >= (Integer)filaI.get(k) && i <= (Integer) filaT.get(k)) {
                            //System.out.println(i+">="+filaI.get(k)+"|"+i+"<="+filaT.get(k));
                            if (j >= (Integer)colI.get(k) && j<=(Integer)colT.get(k)) {
                                
                                
                                pasa = true;
                            }
                        }
                    }
                }

                if (!pasa) {
                    //System.out.println("iiii");
                    int conFT1=0;
                    int conFT = 0;
                    for (int k = i; k < arr.length; k++) {
                        
                        if (arr[k][j] == 1) {
                            conFT++;
                            conFT1 = conFT;
                            
                        }else{
                            break;
                        }
                        
                    }
                    filaI.add(i);
                    filaT.add(conFT1 + 1);
                    int conCT1 = 0;
                    int conCT = 0;
                    for (int l = j; l < arr[i].length; l++) {
                        
                        if (arr[i][l] == 1) {
                            conCT++;
                            conCT1 = conCT;
                        }else{
                            break;
                        }
                    }
                    colI.add(j);
                    colT.add(conCT1 + 1);
                    con++;
                    
                    
                }
                pasa = false;

            }
        }
       //System.out.println(filaI.get(0)+" "+filaT.get(0));
       //System.out.println(colI.get(0)+" "+colT.get(0));
       System.out.println(filaI.size());
        return con;
       }
    
    
    public int recursividad(int arr[], int indice, int frecuencia, int num){
        
        if(arr.length==indice){
            
            return frecuencia;
        
        }else if(num == arr[indice]){
            
            
            frecuencia++;
        }
            
            
        
            return recursividad(arr, indice+1, frecuencia, num);
    }
    
    public void leerVector(int pos, int[] vector){
        if (pos == vector.length-1) { System.out.println(vector[pos]);
        
        System.out.println(recursividad(vector, 0, 0, vector[pos]));
        
    }else {
            System.out.println(vector[pos]);
            System.out.println(recursividad(vector, 0, 0, vector[pos]));
            leerVector(pos+1, vector);
        }
    }
    

    public static void main(String[] args) {

        int arr[] = {1, 2, 1, 2, 1, 1};
            
        Prueba p = new Prueba();
        p.leerVector(0, arr);
        //System.out.println(p.recursividad(arr, 0, 0, 0, arr[2]));
        

    }

}
