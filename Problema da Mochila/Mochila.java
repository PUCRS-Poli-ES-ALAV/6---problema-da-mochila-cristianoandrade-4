import java.util.ArrayList;
import java.util.List;

public class Mochila {

    private List<Item> items;
    private int capacity;

    public Mochila(List<Item> items, int capacity) {
        this.items = items;
        this.capacity = capacity;
        run();
    }

    private static void run() {

    }

    public static List<List<Item>> permutations(List<Item> items) {
        List<List<Item>> result = new ArrayList<>();
        permuteHelper(items, new ArrayList<>(), result);
        return result;
    }

     private static void permuteHelper(List<Item> items, List<Item> current, List<List<Item>> result) {
        if (current.size() == items.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (Item item : items) {
            if (current.contains(item)) {
                continue; 
            }
            current.add(item); 
            permuteHelper(items, current, result);
            current.remove(current.size() - 1);
        }
    }
}