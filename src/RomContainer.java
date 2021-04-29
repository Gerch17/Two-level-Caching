import java.io.*;
import java.util.Hashtable;
import java.util.UUID;

public class RomContainer<KeyType, ValueType> implements Container<KeyType, ValueType>{
    private Hashtable<KeyType, String> romTable;

    RomContainer(){
        romTable = new Hashtable<>();

        File tempFile = new File("temp\\");
        if(!tempFile.exists()){
            tempFile.mkdirs();
        }
    }


    @Override
    public void put(KeyType key, ValueType value) throws IOException {
        String path = "temp\\" + UUID.randomUUID().toString() + ".temp";
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
    public ValueType get(KeyType key) {
        String path = romTable.get(key);

        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream objInputStream = new ObjectInputStream(fin);

            ValueType object = (ValueType) objInputStream.readObject();
            fin.close();
            objInputStream.close();
            remove(key);
            return object;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void remove(KeyType key) {
        if(romTable.containsKey(key)){
            File file = new File(romTable.remove(key));
            file.delete();
        }
    }

    @Override
    public int size() {
        return romTable.size();
    }
}
