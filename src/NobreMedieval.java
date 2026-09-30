public class NobreMedieval extends Cliente{

    public NobreMedieval(String nome, int paciencia, int nivelFome) {
        super(nome, paciencia, nivelFome);
    }

    @Override
    public int avaliarPrato(Prato prato) {
        int nota = 0;
        for (Ingrediente ingrediente : prato.getIngredientes()){
            if (ingrediente.getTemperatura() >=50) nota += 25;
            if (ingrediente.getPicancia() >= 2) nota += 20;
            if (ingrediente instanceof ItemSintetico) nota -= 40;
        }
        if (nota >= 60 ) this.satisfeito = true;
        return Math.max(0, nota);
    }
}
