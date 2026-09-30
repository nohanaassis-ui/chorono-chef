public abstract class  Ingrediente extends Cozinhavel {
    private String nome;
    private double custo;
    private String epocaOrigem;
    private int temperatura;
    private int picancia;
    private boolean processado;


    public Ingrediente(String nome, double custo, String epocaOrigem, int temperatura, int picancia, boolean processado) {
        this.nome = nome;
        this.custo = custo;
        this.epocaOrigem = epocaOrigem;
        this.temperatura = 20;
        this.picancia = 0;
        this.processado = false;
    }

    public Ingrediente(String nome, double custo, String epocaOrigem) {
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nome='" + nome + '\'' +
                ", custo=" + custo +
                ", epocaOrigem='" + epocaOrigem + '\'' +
                ", temperatura=" + temperatura +
                ", picancia=" + picancia +
                ", processado=" + processado +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public double getCusto() {
        return custo;
    }

    public String getEpocaOrigem() {
        return epocaOrigem;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public int getPicancia() {
        return picancia;
    }

    public boolean isProcessado() {
        return processado;
    }

    @Override
    public void aquecer(int graus) {
        this.temperatura += graus;
        this.processado = true;
    }

    @Override
    public void temperar(int nivel) {
        this.picancia += nivel;
        this.processado = true;
    }

    @Override
    public boolean estaPronto() {
        return processado;
    }
}




