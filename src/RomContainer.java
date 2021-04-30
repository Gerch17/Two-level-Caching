import java.io.*;
import java.util.Hashtable;
import java.util.UUID;

public class RomContainer implements Container{
    private Hashtable<Integer, String> romTable;

    RomContainer(){
        romTable = new Hashtable<>();

        File tempFile = new File("temp");
        if(!tempFile.exists()){
            tempFile.mkdirs();
        }
    }


    @Override
    public synchronized void put(int key, Employee value) throws IOException {
        String path = "temp/" + UUID.randomUUID().toString() + ".temp";
        romTable.put(key, path);

        FileOutputStream fout = new FileOutputStream(path);
        ObjectOutputStream objStream = new ObjectOutputStream(fout);
        objStream.writeObject(value);
        objStream.flush();
        objStream.close();
        fout.flush();
        fout.close();
    }

    @Override
    public synchronized Employee get(int key) {
        String path = romTable.get(key);

        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream objInputStream = new ObjectInputStream(fin);

            Employee object = (Employee) objInputStream.readObject();
            fin.close();
            objInputStream.close();
            remove(key);
            return object;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public synchronized Boolean remove(int key) {
        if(romTable.containsKey(key)){
            File file = new File(romTable.remove(key));
            file.delete();
            return true;
        }
        return false;
    }

    public synchronized Boolean containsKey(int key){
        return romTable.containsKey(key);
    }

    @Override
    public synchronized int size() {
        return romTable.size();
    }
}
