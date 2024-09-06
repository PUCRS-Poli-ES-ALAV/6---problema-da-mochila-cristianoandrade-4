import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class App {

    private static List<Item> items;
    private static Mochila mochila;
    private static boolean jaCarregouDeArquivo;

    public App(int[] pesos, int[] valores, int capacity) {
        mochila = new Mochila(capacity);
        
        items = new ArrayList<>();
        for (int i = 0; i < pesos.length; i++) {
            items.add(new Item(valores[i], pesos[i]));
        }

        menu(items, mochila);
    }

    public App(int capacity) {
        mochila = new Mochila(capacity);
        
        items = new ArrayList<>();

        menu(items, mochila);
    }

    public static void menu(List<Item> items, Mochila mochila) {
        boolean sair = false;
        Scanner entrada = new Scanner(System.in);
        String separador = "   =============== ---- - ---- ===============";
        
        String[] opcoes = {
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

        while (!sair) {
            exibirMenu(separador, opcoes);
            int choice = obterEscolhaValida(entrada);

            switch (choice) {
                case 0:
                    sair = true;
                    System.out.println("Até a próxima!");
                    break;
                default:
                    executarOpcao(choice, items, mochila);
                    break;
            }
        }
    }

    private static void exibirMenu(String separador, String[] opcoes) {
        System.out.println(separador);
        for (String opcao : opcoes) {
            System.out.println(opcao);
        }
        System.out.println(separador);
    }


    private static void executarOpcao(int choice, List<Item> items, Mochila mochila) {
        switch (choice) {
            case 1:
                addItemCatalogoManual();
                break;
            case 2:
                addItemCatalogoArquivo();
                break;
            case 3:
                removeItemCatalogo();
                break;
            case 4:
                editarItemCatalogo(); 
                break;
            case 5:
                listarCatalogo();
                break;
            case 6:
                editarMochila();
                break;
            case 7:
                solucaoForcaBruta();
                break;
            case 8:
                solucaoInteligente();
                break;
            default:
                System.out.println("Opção inválida. Escolha uma das opções.");
                break;
        }
    }

    private static void addItemCatalogoManual() {
        Scanner entrada = new Scanner(System.in);

        int peso = obterValorValido(entrada, "Insira peso do item: ");
        int valor = obterValorValido(entrada, "Insira valor do item: ");

        Item item = new Item(valor, peso);
        items.add(item);
        System.out.println("Item adicionado ao catálogo.");
    }

    
    private static void addItemCatalogoArquivo() {
        if (jaCarregouDeArquivo) {
            System.out.println("Arquivo já carregado!");
            return;
        }

        String path = "./dados/casos.txt"; // Caminho para o arquivo de dados
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                if (linha.startsWith("Pesos:")) {
                    // Remove o prefixo "Pesos:" e divide a string pelos separadores de vírgula
                    String[] pesosStr = linha.replace("Pesos:", "").trim().split(",");
                    List<Integer> pesos = new ArrayList<>();
                    
                    for (String peso : pesosStr) {
                        pesos.add(Integer.parseInt(peso.trim()));
                    }
    
                    linha = br.readLine();
                    if (linha.startsWith("Valores:")) {
                        String[] valoresStr = linha.replace("Valores:", "").trim().split(",");
                        List<Integer> valores = new ArrayList<>();

                        for (String valor : valoresStr) {
                            valores.add(Integer.parseInt(valor.trim()));
                        }
                        
                        for (int i = 0; i < pesos.size() && i < valores.size(); i++) {
                            Item item = new Item(valores.get(i), pesos.get(i));
                            items.add(item);
                        }
                    }
                }
            }
            jaCarregouDeArquivo = true;
            System.out.println("Itens adicionados do arquivo com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
    
    private static void removeItemCatalogo() {
        if (items.isEmpty()) {
            System.out.println("O catálogo está vazio. Não há itens para remover.");
            return;
        }

        Scanner entrada = new Scanner(System.in);
        boolean ok = false;
        int index = -1;

        while (!ok) {
            try {
                listarCatalogo();

                System.out.print("Insira o índice do item a ser removido (1 a " + items.size() + "): ");
                index = entrada.nextInt();
                entrada.nextLine(); 

                if (index < 1 || index > items.size()) {
                    System.out.println("Índice fora do intervalo. Tente novamente.");
                } else {
                    ok = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                entrada.nextLine(); 
            }
        }

        items.remove(index - 1);
        System.out.println("Item removido com sucesso.");
    }
    
    private static void editarItemCatalogo() {
        if (items.isEmpty()) {
            System.out.println("O catálogo está vazio. Não há itens para editar.");
            return;
        }
    
        Scanner entrada = new Scanner(System.in);
        listarCatalogo();
    
        int index = -1;
        boolean ok = false;
        while (!ok) {
            try {
                System.out.print("Insira o índice do item que deseja editar (1 a " + items.size() + "): ");
                index = entrada.nextInt();
                if (index >= 1 && index <= items.size()) {
                    ok = true;
                } else {
                    System.out.println("Índice inválido. Por favor, insira um índice válido.");
                }
            } catch (Exception e) {
                System.out.println("Insira um número válido.");
                entrada.nextLine(); 
            }
        }
    
        Item item = items.get(index-1);
        System.out.println("Editando o item: " + item);

        int novoPeso = obterValorValido(entrada, "Insira o novo peso do item (atual: " + item.getPeso() + "): ");
        item.setPeso(novoPeso);
    
        int novoValor = obterValorValido(entrada, "Insira o novo valor do item (atual: " + item.getValor() + "): ");
        item.setValor(novoValor);
    
        System.out.println("Item atualizado com sucesso: " + item);
    }

    
    private static void listarCatalogo() {
        System.out.println("Catálogo Atual:");
        Item item;
        int peso;
        int valor;
        
        for (int i = 0; i < items.size(); i++) {
            item = items.get(i);
            peso = item.getPeso();
            valor = item.getValor();
            
            System.out.println(i+1 + ". Peso: " + peso + ", Valor: " + valor + ".");
        }
    }
    
    private static void editarMochila() {
        Scanner entrada = new Scanner(System.in);

        int novaCapacidade = obterValorValido(entrada, "Insira nova capacidade da mochila (atual: " + mochila.getCapacidade() + "): ");
        mochila.setCapacidade(novaCapacidade);
    
        System.out.println("Capacidade atualizada com sucesso!");
    }
    
    private static void solucaoForcaBruta() {
        List<List<Integer>> subsets = getAllSubsets(items);
        List<List<Integer>> valid_subsets = new ArrayList<>();

        for (List<Integer> list : subsets) {
            if (sumOfListById(list) <= mochila.getCapacidade()) {
                valid_subsets.add(list);
            }
        }

        List<Integer> idsOfGreater = greaterOfListById(valid_subsets);
        List<Item> greater = new ArrayList<>();
        
        for (int id : idsOfGreater) {
            greater.add(getById(id));
        }
        
        System.out.println("-- índice, id, peso, valor -- Peso total= " + sumOfListById(idsOfGreater));
        for (Item item : greater) {
            String str = String.valueOf(item.getId());
            int indice = Integer.parseInt(str.substring(str.length()-1));
            System.out.println(indice+1 + ", " + item.getId() + ", " + item.getPeso() + ", " + item.getValor());
            System.out.println();
        }
    }
    
    private static void solucaoInteligente() {
        // TODO
    }

    private static List<Integer> greaterOfListById(List<List<Integer>> list) {
        List<Integer> greater = list.get(0);
        int sum = sumOfListById(greater);

        for (int i = 1; i < list.size(); i++) {
            if (sumOfListById(list.get(i)) > sum) {
                greater = list.get(i);
            }
        }

        return greater;
    }

    private static int sumOfListById(List<Integer> list) {
        int sum = 0;

        Item item;
        for (int id : list) {
            item = getById(id);
            sum += item.getPeso();
        }

        return sum;
    }

    private static Item getById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
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
        
        current.add(items.get(index).getId());
        generateSubsets(items, index + 1, current, subsets);
        
        current.remove(current.size() - 1);
        generateSubsets(items, index + 1, current, subsets);
    }

    private static int obterEscolhaValida(Scanner entrada) {
        int choice = -1;
        boolean escolhaValida = false;

        while (!escolhaValida) {
            System.out.print("Escolha uma opção: ");
            try {
                choice = entrada.nextInt();
                entrada.nextLine();
                escolhaValida = true;
            } catch (Exception e) {
                System.out.println("Insira um número válido.");
                entrada.nextLine(); // Limpa o buffer do scanner
            }
        }

        return choice;
    }

    private static int obterValorValido(Scanner entrada, String mensagem) {
        int valor = -1;
        boolean valido = false;
    
        while (!valido) {
            System.out.print(mensagem);
            try {
                valor = entrada.nextInt();
                entrada.nextLine(); // Limpa o buffer do scanner
                valido = true;
            } catch (Exception e) {
                System.out.println("Insira um valor válido.");
                entrada.nextLine(); // Limpa o buffer do scanner
            }
        }
    
        return valor;
    }
}