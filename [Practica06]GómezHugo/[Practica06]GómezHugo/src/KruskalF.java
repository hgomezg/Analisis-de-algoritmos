
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KruskalF {

    ArrayList<Vertice> listaVertices;
    ArrayList<Arista> listaArista;
    private ArrayList<String> padre;

    public KruskalF(String direccion) {
        listaVertices = new ArrayList<>();
        listaArista = new ArrayList<>();
        Lector l = new Lector(direccion);
        listaVertices = l.getListaVertices();
        listaArista = l.getListaAristas();
        padre = new ArrayList<>();
        KruskalMST();
    }

    //Método de inicialización
    private void makeSet() {
        for (int i = 0; i < listaVertices.size(); i++) {
            padre.add(listaVertices.get(i).getNombre());
        }
    }

    //Método para encontrar la raiz del vértice actual X
    private String find(String x) {
        int pos = -1;
        for (int i = 0; i < listaVertices.size(); i++) {
            if (x.equalsIgnoreCase(listaVertices.get(i).getNombre())) {
                pos = i;
            }
        }
        if (pos == -1) {
            return "no se encontro el vertice";
        }
        if (x.equalsIgnoreCase(padre.get(pos))) {
            return x;
        } else {
            return find(padre.get(pos));
        }
    }

    //Metodo que determina si dos vertices estan o no en la misma componente conexa
    private boolean sameComponent(String x, String y) {
        if (find(x).equalsIgnoreCase(find(y))) {
            return true;
        } else {
            return false;
        }
    }

    private void union(String x, String y) {
        String xRoot = find(x);
        String yRoot = find(y);
        int pos = -1;
        for (int i = 0; i < padre.size(); i++) {
            if (xRoot.equalsIgnoreCase(padre.get(i))) {
                padre.set(i, yRoot);
            }
        }
    }

    PriorityQueue<Arista> listaAristeTemporal = new PriorityQueue<>();
    ArrayList<Arista> mst = new ArrayList<>();

    public void KruskalMST() {

        String origen, destino;
        int peso;
        int numeroDeAristas = 0, total = 0;

        makeSet();
        for (int i = 0; i < listaArista.size(); i++) {
            listaAristeTemporal.add(listaArista.get(i));
        }

        int num = listaAristeTemporal.size();
        for (int i = 0; i < num; i++) {
            Arista temp = listaAristeTemporal.remove();
            origen = temp.getV1().getNombre();
            destino = temp.getV2().getNombre();
            peso = temp.getPeso();
            if (!sameComponent(origen, destino)) {
                total = +peso;
                numeroDeAristas++;
                mst.add(temp);
                union(origen, destino);
            }
        }
    }

    public String toStringVertices() {

        String sal = "";
        for (int i = 0; i < listaVertices.size(); i++) {
            Vertice temp = listaVertices.get(i);
            if (i < listaVertices.size() - 1) {

                sal = sal + temp.getNombre() + ", ";
            } else {
                sal = sal + temp.getNombre();
            }
        }

        sal.substring(1, sal.length() - 1);

        return sal.trim();
    }

    public ArrayList<Arista> getMst() {
        return mst;
    }

    
    
    public String toStringAristas() {

        String salida = "";
        for (int i = 0; i < mst.size(); i++) {
            Arista temp = mst.get(i);
            salida = salida + temp.toString() + "\n";
        }
        return salida;
    }

}
