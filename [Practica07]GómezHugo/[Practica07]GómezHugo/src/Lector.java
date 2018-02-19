import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lector {

    private ArrayList<Vertice> listaVertices;
    private ArrayList<Arista> listaAristas;
    public Lector(String direccion){
        listaVertices = new ArrayList<>();
        listaAristas = new ArrayList<>();
        leer(direccion);
    }
    
    private void leer(String direccion) {
        try {

            FileReader fr = new FileReader(direccion);
            BufferedReader br = new BufferedReader(fr);
            String aux = "";
            int i = 0;
            while (true) {
                aux = br.readLine();
                if (aux != null) {
                    if(i==0){
                        String[] arrSep = aux.split(",");
                        for(int j = 0; j<arrSep.length; j++){
                            listaVertices.add(new Vertice(arrSep[j].trim(), 10000));
                        }
                    }else{
                    String[] sepAristas = aux.split(",");
                    listaAristas.add(new Arista(new Vertice(sepAristas[0].trim(), 10000), new Vertice(sepAristas[1].trim(), 10000)
                            , Integer.parseInt(sepAristas[2].trim())));
                    }
                } else {
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            
            System.out.println("error en lectura de archivo. ERRROR: " + e);
            System.exit(0);
        }
    }
    
    public void escritura(String direccion, String vertices, ArrayList<Arista> listaAristas){
        File f = new File(direccion);
        FileWriter fw;
        try {
            fw=new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);       
            pw.write(vertices);
            pw.append("\r\n");
            for(int i= 0; i<listaAristas.size();i++){
                Arista aris = listaAristas.get(i);
                pw.write(aris.getV1().getNombre()+","+aris.getV2().getNombre()+","+String.valueOf(aris.getPeso()));
                pw.append("\r\n");
            }
            
            bw.close();
            pw.close();       
        } catch (Exception e) {   
            System.out.println("no se pudo crear el; archivo");   
        }  
    }

    public ArrayList<Vertice> getListaVertices() {
        return listaVertices;
    }

    public void setListaVertices(ArrayList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public ArrayList<Arista> getListaAristas() {
        return listaAristas;
    }

    public void setListaAristas(ArrayList<Arista> listaAristas) {
        this.listaAristas = listaAristas;
    }
}
