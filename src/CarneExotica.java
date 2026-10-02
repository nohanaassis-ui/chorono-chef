public class CarneExotica extends Ingrediente {
    private final String tipoAnimal;

    public CarneExotica(String nome, double custo, String epocaOrigem, String tipoAnimal) {
        super(nome, custo, epocaOrigem);
        this.tipoAnimal = tipoAnimal;
    }

    public String getTipoAnimal() { return tipoAnimal; }
}