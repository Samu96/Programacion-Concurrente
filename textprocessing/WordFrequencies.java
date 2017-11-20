package textprocessing;
import java.util.Map;
import java.util.HashMap;

public class WordFrequencies {
    private Map<String,Integer> all;
    
    public WordFrequencies(){
        all = new HashMap<String,Integer>();
    }
    public void addFrequencies(Map<String,Integer> f){
            for (Map.Entry<String, Integer> entry : f.entrySet()){
    
            if(all.containsKey(entry.getKey())){
                int valor=f.get(entry.getKey());
                valor+= all.get(entry.getKey());
                all.put(entry.getKey(),valor);
            }else{
                int valor=f.get(entry.getKey());
                all.put(entry.getKey(),valor);
            }
        }
    }
    public Map<String,Integer> getFrequencies(){
        
        return all;
    }
}
