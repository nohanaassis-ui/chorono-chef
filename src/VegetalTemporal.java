public class VegetalTemporal extends Ingrediente {
    private final boolean radiotivo;

    public VegetalTemporal(String nome, double custo, String epocaOrigem, boolean radiotivo) {
        super(nome, custo, epocaOrigem);
        this.radiotivo= radiotivo;
    }



    public boolean iseRadioativo()
    { return radiotivo;

    }

    private void setTemperatura(int i) {
    }

    public void resfriar(int graus) {
        setTemperatura(getTemperatura() - graus);
    }

}
