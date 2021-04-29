import java.io.FileNotFoundException;
import java.io.IOException;

public interface Container <KeyType, ValueType>{
    void put(KeyType key, ValueType value) throws IOException;
    ValueType get(KeyType key);
    void remove(KeyType key);
    int size();
}
