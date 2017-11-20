package textprocessing;
import java.util.Queue;
import java.util.LinkedList;
public class FileNames {
    
    private Queue<String> queue;
    private boolean parar = false;
    
    public FileNames(){
        queue = new LinkedList<>();
    }
    public synchronized void addName(String fileName) {
        if(!parar){
            queue.add(fileName);
            notifyAll();
        }
    }
    public synchronized String getName() {
        while(queue.isEmpty()){
            if(parar){
                return null;
            } else{
                try{
                    wait();
                } catch (InterruptedException e){
                }
            }
        }
        notifyAll();
        return queue.poll();
    }
    public synchronized void noMoreNames() {
        parar=true;
    }
}