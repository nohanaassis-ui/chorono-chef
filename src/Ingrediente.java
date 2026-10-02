public abstract class Ingrediente implements Cozinhavel {
    private String nome;
    private double custo;
    private String epocaOrigem;
    private int temperatura;
    private int picancia;
    private boolean processado;

    // Construtor principal completo
    public Ingrediente(String nome, double custo, String epocaOrigem, int temperatura, int picancia, boolean processado) {
        this.nome = nome;
        this.custo = custo;
        this.epocaOrigem = epocaOrigem;
        this.temperatura = temperatura;
        this.picancia = picancia;
        this.processado = processado;
    }

    // Construtor simplificado (define padrões iniciais para itens novos)
    public Ingrediente(String nome, double custo, String epocaOrigem) {
        this(nome, custo, epocaOrigem, 20, 0, false); // Chama o construtor completo com valores padrão
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

    // Getters e Setters (Encapsulamento)
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getCusto() { return custo; }
    public void setCusto(double custo) { this.custo = custo; }

    public String getEpocaOrigem() { return epocaOrigem; }
    public void setEpocaOrigem(String epocaOrigem) { this.epocaOrigem = epocaOrigem; }

    public int getTemperatura() { return temperatura; }
    public int getPicancia() { return picancia; }
    public boolean isProcessado() { return processado; }

    // Métodos da Interface Cozinhavel
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

    // Método especifico de alteração de estado
    public void resfriar(int graus) {
        this.temperatura -= graus;
    }
}




