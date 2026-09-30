public class Ciborgue extends Cliente {

    public Ciborgue(String nome, int paciencia, int nivelFome) {
        super(nome, paciencia, nivelFome);
    }

    @Override
    public int avaliarPrato(Prato prato) {
        int nota = 0;

        for (Ingrediente ingrediente : prato.getIngredientes()) {
            if (ingrediente instanceof ItemSintetico) {
                nota += 45;
            } else if (ingrediente instanceof VegetalTemporal) {
                VegetalTemporal vegetalTemporal = (VegetalTemporal) ingrediente;
                if (vegetalTemporal.iseRadioativo()) {
                    nota += 25;
                }
            }
        }

        if (nota >= 60) this.satisfeito = true;
        return Math.max(0, nota);
    }
}