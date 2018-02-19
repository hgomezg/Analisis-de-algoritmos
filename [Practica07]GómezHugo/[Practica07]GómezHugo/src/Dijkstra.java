
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra {

    ArrayList<Vertice> listaVertices;
    ArrayList<Arista> listaArista;
    ArrayList<Arista> listaSalida;
    ArrayList<Vertice> listaSalidaVer;
    PriorityQueue<Arista> colaT;
    

    public Dijkstra(String direccion) {

        listaVertices = new ArrayList<>();
        listaArista = new ArrayList<>();
        Lector l = new Lector(direccion);
        listaVertices = l.getListaVertices();
        listaArista = l.getListaAristas();
        listaSalida = new ArrayList<>();
        listaSalidaVer = new ArrayList<>();
        colaT = new PriorityQueue<>();
        
        dijsktraRM();
        
        String vertices ="";
        for(int i=0;i<listaVertices.size();i++){
            if(i==listaVertices.size()-1){
                vertices = vertices+listaVertices.get(i).getNombre();
            }else{
                vertices = vertices+listaVertices.get(i).getNombre()+",";
            }
        }
        listaSalida.remove(0);
        l.escritura("salida.graph", vertices, listaSalida);
        
    }
    
    public void dijsktraRM(){
        
        Vertice temp1 = new Vertice(listaVertices.get(0).getNombre(), 0);
        Arista p = new Arista(temp1, temp1, 0);
        colaT.add(p);
        
        while(colaT.size()!=0){
            
            Arista u = colaT.remove();
            //System.out.println(u.getV1().getNombre()+"-"+u.getV2().getNombre()+"-"+u.getPeso()+":.");
            if(!u.getV1().isVisitado()){
                
                listaSalida.add(u);
                listaSalidaVer.add(u.getV2());
                //cmabiamos el valor de la lista de vertices y de la lista de aristas por
                //visitados
                u.getV1().setVisitado(true);
                for(int i= 0; i<listaVertices.size(); i++){
                    if(u.getV1().getNombre().equalsIgnoreCase(listaVertices.get(i).getNombre())){
                        listaVertices.get(i).setVisitado(true);
                    }
                }
                for(int i=0;i<listaArista.size();i++){
                    if(listaArista.get(i).getV1().getNombre().equalsIgnoreCase(u.getV1().getNombre())){
                        listaArista.get(i).getV1().setVisitado(true);
                    }
                    if(listaArista.get(i).getV2().getNombre().equalsIgnoreCase(u.getV1().getNombre())){
                        listaArista.get(i).getV2().setVisitado(true);
                    }
                }
                
                
                
                for(int i= 0;i<listaArista.size();i++){
                    
                    if(listaArista.get(i).getV1().getNombre().equalsIgnoreCase(u.getV1().getNombre())){
                        int w = listaArista.get(i).getPeso();
                        if(!listaArista.get(i).getV2().isVisitado()){
                            
                            relajacion(u.getV1(), listaArista.get(i).getV2(), w);
                        }
                    }
                    
                    if(listaArista.get(i).getV2().getNombre().equalsIgnoreCase(u.getV1().getNombre())){
                        int w = listaArista.get(i).getPeso();
                        if(!listaArista.get(i).getV1().isVisitado()){
                            
                            relajacion(u.getV1(), listaArista.get(i).getV1(), w);
                        }
                    }
                    
                }
            }
            
        }
    }
    
    private void relajacion(Vertice actual, Vertice adyacente, int peso){
        
        int distancia = actual.getPeso()+peso;
        if(distancia<adyacente.getPeso()){
            adyacente.setPeso(distancia);
            for(Vertice v : listaVertices){
                if(v.getNombre().equalsIgnoreCase(adyacente.getNombre())){
                    v.setPeso(distancia);
                }
            }
            for(Arista a : listaArista){
                if(a.getV1().getNombre().equalsIgnoreCase(adyacente.getNombre())){
                    a.getV1().setPeso(distancia);
                }
                if(a.getV2().getNombre().equalsIgnoreCase(adyacente.getNombre())){
                    a.getV2().setPeso(distancia);
                }
            }
            
            Arista agr = new Arista(adyacente, actual, distancia);
            colaT.add(agr);
        }       
    }
    
    
    public static void main(String[] args) {
        String direccion = args[0];
        String[] sepDireccion = direccion.split("\\.");
        if(!sepDireccion[1].equals("graph")){
            System.out.println("Direccion incorrecta");
            System.exit(0);
        }else{
            Dijkstra d = new Dijkstra(direccion);
        }    
    }
}