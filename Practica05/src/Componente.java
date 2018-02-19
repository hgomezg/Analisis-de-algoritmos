
import java.util.ArrayList;

public class Componente {
    
    ArrayList<Vertice> lVertices; 
    ArrayList<Arista> lAristas; 
    
    public Componente(){}

    public Componente(ArrayList<Vertice> lVertices, ArrayList<Arista> lAristas) {
        this.lVertices = lVertices;
        this.lAristas = lAristas;
    }

    public ArrayList<Vertice> getlVertices() {
        return lVertices;
    }

    public void setlVertices(ArrayList<Vertice> lVertices) {
        this.lVertices = lVertices;
    }

    public ArrayList<Arista> getlAristas() {
        return lAristas;
    }

    public void setlAristas(ArrayList<Arista> lAristas) {
        this.lAristas = lAristas;
    }


    
    
    
}
