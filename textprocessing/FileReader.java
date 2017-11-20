package textprocessing;
public class FileReader extends Thread{
    private FileNames fn;
    private FileContents fc;
    
    public FileReader(FileNames fn, FileContents fc){
        this.fn=fn;
        this.fc=fc;
    }
    
    public void run(){
        String name= fn.getName();
        String entrar;
        fc.registerWriter();
        while(name != null) {
            entrar = Tools.getContents(name);
            fc.addContents(entrar);
            name= fn.getName();
        }
        fc.unregisterWriter();
    }
}
 