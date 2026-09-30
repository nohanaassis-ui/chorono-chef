import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante("Chrono Chef Prime");

        // Adiciona alguns clientes iniciais na fila
        restaurante.adicionarCliente(new NobreMedieval("Barão de Aragon", 85, 1));
        restaurante.adicionarCliente(new Ciborgue("UNIT-X88", 70, 2));

        boolean jogoAtivo = true;

        System.out.println("=== BEM-VINDO AO CHRONO CHEF ===");

        while (jogoAtivo) {
            System.out.println("\n----------------------------------");
            // Exibe o status atual do restaurante
            restaurante.exibirStatus();

            System.out.println("\n--- MENU ---");
            System.out.println("1. Cozinhar e Servir Prato");
            System.out.println("2. Passar Turno (Avançar Tempo)");
            System.out.println("3. Sair do Jogo");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado

            if (opcao == 1) {
                // Monta o prato e serve o próximo cliente da fila
                Prato prato = restaurante.prepararPratoAutomatico();
                restaurante.servirCliente(prato);
            } else if (opcao == 2) {
                restaurante.passarTurno();
            } else if (opcao == 3) {
                jogoAtivo = false;
                System.out.println("\nFechando as portas do Chrono Chef por hoje...");
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }

            // Verifica se perdeu o jogo (ex: reputação zerada)
            if (restaurante.verificarFimDeJogo()) {
                jogoAtivo = false;
            }
        }

        scanner.close();
        System.out.println("\nObrigado por jogar Chrono Chef!");
    }
}