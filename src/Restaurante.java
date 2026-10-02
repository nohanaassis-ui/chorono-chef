import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Restaurante {
    private final String nome;
    private double caixa;
    private int reputacao;
    private final List<Cliente> filaClientes;
    private final List<Ingrediente> estoque;
    private final Random random;
    private boolean temFogaoQuantico = false;
    private boolean temDecoracaoEpoca = false;

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
        estoque.clear();
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
        IO.println("\n==================================================");
        IO.println(" RESTAURANTE " + nome.toUpperCase());
        IO.println(" CAIXA: R$ " + String.format("%.2f", caixa) + " | REPUTAÇÃO: " + reputacao + "%");
        IO.println("==================================================");
        IO.println("FILA DE ESPERA:");
        if (filaClientes.isEmpty()) {
            IO.println(" (Nenhum cliente na fila)");
        } else {
            for (int i = 0; i < filaClientes.size(); i++) {
                Cliente c = filaClientes.get(i);
                IO.println(" [" + i + "] " + c.getNome() + " (" + c.getClass().getSimpleName() +
                        ") - Paciência: " + c.getPaciencia() + "%");
            }
        }
    }

    public void servirCliente(Prato prato) {
        if (filaClientes.isEmpty()) {
            IO.println("-> Não há clientes na fila para servir!");
            return;
        }

        int indexCliente = 0; // Atende sempre o primeiro da fila
        Cliente c = filaClientes.get(indexCliente);
        int nota = c.avaliarPrato(prato);

        IO.println("\n--- SERVINDO " + c.getNome().toUpperCase() + " ---");
        IO.println("Prato: " + prato.getNome());
        IO.println("Avaliação do cliente: " + nota + " / 100 pontos.");

        double pagamento = nota * 0.5;

        if (c.isSatisfeito()) {
            this.caixa += pagamento;
            this.reputacao = Math.min(100, reputacao + 8);
            IO.println("STATUS: O cliente adorou! Pagou R$ " + String.format("%.2f", pagamento));
        } else {
            this.reputacao -= 15;
            IO.println("STATUS: O cliente detestou a refeição! Reputação caiu.");
        }

        filaClientes.remove(indexCliente); // Remove o cliente atendido
    }

    public void passarTurno() {
        IO.println("\n>>> O TEMPO AVANÇA NO RESTAURANTE <<<");
        List<Cliente> desistentes = new ArrayList<>();

        // 1. Reduz paciência dos clientes considerando a melhoria de Decoração
        int perdaPaciencia = temDecoracaoEpoca ? 15 : 25;
        for (Cliente c : filaClientes) {
            c.reduzirPaciencia(perdaPaciencia);
            if (c.getPaciencia() <= 0) {
                desistentes.add(c);
            }
        }

        // 2. Remove clientes que desistiram
        for (Cliente c : desistentes) {
            IO.println("⚠ ALERTA: " + c.getNome() + " perdeu a paciência e foi embora!");
            this.reputacao -= 20;
            filaClientes.remove(c);
        }

        // 3. Eventos Temporais Aleatórios
        int eventoSorteado = random.nextInt(100);

        if (eventoSorteado < 25) {
            IO.println("\n❄ EVENTO TEMPORAL: [FENDA TEMPORAL DETECTADA]!");
            IO.println("Uma massa de ar congelante do espaço-tempo resfriou os ingredientes do estoque em -20°C!");
            for (Ingrediente ing : estoque) {
                ing.resfriar(20);
            }
        } else if (eventoSorteado >= 25 && eventoSorteado < 50) {
            IO.println("\n EVENTO TEMPORAL: [DIA DE FESTA MULTIVERSAL]!");
            IO.println("Música festiva ecoa do portal! Todos os clientes ganharam +15% de paciência!");
            for (Cliente c : filaClientes) {
                c.aumentarPaciencia(15);
            }
        }

        // 4. Reabastecimento automático do estoque
        if (estoque.size() <= 2) {
            IO.println("\n[ESTOQUE REPOSTO] O portal temporal reabasteceu a cozinha!");
            inicializarEstoque();
        }
    }

    public boolean verificarFimDeJogo() {
        if (this.reputacao <= 0) {
            IO.println("\n==========================================");
            IO.println(" GAME OVER! Sua reputação zerou e o restaurante fechou.");
            IO.println("==========================================");
            return true;
        }
        return false;
    }

    public List<Ingrediente> getEstoque() {
        return this.estoque;
    }

    public Prato prepararPratoAutomatico() {
        if (estoque.isEmpty()) {
            IO.println("[ESTOQUE VAZIO] O portal temporal reabasteceu os ingredientes!");
            inicializarEstoque();
        }

        Prato prato = new Prato("Prato da Casa");

        if (!estoque.isEmpty()) {
            prato.adicionarIngrediente(estoque.remove(0));
        }
        if (!estoque.isEmpty()) {
            prato.adicionarIngrediente(estoque.remove(0));
        }

        int temperaturaBonus = temFogaoQuantico ? 150 : 100;
        for (Ingrediente ing : prato.getIngredientes()) {
            ing.aquecer(temperaturaBonus);
            ing.temperar(5);
        }

        return prato;
    }

    public void abrirLoja(Scanner scanner) {
        IO.println("\n=== 🛒 LOJA DE MELHORIAS CHRONO ===");
        IO.println("Seu Caixa Atual: R$ " + String.format("%.2f", this.caixa));
        IO.println("1. Fogão Quântico (R$ 100,00) - " + (temFogaoQuantico ? "[JÁ COMPRADO]" : "Aquece pratos com bônus de +150°C"));
        IO.println("2. Decoração de Época (R$ 150,00) - " + (temDecoracaoEpoca ? "[JÁ COMPRADA]" : "Clientes perdem menos paciência"));
        IO.println("3. Voltar ao Menu Principal");
        IO.println("Escolha uma opção de compra: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            if (temFogaoQuantico) {
                IO.println(" Você já possui o Fogão Quântico!");
            } else if (this.caixa >= 100.0) {
                this.caixa -= 100.0;
                this.temFogaoQuantico = true;
                IO.println(" PARABÉNS! Você comprou o Fogão Quântico!");
            } else {
                IO.println(" Saldo insuficiente no caixa!");
            }
        } else if (opcao == 2) {
            if (temDecoracaoEpoca) {
                IO.println(" Você já possui a Decoração de Época!");
            } else if (this.caixa >= 150.0) {
                this.caixa -= 150.0;
                this.temDecoracaoEpoca = true;
                IO.println("PARABÉNS! Você comprou a Decoração de Época!");
            } else {
                IO.println(" Saldo insuficiente no caixa!");
            }
        } else if (opcao == 3) {
            IO.println("Saindo da loja...");
        } else {
            IO.println("Opção inválida!");
        }
    }
}