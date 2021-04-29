import java.io.IOException;
import java.util.Hashtable;

public class RamContainer<KeyType, ValueType> implements Container<KeyType, ValueType>{
    private Hashtable<KeyType, ValueType> table;
    int size;
    private RomContainer<KeyType, ValueType> romContainer;

    RamContainer(int size){
        table = new Hashtable<>();
        this.size = size;
        romContainer = new RomContainer<>();
    }


    @Override
    public void put(KeyType key, ValueType value) {
        if(table.size() < size)
            table.put(key, value);
        else{
            putToRom();
            table.put(key, value);
        }
    }

    @Override
    public ValueType get(KeyType key) {
        if(table.containsKey(key))
            return table.get(key);
        else {
            putToRom();
            table.put(key, romContainer.get(key));
            return romContainer.get(key);
        }
    }

    @Override
    public void remove(KeyType key) {
        try{
            table.remove(key);
        }catch (IndexOutOfBoundsException e){
            romContainer.remove(key);

        }catch (Exception e){

        }
    }

    @Override
    public int size() {
        return table.size() + romContainer.size();
    }

    //Функцмя, которая переносит крайний по очерёдности добавленный объект на диск и удаляет этот объект из оперативной памяти
    private void putToRom(){
        try {
            romContainer.put((KeyType) table.entrySet().toArray()[table.entrySet().toArray().length-1], (ValueType) table.values().toArray()[table.size()-1]);
            String q = table.entrySet().toArray()[table.size()-1].toString().split("=")[0];
            System.out.println(q);
            table.remove(Integer.parseInt(q));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
