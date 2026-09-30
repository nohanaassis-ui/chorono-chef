public abstract class Cozinhavel {
    public abstract void aquecer(int graus);

    public abstract void temperar(int nivel);

    public abstract boolean estaPronto();

    public interface ICozinhavel {
        void aquecer(int graus);
        void temperar(int nivel);
        boolean estaPronto();
    }
}
