import java.util.ArrayList;
import java.util.List;

public class Items {
    private List<Item> items;

    public Items() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public boolean addItems(List<Item> items) {
        return items.addAll(items);
    }

    public boolean removeItem(Item item) {
        if (getIds().contains(item.getId())) {
            return items.remove(item);
        }

        return false;
    }

    public List<Integer> getIds(){
        List<Integer> list = new ArrayList<>();

        for(Item item : items) {
            list.add(item.getId());
        }

        return list;
    }
}
