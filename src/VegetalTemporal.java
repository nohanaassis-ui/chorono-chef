public class VegetalTemporal extends Ingrediente {
    private boolean radiotivo;

    public VegetalTemporal(String nome, double custo, String epocaOrigem, boolean radiotivo) {
        super(nome, custo, epocaOrigem);
        this.radiotivo= radiotivo;
    }

    public boolean iseRadioativo()
    { return radiotivo;

    }
}
