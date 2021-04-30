import java.io.FileNotFoundException;
import java.io.IOException;

public interface Container {
    void put(int id, Employee employee) throws IOException;
    Employee get(int id);
    Boolean remove(int id);
    int size();
}
