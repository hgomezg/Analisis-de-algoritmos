
import java.util.ArrayList;

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
    
    
    public Grafica() {

        Lector lector = new Lector("Completa.csv");
        vertices = lector.vertices;
        aristas = lector.aristas;
        
        pasarVerticesLista();
        pasarAristaLista();
        conjuntoIndependiente();
    }
    
    private void pasarVerticesLista(){
        
        this.listaVertices = new ArrayList<>();
        for(int i = 0; i< vertices.length; i++){
            listaVertices.add(new Vertice(vertices[i]));
        }
    }
    
    private void pasarAristaLista(){
        
        this.listaArista = new ArrayList<>();
        String s1 = "";
        String s2 = "";
        
        for(int i = 0; i<aristas.size(); i++){
            
            for(int j = 0; j<this.listaVertices.size(); j++){
                
                if(aristas.get(i)[0].equalsIgnoreCase(listaVertices.get(j).getId())){
                    s1 = listaVertices.get(j).getId();
                }
                
                if(aristas.get(i)[1].equalsIgnoreCase(listaVertices.get(j).getId())){
                    s2 = listaVertices.get(j).getId();
                }
            }
            
            this.listaArista.add(new Arista(new Vertice(s1), new Vertice(s2)));
        }
        
    }
    
    private void conjuntoIndependiente(){
        
        listaCJ = new ArrayList<>();
        
        ArrayList<Vertice> listaVerticesC = new ArrayList<>(this.listaVertices);
        
        listaVerticesC.get(0).setValor(1);
        
        while(listaVerticesC.size() != 0){
            
            //busca primer vertice de listaVerticesC diferente a -1
            Vertice v1 = new Vertice();
            for(int i = 0; i<listaVerticesC.size(); i++){
                
                if(listaVerticesC.get(i).getValor()!=-1){
                    
                    v1.setId(listaVerticesC.get(i).getId());
                    v1.setValor(listaVerticesC.get(i).getValor());
                    break;
                }
            }
            
            //pone el valor de v1 a toda la listaArista que coincida con el id
            for(int i = 0; i<listaArista.size(); i++){
                
                if(v1.getId().equalsIgnoreCase(listaArista.get(i).getV1().getId())){
                    listaArista.get(i).getV1().setValor(v1.getValor());
                }
                
                if(v1.getId().equalsIgnoreCase(listaArista.get(i).getV2().getId())){
                    listaArista.get(i).getV2().setValor(v1.getValor());
                }
            }
            
            //prueba
            //for(int i = 0; i<listaVerticesC.size(); i++){
              //  System.out.println(listaVerticesC.get(i).getId()+"\t"+listaVerticesC.get(i).getValor());
            //}
            
            for(int i = 0; i<listaArista.size(); i++){
                System.out.println(listaArista.get(i).getV1().getId()+" "+
                        listaArista.get(i).getV1().getValor()+" : "+listaArista.get(i).getV2().getId()+" "+
                        listaArista.get(i).getV2().getValor());
            }
            System.out.println();
            //fin prueba
            
            //da valores a listaArista a todos 
            for(int i = 0; i<listaArista.size(); i++){
                
                if(v1.getId().equalsIgnoreCase(listaArista.get(i).getV1().getId())){
                    
                    if(listaArista.get(i).getV1().getValor() == 1){
                        
                        String idV2 = listaArista.get(i).getV2().getId();
                        
                        for(int j = 0 ; j<listaArista.size(); j++){
                            
                            if(listaArista.get(j).getV2().getId().equalsIgnoreCase(idV2)){
                                
                                listaArista.get(j).getV2().setValor(0);
                            }
                            if(listaArista.get(j).getV1().getId().equalsIgnoreCase(idV2)){
                                
                                listaArista.get(j).getV1().setValor(0);
                            }
                        }
                    }
                    
                    
                    if(listaArista.get(i).getV2().getValor() == 1){
                        
                        String idV1 = listaArista.get(i).getV1().getId();
                        
                        for(int j = 0 ; j<listaArista.size(); j++){
                            
                            if(listaArista.get(j).getV1().getId().equalsIgnoreCase(idV1)){
                                
                                listaArista.get(j).getV1().setValor(0);
                            }
                            
                            if(listaArista.get(j).getV2().getId().equalsIgnoreCase(idV1)){
                                
                                listaArista.get(j).getV2().setValor(0);
                            }
                        }
                    }
                    
                    
                    if(listaArista.get(i).getV1().getValor() == 0){
                        
                        String idV2 = listaArista.get(i).getV2().getId();
                        
                        for(int j = 0 ; j<listaArista.size(); j++){
                            
                            if(listaArista.get(j).getV2().getId().equalsIgnoreCase(idV2)){
                                
                                listaArista.get(j).getV2().setValor(1);
                            }
                            
                            if(listaArista.get(j).getV1().getId().equalsIgnoreCase(idV2)){
                                    
                                    listaArista.get(j).getV1().setValor(1);
                            }
                        }
                    }
                    
                    if(listaArista.get(i).getV2().getValor() == 0){
                        
                        String idV1 = listaArista.get(i).getV1().getId();
                        
                        for(int j = 0 ; j<listaArista.size(); j++){
                            
                            if(listaArista.get(j).getV1().getId().equalsIgnoreCase(idV1)){
                                listaArista.get(j).getV1().setValor(1);
                            }
                            
                            if(listaArista.get(j).getV2().getId().equalsIgnoreCase(idV1)){
                                
                                listaArista.get(j).getV2().setValor(1);
                            }
                        }
                    }
                }
            }
            
            
            //copia a listaVerticesC los valores de listaArista
            for(int i = 0; i<listaArista.size(); i++){
                
                for(int j = 0; j<listaVerticesC.size(); j++){
                    
                    if(listaArista.get(i).getV1().getId().equalsIgnoreCase
                                                            (listaVerticesC.get(j).getId() )){
                        
                        listaVerticesC.get(j).setValor(listaArista.get(i).getV1().getValor());
                    }
                    
                    if(listaArista.get(i).getV2().getId().equalsIgnoreCase(listaVerticesC.get(j).getId())){
                        
                        listaVerticesC.get(j).setValor(listaArista.get(i).getV2().getValor());
                    }
                }
            }
            
            
            //elimina de listaVerticesC el elemento con el que se comparo
            for(int i = 0; i<listaVerticesC.size(); i++){
                
                if(listaVerticesC.get(i).getId().equalsIgnoreCase(v1.getId())){
                    
                    listaCJ.add(v1);
                    listaVerticesC.remove(i);
                }
            }
        }
    }
    
    
    public static void main(String[] args) {
        Grafica g = new Grafica();
        
        
        for(int i = 0; i<g.listaCJ.size(); i++){
            
            System.out.println("id: "+g.listaCJ.get(i).getId()+"\tvalor: "+g.listaCJ.get(i).getValor());
            
        }
        
    }

}
