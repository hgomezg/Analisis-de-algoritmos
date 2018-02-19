public class SpanningTree {
    
    public static void main(String[] args) {
        
        String valor2 = args[0];
        String valor3 = args[1];
        
        String[] nombreArchivo = valor2.split("\\.");
        if(!nombreArchivo[1].equals("graph")){
            System.out.println("El nombre del archivo esta mal");
            return;
        }
        
        if(valor3.equals("kruskal")){
            KruskalF k = new KruskalF(valor2);
            Lector.escritura("salida.graph", k.toStringVertices(), k.getMst());
        }else if(valor3.equals("prim")){
            Prim p = new Prim(valor2);
            Lector.escritura("salida.graph", p.toStringVertices(), p.getListaAristaSalida());
        }else{
        	System.out.println("El nombre del algoritmo esta mal");
        }
    }
}