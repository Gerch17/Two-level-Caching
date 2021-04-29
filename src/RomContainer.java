import com.sun.security.auth.UnixNumericUserPrincipal;

import java.io.*;
import java.util.Hashtable;
import java.util.UUID;

public class RomContainer<KeyType, ValueType> implements Container<KeyType, ValueType>{
    Hashtable<KeyType, String> table;

    RomContainer(){
        table = new Hashtable<>();

        File tempFile = new File("temp\\");
        if(tempFile.exists()){
            tempFile.mkdirs();
        }
    }


    @Override
    public void put(KeyType key, ValueType value) throws IOException {
        String path;
        path = "temp\\" + UUID.randomUUID().toString() + ".temp";
        table.put(key, path);

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
        String path = table.get(key);

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
        if(table.containsKey(key)){
            File file = new File(table.remove(key));
            file.delete();
        }
    }

    @Override
    public int size() {
        return table.size();
    }
}
