public class Dinossauro extends Cliente {

    public Dinossauro(String nome, int paciencia, int nivelFome) {
        super(nome, paciencia);
        this.nivelFome = nivelFome; // Adicionado: inicializa o nivelFome herdado de Cliente!
    }

    @Override
    public int avaliarPrato(Prato prato) {
        int nota = 0;

        for (Ingrediente ingrediente : prato.getIngredientes()) {
            if (ingrediente instanceof CarneExotica) {
                nota += 40;
                // Dinossauros gostam de carne crua/fria!
                if (ingrediente.getTemperatura() < 40) {
                    nota += 10;
                }
            } else {
                // Se não for CarneExotica (ex: vegetais ou sintéticos), o dinossauro desgosta
                nota -= 30;
            }
        }

        // Bônus pela quantidade de ingredientes em relação ao nível de fome
        if (prato.getIngredientes().size() >= this.nivelFome) {
            nota += 20;
        }

        if (nota >= 60) {
            this.satisfeito = true;
        }

        return Math.max(0, nota);
    }
}
