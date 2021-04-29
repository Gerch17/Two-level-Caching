import java.util.Hashtable;

public class RamContainer<KeyType, ValueType> implements Container<KeyType, ValueType>{
    Hashtable<KeyType, ValueType> table;
    int size;
    RomContainer<KeyType, String> romContainer;

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
            romContainer.put();
        }
    }

    @Override
    public ValueType get(KeyType key) {
        try{
            return table.get(key);
        }catch (Exception e){
            //TODO put here method from ROMContainer
            return null;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void remove(KeyType key) {
        try{
            table.remove(key);
        }catch (ClassNotFoundException e){
            //TODO put here method from ROMContainer

        }catch (Exception e){

        }
    }

    @Override
    public int size() {
        //TODO return size from ROM table;
        return table.size();
    }
}
