public class ItemSintetico extends Ingrediente {
    private int voltagem;

    public ItemSintetico(String nome, double custo, String epocaOrigem, int voltagem) {
        super(nome, custo, epocaOrigem);
        this.voltagem = voltagem;
    }

    public int getVoltagem() { return voltagem; }
}


