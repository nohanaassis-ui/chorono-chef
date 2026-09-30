public class Dinossauro  extends Cliente{

    public Dinossauro(String nome, int paciencia, int nivelFome) {
        super(nome, paciencia, nivelFome);
    }

    @Override
    public int avaliarPrato(Prato prato) {
        int nota = 0;
        for (Ingrediente ingrediente : prato.getIngredientes()){
            if (ingrediente instanceof CarneExotica){
                nota += 40;
                if (ingrediente.getTemperatura() < 40) nota += 10;
            } else if (ingrediente != null){
                nota -= 30;
            }
        }
        if (prato.getIngredientes().size() >= nivelFome) nota += 20;
        if (nota >= 60) this.satisfeito = true;
        return Math.max(0 , nota);
    }
}
