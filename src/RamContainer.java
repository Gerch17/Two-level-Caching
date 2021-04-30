import java.io.IOException;
import java.util.Hashtable;

public class RamContainer implements Container{
    private Hashtable<Integer, Employee> table;
    int size;
    private RomContainer romContainer;

    RamContainer(int size){
        table = new Hashtable<>();
        this.size = size;
        romContainer = new RomContainer();
    }


    @Override
    public synchronized void put(int id, Employee value) {
        if(table.size() < size) {
            table.put(id, value);
            System.out.println("элемент добавлен в контейнер");
        }
        else{
            putToRom();
            table.put(id, value);
            System.out.println("элемент добавлен в контейнер, с переносом старого на диск");
        }
    }

    @Override
    public synchronized Employee get(int key) {
        if(table.containsKey(key)){
            System.out.println("элемент получен из контейнера в оперативной памяти");
            return table.get(key);
        }
        else if (romContainer.containsKey(key)){
            putToRom();
            table.put(key, romContainer.get(key));
            System.out.println("старый элемент был перенесён на диск, вызванный элемент перенесён в контейнер оперативной памяти");
            return table.get(key);
        }
        System.out.println("элемент не найден");
        return null;
    }

    @Override
    public synchronized Boolean remove(int key) {
        if(table.containsKey(key)) {
            table.remove(key);
            System.out.println("элемент удалён из контейнера в оперативной памяти");
            return true;
        } else if(romContainer.remove(key)) {
            System.out.println("элемент удалён из контейнера на диске");
            return true;
        }
        System.out.println("элемент не найден");
        return false;
    }

    @Override
    public synchronized int size() {
        return table.size() + romContainer.size();
    }

    //Функцмя, которая переносит крайний по очерёдности добавленный объект на диск и удаляет этот объект из оперативной памяти
    private synchronized Boolean putToRom(){
        try {
            int id = Integer.parseInt(table.entrySet().toArray()[table.entrySet().toArray().length-1].toString().split("=")[0]);
            Employee employee = table.get(id);
            romContainer.put(id, employee);
            table.remove(id);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
