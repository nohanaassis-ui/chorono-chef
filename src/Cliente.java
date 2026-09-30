public  abstract class Cliente {
    protected String nome;
    protected int paciencia;
    protected int nivelFome;
    protected boolean satisfeito;

    public Cliente(String nome, int paciencia, int nivelFome) {
        this.nome = nome;
        this.paciencia = paciencia;
        this.nivelFome = nivelFome;
        this.satisfeito = false;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", paciencia=" + paciencia +
                ", nivelFome=" + nivelFome +
                ", satisfeito=" + satisfeito +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public int getPaciencia() {
        return paciencia;
    }

    public int getNivelFome() {
        return nivelFome;
    }

    public boolean isSatisfeito() {
        return satisfeito;
    }


    public void reduzirPaciencia(int qtd){
        this.paciencia -= qtd;
        if(this.paciencia < 0) this.paciencia = 0;
    }

    public  abstract int avaliarPrato(Prato prato);
}
