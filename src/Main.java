import java.util.Scanner;


void main() {

        Scanner scanner = new Scanner(System.in);
        Restaurante restaurante = new Restaurante("Chrono Chef Prime");

        // Clientes iniciais da fila
        restaurante.adicionarCliente(new NobreMedieval("Barão de Aragon", 85));
        restaurante.adicionarCliente(new Ciborgue("UNIT-X88", 70));

        boolean jogoAtivo = true;

        IO.println("==================================================");
        IO.println("          ⏳ BEM-VINDO AO CHRONO CHEF ⏳          ");
        IO.println("==================================================");

        while (jogoAtivo) {
            IO.println("\n--------------------------------------------------");
            // Exibe o status atual do restaurante (Caixa, Reputação, Fila)
            restaurante.exibirStatus();

            IO.println("\n┌────────────────── MENU ──────────────────┐");
            IO.println("│ 1.  Cozinhar e Servir Prato            │");
            IO.println("│ 2.  Passar Turno (Avançar Tempo)        │");
            IO.println("│ 3.  Loja de Melhorias (Upgrades)        │");
            IO.println("│ 4.  Sair do Jogo                       │");
            IO.println("└──────────────────────────────────────────┘");
            IO.println(" Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado

            if (opcao == 1) {
                // Monta o prato e serve o próximo cliente da fila
                Prato prato = restaurante.prepararPratoAutomatico();
                restaurante.servirCliente(prato);
            } else if (opcao == 2) {
                restaurante.passarTurno();
            } else if (opcao == 3) {
                restaurante.abrirLoja(scanner);
            } else if (opcao == 4) {
                jogoAtivo = false;
                System.out.println("\nFechando as portas do Chrono Chef por hoje...");
            } else {
                System.out.println("⚠️️ Opção inválida! Tente novamente.");
            }

            if (restaurante.verificarFimDeJogo()) {
                jogoAtivo = false;
            }
        }

        scanner.close();
        IO.println("\n==================================================");
        IO.println("       Obrigada por jogar Chrono Chef!       ");
        IO.println("==================================================");
    }
