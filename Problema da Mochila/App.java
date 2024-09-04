import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<Item> items;
    private static Mochila mochila;

    public App(int[] pesos, int[] valores, int capacity) {
        mochila = new Mochila(capacity);
        
        items = new ArrayList<>();
        for (int i = 0; i < pesos.length; i++) {
            items.add(new Item(valores[i], pesos[i]));
        }

        start();
    }

    private static void start() {
        menu();
    }

    public static void menu(){
        boolean sair = false;
        Scanner entrada = new Scanner(System.in);
        int choice = -1;
        String separador = "   =============== ---- - ---- ===============";
        
        String[] opcoes = new String[] {
                "1. Adicionar manualmente item ao catálogo",
                "2. Adicionar item ao catálogo a partir de .txt",
                "3. Remover item do catálogo",
                "4. Editar item do catálogo",
                "5. Listar catálogo",
                "",
                "6. Editar capacidade da mochila",
                "",
                "7. Encontrar solução de força bruta",
                "8. Encontrar solução inteligente",
                "",
                "0. SAIR"
        }; 
        
        System.out.println(separador);
        for (String str : opcoes) {
            System.out.println(str);
        }
        System.out.println(separador);

        try{
            choice = entrada.nextInt();
            entrada.nextLine();
        } catch(Exception e){
            System.out.println("Insira um número válido.");
        }

        if (choice == 0) {
            sair = true;
            System.out.println("Até a próxima!");
            System.out.println();
        } else {
            while(!sair) {
                run(choice);
            }
        }

        entrada.close();
    }

    private static void run(int choice) {
        
        switch (choice) {
            case 1:
                addItemCatalogoManual(items);
                break;
            case 2:
                addItemCatalogoArquivo(items);
                break;
            case 3:
                removeItemCatalogo(items);
                break;
            case 4:
                editarItemCatalogo(items); 
                break;
            case 5:
                listarCatalogo(items);
                break;
            case 6:
                editarMochila(mochila);
                break;
            case 7:
                solucaoForcaBruta(items, mochila);
                break;
            case 8:
                solucaoInteligente(items, mochila);
                break;
            default:
                System.out.println("Escolha uma das opções.");
                break;
        }
    }

    
    
    private static void addItemCatalogoManual(List<Item> items2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemCatalogo'");
    }

    private static void addItemCatalogoArquivo(List<Item> items2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addItemCatalogoArquivo'");
    }

    private static void removeItemCatalogo(List<Item> items2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeItemCatalogo'");
    }

    private static void editarItemCatalogo(List<Item> items2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarItemCatalogo'");
    }

    private static void listarCatalogo(List<Item> items2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarCatalogo'");
    }

    private static void editarMochila(Mochila mochila2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarMochila'");
    }
    
    private static void solucaoForcaBruta(List<Item> items2, Mochila mochila2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'solucao'");
    }

    private static void solucaoInteligente(List<Item> items2, Mochila mochila2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'solucaoInteligente'");
    }
    
    public static List<List<Integer>> getAllSubsets(List<Item> items) {
        List<List<Integer>> subsets = new ArrayList<>();
        generateSubsets(items, 0, new ArrayList<>(), subsets);
        return subsets;
    }

    private static void generateSubsets(List<Item> items, int index, List<Integer> current, List<List<Integer>> subsets) {
        if (index == items.size()) {
            subsets.add(new ArrayList<>(current));
            return;
        }

        // Include the current item
        current.add(items.get(index).getId());
        generateSubsets(items, index + 1, current, subsets);

        // Exclude the current item
        current.remove(current.size() - 1);
        generateSubsets(items, index + 1, current, subsets);
    }
}