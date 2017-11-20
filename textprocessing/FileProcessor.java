package textprocessing;

import java.util.Map;
import java.util.HashMap;

public class FileProcessor extends Thread{
    
    private FileContents fc;
    private WordFrequencies wf;
    
    public FileProcessor(FileContents fc, WordFrequencies wf){
        this.fc=fc;
        this.wf=wf;
    }
    
    public void run(){
        String leer = fc.getContents();
        while( leer != null){
            String[] separado= leer.split("[^\\wáéíóúüÁÉÍÓÚÜÑñ]+");
            Map<String,Integer> añadir= new HashMap<String,Integer>();
            int valor=0;
            for (int i =0; i<separado.length ;i++ ) {
                if(añadir.containsKey(separado[i])){
                    valor=añadir.get(separado[i]);
                    valor++;
                    añadir.put(separado[i],valor);
                }else{
                    añadir.put(separado[i],1);
                }
            }
            wf.addFrequencies(añadir);
            leer = fc.getContents();
        }
    }
}
