package textprocessing;
import java.util.Queue;
import java.util.LinkedList;
public class FileContents {
    private Queue<String> queue;
    private int registerCount = 0;
    private boolean closed = false;
    private int maxfiles;
    private int maxchar;
    private int amount =0;
    
    public FileContents(int maxFiles, int maxChars) {
        queue = new LinkedList<>();
        maxfiles = maxFiles;
        maxchar = maxChars;
    }
    public synchronized void registerWriter() {
        registerCount++;
        
    }
    public synchronized void unregisterWriter() {
        registerCount--;
        if(registerCount==0) closed=true;
    }
    
    public synchronized void addContents(String contents) {
        if(queue.isEmpty()){
            amount+=contents.length();
            queue.add(contents);
            notifyAll();
            return;
        } 
        while(queue.size() == maxfiles){
            try{
                wait();
            } catch (InterruptedException e){
            }
        } 
        
        while(contents.length()+ amount > maxchar){
            try{
                wait();
            } catch (InterruptedException e){
            }
        }
        
        amount+=contents.length();
        queue.add(contents);
        notifyAll();
        
    }
    public synchronized String getContents() {
        while(queue.isEmpty()){
            if(closed){
                return null;
            } else{
                try{
                    wait();
                } catch (InterruptedException e){
                }
            }
        }
        String devolver=queue.poll();
        amount-=devolver.length();
        notifyAll();
        return devolver;
    }
}
