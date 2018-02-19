public class Arista implements Comparable<Arista>{

    private Vertice v1;
    private Vertice v2;
    private int peso;

    public Arista(Vertice v1, Vertice v2, int peso) {
        this.v1 = v1;
        this.v2 = v2;
        this.peso = peso;
    }
    
    public Arista(){}

    public Vertice getV1() {
        return v1;
    }

    public void setV1(Vertice v1) {
        this.v1 = v1;
    }

    public Vertice getV2() {
        return v2;
    }

    public void setV2(Vertice v2) {
        this.v2 = v2;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista t) {
        if(peso>t.getPeso()){
            return 1;
        }else if(peso<t.getPeso()){
            return -1;
        }else{
            return 0;
        }
    }
}
