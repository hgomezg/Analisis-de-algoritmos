
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hugo
 */
public class Grafica {

    String[] vertices;
    ArrayList<String[]> aristas;
    ArrayList<Vertice> listaVertices;
    ArrayList<Arista> listaArista;
    ArrayList<Vertice> listaCJ;
    Scanner sc;
    String ent;
    
    public Grafica() {
        
        sc = new Scanner(System.in);
        System.out.println("Nombre del archivo:");
        ent = sc.next();
        Lector lector = new Lector(ent);
        
        
        vertices = lector.vertices;
        aristas = lector.aristas;

        pasarVerticesLista();
        pasarAristaLista();
        conjuntoIndependiente();
        archivoIndMax();
    }

    private void pasarVerticesLista() {

        this.listaVertices = new ArrayList<>();
        for (int i = 0; i < vertices.length; i++) {
            listaVertices.add(new Vertice(vertices[i]));
        }
    }

    private void pasarAristaLista() {

        this.listaArista = new ArrayList<>();
        String s1 = "";
        String s2 = "";

        for (int i = 0; i < aristas.size(); i++) {

            for (int j = 0; j < this.listaVertices.size(); j++) {

                if (aristas.get(i)[0].equalsIgnoreCase(listaVertices.get(j).getId())) {
                    s1 = listaVertices.get(j).getId();
                }

                if (aristas.get(i)[1].equalsIgnoreCase(listaVertices.get(j).getId())) {
                    s2 = listaVertices.get(j).getId();
                }
            }

            this.listaArista.add(new Arista(new Vertice(s1), new Vertice(s2)));
        }

    }

    private ArrayList<Vertice> vecinos(String v1, ArrayList<Arista> lista){
        
        ArrayList<Vertice> listaR = new ArrayList<>();
        
        for(int i = 0; i<lista.size(); i++){
            
            if(lista.get(i).getV1().getId().equalsIgnoreCase(v1)){
                listaR.add(lista.get(i).getV2());
            }
            
            if(lista.get(i).getV2().getId().equalsIgnoreCase(v1)){
                listaR.add(lista.get(i).getV1());
            }
        }
        
        return listaR;
    }
    
    private void conjuntoIndependiente() {

        for(int i = 0; i<listaVertices.size(); i++){
            if(listaVertices.get(i).getValor() == -1){
                
                listaVertices.get(i).setValor(1);
                
                for(int j = 0; j<listaArista.size(); j++){
                    
                    ArrayList<Vertice> listaVecinos = vecinos(listaVertices.get(i).getId(), listaArista);
                    
                    for(int k = 0; k<listaVecinos.size(); k++){
                        
                        if(listaVecinos.get(k).getValor() == -1){
                            
                            for(int l = 0; l<listaVertices.size(); l++){
                                if(listaVertices.get(l).getId().equalsIgnoreCase(listaVecinos.get(k).getId())){
                                    listaVertices.get(l).setValor(0);
                                }
                            }
                            
                        }
                    }
                }
            }
        }
    }
    
    public void archivoIndMax(){
        
        ArrayList<Vertice> listaSal = new ArrayList<>();
        for(int i = 0; i<listaVertices.size(); i++){
            if(listaVertices.get(i).getValor()==1){
                listaSal.add(listaVertices.get(i));
            }
        }
        
        String sal = "";
        
        for(int i = 0; i<listaSal.size(); i++){
            
            if(i == listaSal.size()-1){
                
                sal = sal+listaSal.get(i).getId();
            }else{
                sal =sal + listaSal.get(i).getId()+",";
            }
            
        }
        
        escritura("Salida"+ent, sal);
    }
    
    
    private void escritura(String direccion, String txt){
    
        File f = new File(direccion);
       
        FileWriter fw;
        
        try {
            
            fw=new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write(txt);
            bw.close();
            pw.close();
            
            
        } catch (Exception e) {
            System.out.println("no se pudo crear el archivo");
        }
    }
    
    public static void main(String[] args) {
        Grafica g = new Grafica();
    }

}
