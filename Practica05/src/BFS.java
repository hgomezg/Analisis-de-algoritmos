
import java.util.ArrayList;

public class BFS {
    
    

    public static void main(String[] args) {

        //BFS b = new BFS(args[0], args[1]);
        BFS b = new BFS("CicloPar.graph", "BFS");

    }

/////////////////////////Inicia algoritmo BFS////////////////////////////
    Vertice[] arrVertice;
    ArrayList<Arista> listaArista;

    public BFS(String direccion, String metodo) {

        Lector l = new Lector(direccion);

        arrVertice = l.arrVertices;
        listaArista = l.listaArista;

        if (metodo.equalsIgnoreCase("BFS")) {

            bfs1();
            
            
            for (int i = 0; i < lisComponentes.size(); i++) {
                System.out.print("componente " + (i + 1) + "{");
                for (int j = 0; j < lisComponentes.get(i).size(); j++) {
                    System.out.print(lisComponentes.get(i).get(j).getId() + " ");
                }
                System.out.print("}");
                
                System.out.println();
            }
            System.out.print("vertices{");
            for (int i = 0; i < lArista1.size(); i++) {
                
                System.out.print(lArista1.get(i).getV1().getId()+"-"+lArista1.get(i).getV2().getId()+" ");
            }
            System.out.println("}");
            
            
        }

    }
  
    ArrayList<ArrayList<Vertice>> lisComponentes = new ArrayList<>();
    ArrayList<ArrayList<Arista>> lisAris = new ArrayList<>();
    public void bfs1() {

        for (int i = 0; i < arrVertice.length; i++) {

            Vertice temp = arrVertice[i];
            boolean pertenece = false;
            for (int j = 0; j < lisComponentes.size(); j++) {

                for (int k = 0; k < lisComponentes.get(j).size(); k++) {

                    if (temp.getId().equalsIgnoreCase(lisComponentes.get(j).get(k).getId())) {
                        pertenece = true;
                    }
                }

            }

            if (!pertenece) {
                temp.setValor(0);
                lisComponentes.add(bfs(temp).getlVertices());
                //lisAris.add(bfs(temp).getlAristas());
            }
            
        }

    }
    
    
    
    ArrayList<Arista> lArista1 = new ArrayList<>();
    public Componente bfs(Vertice v1) {
        
        ArrayList<Vertice> listaComponentes = new ArrayList<>();
        
        ArrayList<Vertice> listaSalida1 = new ArrayList<>();
        listaComponentes.add(v1);
        listaSalida1.add(v1);
        
        while (listaComponentes.size() != 0) {

            Vertice actual = listaComponentes.get(0);

            for (int i = 0; i < listaArista.size(); i++) {

                if (actual.getId().equalsIgnoreCase(listaArista.get(i).getV1().getId())) {

                    Vertice temp = listaArista.get(i).getV2();

                    for (int j = 0; j < arrVertice.length; j++) {

                        if (temp.getId().equalsIgnoreCase(arrVertice[j].getId())) {

                            if (arrVertice[j].getValor() == -1) {

                                arrVertice[j].setValor(actual.getValor() + 1);
                                listaComponentes.add(temp);
                                listaSalida1.add(temp);
                                lArista1.add(new Arista(actual, arrVertice[j]));
                                
                            }

                        }
                    }
                }

                if (actual.getId().equalsIgnoreCase(listaArista.get(i).getV2().getId())) {

                    Vertice temp = listaArista.get(i).getV1();

                    for (int j = 0; j < arrVertice.length; j++) {

                        if (temp.getId().equalsIgnoreCase(arrVertice[j].getId())) {

                            if (arrVertice[j].getValor() == -1) {

                                arrVertice[j].setValor(actual.getValor() + 1);
                                listaComponentes.add(temp);
                                listaSalida1.add(temp);
                                lArista1.add(new Arista(v1, temp));
                                
                            }
                        }
                    }
                }
            }
            listaComponentes.remove(0);
        }
        
        Componente c = new Componente();
        c.setlAristas(lArista1);
        c.setlVertices(listaSalida1);
        return c;
    }
    ////////////////////Termina algoritmo BFS///////////////////////////////
    
}
