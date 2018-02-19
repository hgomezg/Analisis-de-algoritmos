import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {

    private ArrayList<Vertice> listaVertices;
    private ArrayList<Arista> listaAristas;
    private Lector lector;
    private PriorityQueue<Arista> cola;
    private ArrayList<Vertice> listaVerticesSalida;
    private ArrayList<Arista> listaAristaSalida;

    public Prim(String direccion) {

        this.lector = new Lector(direccion);
        this.listaVertices = lector.getListaVertices();
        this.listaAristas = lector.getListaAristas();
        this.cola = new PriorityQueue<Arista>();
        this.listaVerticesSalida = new ArrayList<>();
        this.listaAristaSalida = new ArrayList<>();

        arbolGenerador();
    }

    public void arbolGenerador() {

        Vertice temporal = listaVertices.get(0);

        while (listaAristaSalida.size() != listaVertices.size() - 1) {

            if (cola.size() != 0) {
                temporal = cola.remove().getV1();
            }

            for (int i = 0; i < listaAristas.size(); i++) {

                Arista aristaTemporal = listaAristas.get(i);

                if (temporal.getNombre().equalsIgnoreCase(aristaTemporal.getV1().getNombre())) {
                    boolean meter = true;
                    for (int j = 0; j < listaVerticesSalida.size(); j++) {
                        if (aristaTemporal.getV2().getNombre().equalsIgnoreCase(listaVerticesSalida.get(j).getNombre())) {
                            meter = false;
                        }
                    }
                    if (meter) {
                        Vertice v1 = aristaTemporal.getV2();
                        Vertice v2 = aristaTemporal.getV1();
                        int pesoTemporal = aristaTemporal.getPeso();
                        cola.add(new Arista(v1, v2, pesoTemporal));
                    }

                }//termina primer lado

                if (temporal.getNombre().equalsIgnoreCase(aristaTemporal.getV2().getNombre())) {
                    boolean meter = true;
                    for (int j = 0; j < listaVerticesSalida.size(); j++) {
                        if (aristaTemporal.getV1().getNombre().equalsIgnoreCase(listaVerticesSalida.get(j).getNombre())) {
                            meter = false;
                        }
                    }
                    if (meter) {
                        Vertice v1 = aristaTemporal.getV1();
                        Vertice v2 = aristaTemporal.getV2();
                        int pesoTemporal = aristaTemporal.getPeso();
                        cola.add(new Arista(v1, v2, pesoTemporal));
                    }

                }//termina segundo lado

            }//termina for de listaArista
            Arista aristaTemporal2 = cola.element();
            listaVerticesSalida.add(aristaTemporal2.getV1());
            listaVerticesSalida.add(aristaTemporal2.getV2());
            listaAristaSalida.add(aristaTemporal2);

        }//termina while de listaAristaSalida

    }//termina metodo

    public String toStringVertices(){
        String sal ="";
        for(int i=0;i<listaVertices.size(); i++){       
            Vertice v = listaVertices.get(i);
            if(i<listaVertices.size()-1){
                sal = sal+v.getNombre()+", ";
            }else{
                sal = sal +v.getNombre();
            }
        }
        return sal;
    }
    
    public String toStringAristas(){
        String sal="";
        for(int i=0; i<listaAristaSalida.size(); i++){
            Arista temp = listaAristaSalida.get(i);
            sal=sal+temp.getV1().getNombre()+","+temp.getV2().getNombre()+","+String.valueOf(temp.getPeso())+"\n";
        }
        return sal.trim();
    }

    public ArrayList<Arista> getListaAristaSalida() {
        return listaAristaSalida;
    }
    
}
