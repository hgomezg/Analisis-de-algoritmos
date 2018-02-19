package algorithm;

import java.util.Scanner;
import processing.core.PApplet;

/**
 * Interfaz Grafica
 *
 * @author Kike
 */
public class Adoquinamiento extends PApplet {

    // Propiedades de la cuadricula
    int alto = 0;         // Altura (en celdas) de la cuadricula.
    int ancho = 0;        // Ancho (en celdas) de la cuadricula.
    int celda = 35;       // Tamaño de cada celda cuadrada (en pixeles).
    Cuadricula modelo;    // El objeto que representa la cuadricula
    int k = 0;

    public Adoquinamiento() {
        Scanner sc = new Scanner(System.in);
        System.out.println("De tamaño k:");
        this.k = sc.nextInt();
        if(k <= 0){
            System.out.println("No puede ser de ese tamaño!!!\nSe hara de tamaño k=1");
            k=1;
            
        }
        
        int m = (int) Math.pow(2, k);
        alto = m;
        ancho = m;
    }

    @Override
    public void settings() {
        size(ancho * celda, alto * celda);
    }

    @Override
    public void setup() {

        frameRate(15); //Ajusta velocidad de iteraciones por segundo

        modelo = new Cuadricula(ancho, alto, celda);
        modelo.cambioColor();
    }

    /**
     * Pinta el matriz del modelo
     */
    @Override
    public void draw() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                fill(modelo.matriz[i][j].r, modelo.matriz[i][j].g, modelo.matriz[i][j].b);
                rect(j * modelo.tamanio, i * modelo.tamanio, modelo.tamanio, modelo.tamanio);

                stroke(255); //Paredes (Blancas)
                line(j * modelo.tamanio, i * modelo.tamanio, j * modelo.tamanio, ((i + 1) * modelo.tamanio));
                line(j * modelo.tamanio, i * modelo.tamanio, ((j + 1) * modelo.tamanio), i * modelo.tamanio);
                line((j * modelo.tamanio) + modelo.tamanio, i * modelo.tamanio, (j + 1) * modelo.tamanio, (((i + 1) * modelo.tamanio)));
                line(j * modelo.tamanio, (i * modelo.tamanio) + modelo.tamanio, ((j + 1) * modelo.tamanio), ((i + 1) * modelo.tamanio));
            }
        }
    }

    // --- Clase Celda ---
    /**
     * Representación de cada celda de la cuadrícula.
     */
    class Celda {

        int r, g, b;

        /**
         * Constructor de una celda con su color en RGB
         */
        Celda(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        void setColor(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    // --- Clase Cuadricula ---
    /**
     * Representa la cuadricula de adoquines
     */
    class Cuadricula {

        int ancho, alto;  // Tamaño de celdas a lo largo y ancho de la cuadrícula.
        int tamanio;  // Tamaño en pixeles de cada celda.
        Celda[][] matriz;  // Mundo de celdas (Cuadricula del laberinto)
        Scanner sc;

        /**
         * Constructor del modelo
         *
         * @param ancho Cantidad de celdas a lo ancho en la cuadricula.
         * @param alto Cantidad de celdas a lo alto de la cuadricula.
         * @param tamanio Tamaño (en pixeles) de cada celda cuadrada que compone
         * la cuadricula.
         */
        Cuadricula(int ancho, int alto, int tamanio) {
            sc = new Scanner(System.in);
            this.ancho = ancho;
            this.alto = alto;
            this.tamanio = tamanio;
            matriz = new Celda[alto][ancho];
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    matriz[i][j] = new Celda(0, 0, 0);
                }
            }
        }

        /**
         * Metodo de demostracion para cambiar el color de 4 celdas
         */
        void cambioColor() {

            int adoEspPar = (int) (Math.random() * alto);
            int adoEspAcos = (int) (Math.random() * alto);
            
            matriz[adoEspPar][adoEspAcos].setColor(250, 250, 250);
            recursivo1(matriz, adoEspAcos, adoEspPar);
            
        }

        private void recursivo1(Celda[][] matriz, int posX, int posY) {

            int cR = (int) (Math.random() * 253);
            int cG = (int) (Math.random() * 253);
            int cV = (int) (Math.random() * 253);

            if (matriz.length == 2) {

                if (posX == 0 && posY == 0) {
                    matriz[0][1].setColor(cR, cG, cV);
                    matriz[1][0].setColor(cR, cG, cV);
                    matriz[1][1].setColor(cR, cG, cV);
                } else if ((posX == 1) && (posY == 0)) {
                    matriz[0][0].setColor(cR, cG, cV);
                    matriz[1][0].setColor(cR, cG, cV);
                    matriz[1][1].setColor(cR, cG, cV);
                } else if (posX == 0 && posY == 1) {
                    matriz[0][0].setColor(cR, cG, cV);
                    matriz[0][1].setColor(cR, cG, cV);
                    matriz[1][1].setColor(cR, cG, cV);
                } else if (posX == 1 && posY == 1) {
                    matriz[0][0].setColor(cR, cG, cV);
                    matriz[1][0].setColor(cR, cG, cV);
                    matriz[0][1].setColor(cR, cG, cV);
                }

            } else {
                int region = 0;

                int k = matriz.length;

                if (posX < k/2 && posY < k / 2) {

                    matriz[(k / 2) - 1][(k / 2)].setColor(cR, cG, cV);
                    matriz[(k / 2)][(k / 2)].setColor(cR, cG, cV);
                    matriz[(k / 2)][(k / 2) - 1].setColor(cR, cG, cV);
                    region = 1;

                } else if ((posX >= k / 2) && (posY < k / 2)) {

                    matriz[(k / 2) - 1][(k / 2) - 1].setColor(cR, cG, cV);
                    matriz[(k / 2)][(k / 2) - 1].setColor(cR, cG, cV);
                    matriz[(k / 2)][(k / 2)].setColor(cR, cG, cV);
                    region = 2;

                } else if (posX < k / 2 && posY >= k / 2) {

                    matriz[(k / 2) - 1][(k / 2) - 1].setColor(cR, cG, cV);
                    matriz[(k / 2) - 1][(k / 2)].setColor(cR, cG, cV);
                    matriz[(k / 2)][(k / 2)].setColor(cR, cG, cV);
                    region = 3;

                } else if (posX >= k / 2 && posY >= k / 2) {

                    matriz[(k / 2) - 1][(k / 2) - 1].setColor(cR, cG, cV);
                    matriz[(k / 2) - 1][(k / 2)].setColor(cR, cG, cV);
                    matriz[(k / 2)][(k / 2) - 1].setColor(cR, cG, cV);
                    region = 4;
                }

                if (region == 1) {

                    Celda[][] r1 = subMatriz(matriz, 0, 0);
                    Celda[][] r2 = subMatriz(matriz, 0, k/2);
                    Celda[][] r3 = subMatriz(matriz, k/2, 0);
                    Celda[][] r4 = subMatriz(matriz, k / 2, k / 2);

                    
                    recursivo1(r1, posX, posY);
                    recursivo1(r2, 0, r2.length-1);
                    recursivo1(r3, r3.length-1, 0);
                    recursivo1(r4, 0, 0);

                } else if (region == 2) {

                    Celda[][] r1 = subMatriz(matriz, 0, 0);
                    Celda[][] r2 = subMatriz(matriz, 0, k/2);
                    Celda[][] r3 = subMatriz(matriz, k/2, 0);
                    Celda[][] r4 = subMatriz(matriz, k / 2, k / 2);
                    
                    posX = posX - r2.length;
                    
                    recursivo1(r1, r1.length-1, r1.length-1);
                    recursivo1(r2, posX, posY);
                    recursivo1(r3, r3.length-1, 0);
                    recursivo1(r4, 0, 0);

                } else if (region == 3) {
                    
                    Celda[][] r1 = subMatriz(matriz, 0, 0);
                    Celda[][] r2 = subMatriz(matriz, 0, k / 2);
                    Celda[][] r3 = subMatriz(matriz, k / 2, 0);
                    Celda[][] r4 = subMatriz(matriz, k / 2, k / 2);
                    
                    posY = posY - r2.length;
                    
                    recursivo1(r1, r1.length - 1, r1.length - 1);
                    recursivo1(r2, 0, r2.length - 1);
                    recursivo1(r3, posX, posY);
                    recursivo1(r4, 0, 0);

                } else if (region == 4) {
                    Celda[][] r1 = subMatriz(matriz, 0, 0);
                    Celda[][] r2 = subMatriz(matriz, 0, k / 2);
                    Celda[][] r3 = subMatriz(matriz, k / 2, 0);
                    Celda[][] r4 = subMatriz(matriz, k / 2, k / 2);

                    posX = posX - r2.length;
                    posY = posY - r2.length;
                    
                    recursivo1(r1, r1.length - 1, r1.length - 1);
                    recursivo1(r2, 0, r2.length-1);
                    recursivo1(r3, r3.length - 1, 0);
                    recursivo1(r4, posX, posY);
                    
                }
            }
        }
    }

    private Celda[][] subMatriz(Celda[][] matriz, int py, int px) {

        if(matriz.length>2) {
            Celda[][] reg = new Celda[matriz.length / 2][matriz.length / 2];

            for (int i = 0, k = py; i < reg.length; i++, k++) {
                for (int j = 0, l = px; j < reg.length; j++, l++) {

                    reg[i][j] = matriz[k][l];

                }
            }
            return reg;
        }

        return matriz;
    }

    static public void main(String args[]) {
        PApplet.main(new String[]{"algorithm.Adoquinamiento"});
    }
}
