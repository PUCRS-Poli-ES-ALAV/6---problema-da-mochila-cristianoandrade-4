import java.util.ArrayList;
import java.util.List;

public class Mochila{

    private int capacity;
    private List<Item> mochila;
    
    public Mochila(int capacity) {
        this.capacity = capacity;
        mochila = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (mochila.size() < capacity) {
            return mochila.add(item);
        }
        return false;
    }

    public Item getItem(int index) {
        return mochila.get(index);
    }

    public List<Integer> getIds(){
        List<Integer> list = new ArrayList<>();

        for(Item item : mochila) {
            list.add(item.getId());
        }

        return list;
    }

    public boolean removeItem(Item item) {
        if (getIds().contains(item.getId())) {
            return mochila.remove(item);
        }

        return false;
    }

    public int getCapacidade() {
        return capacity;
    }

    public void setCapacidade(int capacity) {
        this.capacity = capacity;
    }


}