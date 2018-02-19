public class Vertice implements Comparable<Vertice>{
    
    private String nombre;
    private int peso;
    private boolean visitado;

    public Vertice(String nombre, int peso) {
        this.nombre = nombre;
        this.peso = peso;
        this.visitado=false;
    }
    
    public Vertice(){}

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }    

    @Override
    public int compareTo(Vertice t) {
        if(peso>t.getPeso()){
            return 1;
        }else if(peso<t.getPeso()){
            return -1;
        }else{
            return -1;
        }
    }
    
}
