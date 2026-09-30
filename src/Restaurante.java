import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Restaurante {
    private String nome;
    private double caixa;
    private int reputacao;
    private final List<Cliente> filaClientes;
    private final List<Ingrediente> estoque;
    private final Random random;

    public Restaurante(String nome) {
        this.nome = nome;
        this.caixa = 250.00;
        this.reputacao = 100;
        this.filaClientes = new ArrayList<>();
        this.estoque = new ArrayList<>();
        this.random = new Random();
        inicializarEstoque();
    }

    private void inicializarEstoque() {
        estoque.add(new CarneExotica("Costela de Brontossauro", 35.0, "Pré-História", "Costela"));
        estoque.add(new CarneExotica("Javali Real", 22.0, "Idade Média", "Lombo"));
        estoque.add(new ItemSintetico("Placa Mãe Crocante", 30.0, "Futuro", 220));
        estoque.add(new ItemSintetico("Cabo de Fibra Óptica", 15.0, "Futuro", 110));
        estoque.add(new VegetalTemporal("Cogumelo Luminescente", 18.0, "Era Quântica", true));
    }

    public void adicionarCliente(Cliente cliente) {
        this.filaClientes.add(cliente);
    }

    public void exibirStatus() {
        System.out.println("\n==================================================");
        System.out.println(" RESTAURANTE " + nome.toUpperCase());
        System.out.println(" CAIXA: R$ " + String.format("%.2f", caixa) + " | REPUTAÇÃO: " + reputacao + "%");
        System.out.println("==================================================");
        System.out.println("FILA DE ESPERA:");
        if (filaClientes.isEmpty()) {
            System.out.println(" (Nenhum cliente na fila)");
        } else {
            for (int i = 0; i < filaClientes.size(); i++) {
                Cliente c = filaClientes.get(i);
                System.out.println(" [" + i + "] " + c.getNome() + " (" + c.getClass().getSimpleName() +
                        ") - Paciência: " + c.getPaciencia() + "%");
            }
        }
    }

    public void servirCliente(Prato prato) {
        if (filaClientes.isEmpty()) {
            System.out.println("-> Não há clientes na fila para servir!");
            return;
        }

        int indexCliente = 0; // Atende sempre o primeiro da fila

        Cliente c = filaClientes.get(indexCliente);
        int nota = c.avaliarPrato(prato);

        System.out.println("\n--- SERVINDO " + c.getNome().toUpperCase() + " ---");
        System.out.println("Prato: " + prato.getNome());
        System.out.println("Avaliação do cliente: " + nota + " / 100 pontos.");

        if (c.isSatisfeito()) {
            double pagamento = prato.calcularCustoTotal() * 2.2;
            this.caixa += pagamento;
            this.reputacao = Math.min(100, reputacao + 8);
            System.out.println("STATUS: O cliente adorou! Pagou R$ " + String.format("%.2f", pagamento));
        } else {
            this.reputacao -= 15;
            System.out.println("STATUS: O cliente detestou a refeição! Reputação caiu.");
        }

        filaClientes.remove(indexCliente); // Remove o cliente atendido da fila
    }
    public void passarTurno() {
        System.out.println("\n>>> O TEMPO AVANÇA NO RESTAURANTE <<<");
        List<Cliente> desistentes = new ArrayList<>();

        for (Cliente c : filaClientes) {
            c.reduzirPaciencia(25);
            if (c.getPaciencia() <= 0) {
                desistentes.add(c);
            }
        }

        for (Cliente c : desistentes) {
            System.out.println(" ALERT: " + c.getNome() + " perdeu a paciência e foi embora!");
            this.reputacao -= 20;
            filaClientes.remove(c);
        }

        if (random.nextInt(100) < 30) {
            System.out.println(" ANOMALIA TEMPORAL DETECTADA! Paciência dos clientes reduzida!");
            for (Cliente c : filaClientes) {
                c.reduzirPaciencia(10);
            }
        }
    }

    public boolean verificarFimDeJogo() {
        if (this.reputacao <= 0) {
            System.out.println("\n==================================================");
            System.out.println(" GAME OVER! Sua reputação zerou e o restaurante fechou.");
            System.out.println("==================================================");
            return true;
        }
        return false;
    }

    public List<Ingrediente> getEstoque() {
        return estoque;
    }

    public Prato prepararPratoAutomatico() {
        Prato prato = new Prato("Prato da Casa");

        // Adiciona até 2 ingredientes do estoque ao prato (se houver estoque)
        if (!estoque.isEmpty()) {
            prato.adicionarIngrediente(estoque.remove(0));
        }
        if (!estoque.isEmpty()) {
            prato.adicionarIngrediente(estoque.remove(0));
        }

        // Cozinha/Aquece os ingredientes do prato
        for (Ingrediente ing : prato.getIngredientes()) {
            ing.aquecer(100);
            ing.temperar(5);
        }

        return prato;
    }
}