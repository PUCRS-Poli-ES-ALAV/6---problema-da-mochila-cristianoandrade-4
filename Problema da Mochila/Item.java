public class Item{
    private Integer valor;
    private Integer peso;
    private Integer id;
    private static Integer curr = 1000;

    public Item(int valor, int peso) {
        this.valor = valor;
        this.peso = peso;
        this.id = curr++;
    }

    public Integer getValor() {
        return this.valor;
    }

    public Integer getPeso() {
        return this.peso;
    }   
    
    public Integer getId() {
        return this.id;
    }    
}