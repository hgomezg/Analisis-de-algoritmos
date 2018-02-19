package sort;

import java.util.Scanner;

public class Main{

  public static void main(String[] args){
    
      Scanner sc = new Scanner(System.in);
      System.out.println("archivo");
      String archivo1 = sc.next();
      System.out.println("tiempo de frame");
      int framerate1 = Integer.parseInt(sc.next());
      System.out.println("algoritmo");
      String algoritmo1 = sc.next();
      
      Sort a1 = new Sort(archivo1, framerate1, algoritmo1);
      /*
      if(args.length == 3){
      String archivo = args[0];
      int framerate = Integer.parseInt(args[1]);
      String algoritmo = args[2];
      Sort a = new Sort(archivo, framerate, algoritmo);
    }else{
      System.err.println("(-)\tFavor de ejecutar con los argumentos correspondientes desde la carpeta 'src'");
      System.err.println("\t\t Ejemplo: java sort.Main imagen1 60 bubble");
    }*/
  }

}
