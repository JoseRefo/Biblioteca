import java.util.Scanner;


public class Biblioteca {

    // ---------- Configuracao das estruturas de dados ----------
    static final int MAX_LIVROS = 200;
    static final int MAX_UTILIZADORES = 200;
    static final int MAX_EMPRESTIMOS = 2000;

    // Catalogo de livros (vetores paralelos)
    static int[] idLivro = new int[MAX_LIVROS];
    static String[] tituloLivro = new String[MAX_LIVROS];
    static String[] autorLivro = new String[MAX_LIVROS];
    static int[] anoLivro = new int[MAX_LIVROS];
    static int[] qtdDisponivel = new int[MAX_LIVROS];
    static int[] qtdTotal = new int[MAX_LIVROS];
    static int totalLivros = 0;

    // Utilizadores registados
    static int[] idUtilizador = new int[MAX_UTILIZADORES];
    static String[] nomeUtilizador = new String[MAX_UTILIZADORES];
    static int totalUtilizadores = 0;

    // Matriz de emprestimos: cada linha representa um emprestimo
    // colunas -> [0]=idLivro, [1]=idUtilizador, [2]=estado (1=emprestado, 0=devolvido)
    static int[][] emprestimos = new int[MAX_EMPRESTIMOS][3];
    static int totalEmprestimos = 0;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            switch (opcao) {
                case 1:
                    registarLivro();
                    break;
                case 2:
                    registarUtilizador();
                    break;
                case 3:
                    listarCatalogo();
                    break;
                case 4:
                    pesquisarLivro();
                    break;
                case 5:
                    efetuarEmprestimo();
                    break;
                case 6:
                    efetuarDevolucao();
                    break;
                case 7:
                    mostrarEstatisticas();
                    break;
                case 0:
                    System.out.println("\nA encerrar o sistema. Ate breve!");
                    break;
                default:
                    System.out.println("\n>> Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }

    // ==========================================================
    //                          MENU
    // ==========================================================
    static void mostrarMenu() {
        System.out.println("\n===================================================");
        System.out.println("   SISTEMA DE GESTAO - BIBLIOTECA MUNICIPAL");
        System.out.println("===================================================");
        System.out.println("1. Registar novo livro");
        System.out.println("2. Registar novo utilizador");
        System.out.println("3. Listar catalogo completo");
        System.out.println("4. Pesquisar livro (por titulo ou autor)");
        System.out.println("5. Efetuar emprestimo");
        System.out.println("6. Efetuar devolucao");
        System.out.println("7. Estatisticas");
        System.out.println("0. Sair");
        System.out.println("===================================================");
    }

    // ==========================================================
    //                   REGISTO DE LIVROS
    // ==========================================================
    static void registarLivro() {
        System.out.println("\n--- Registo de Novo Livro ---");

        if (totalLivros >= MAX_LIVROS) {
            System.out.println(">> Catalogo cheio. Nao e possivel registar mais livros.");
            return;
        }

        int id = lerInteiro("ID unico do livro: ");
        if (encontrarIndiceLivro(id) != -1) {
            System.out.println(">> Ja existe um livro registado com esse ID.");
            return;
        }

        String titulo = lerTexto("Titulo: ");
        String autor = lerTexto("Autor: ");
        int ano = lerInteiro("Ano de publicacao: ");
        int quantidade = lerInteiro("Quantidade disponivel: ");

        idLivro[totalLivros] = id;
        tituloLivro[totalLivros] = titulo;
        autorLivro[totalLivros] = autor;
        anoLivro[totalLivros] = ano;
        qtdDisponivel[totalLivros] = quantidade;
        qtdTotal[totalLivros] = quantidade;
        totalLivros++;

        System.out.println(">> Livro registado com sucesso!");
    }

    // ==========================================================
    //                REGISTO DE UTILIZADORES
    // ==========================================================
    static void registarUtilizador() {
        System.out.println("\n--- Registo de Novo Utilizador ---");

        if (totalUtilizadores >= MAX_UTILIZADORES) {
            System.out.println(">> Limite de utilizadores atingido.");
            return;
        }

        int id = lerInteiro("ID unico do utilizador: ");
        if (encontrarIndiceUtilizador(id) != -1) {
            System.out.println(">> Ja existe um utilizador registado com esse ID.");
            return;
        }

        String nome = lerTexto("Nome do utilizador: ");

        idUtilizador[totalUtilizadores] = id;
        nomeUtilizador[totalUtilizadores] = nome;
        totalUtilizadores++;

        System.out.println(">> Utilizador registado com sucesso!");
    }

    // ==========================================================
    //                CONSULTA DO CATALOGO
    // ==========================================================
    static void listarCatalogo() {
        System.out.println("\n--- Catalogo de Livros ---");

        if (totalLivros == 0) {
            System.out.println(">> Nao existem livros registados.");
            return;
        }

        imprimirCabecalhoCatalogo();
        for (int i = 0; i < totalLivros; i++) {
            imprimirLinhaLivro(i);
        }
    }

    static void pesquisarLivro() {
        System.out.println("\n--- Pesquisa de Livros ---");
        System.out.println("1. Pesquisar por titulo");
        System.out.println("2. Pesquisar por autor");
        int opcao = lerInteiro("Escolha uma opcao: ");

        if (opcao != 1 && opcao != 2) {
            System.out.println(">> Opcao invalida.");
            return;
        }

        String termo = lerTexto("Introduza o termo de pesquisa: ").toLowerCase();
        boolean encontrou = false;

        imprimirCabecalhoCatalogo();
        for (int i = 0; i < totalLivros; i++) {
            String campo = (opcao == 1) ? tituloLivro[i] : autorLivro[i];
            if (campo.toLowerCase().contains(termo)) {
                imprimirLinhaLivro(i);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println(">> Nenhum livro encontrado com esse criterio.");
        }
    }

    static void imprimirCabecalhoCatalogo() {
        System.out.printf("%-5s %-30s %-20s %-6s %-12s%n",
                "ID", "Titulo", "Autor", "Ano", "Disponivel");
        System.out.println("-------------------------------------------------------------------");
    }

    static void imprimirLinhaLivro(int i) {
        System.out.printf("%-5d %-30s %-20s %-6d %-12s%n",
                idLivro[i], tituloLivro[i], autorLivro[i], anoLivro[i],
                qtdDisponivel[i] + "/" + qtdTotal[i]);
    }

    // ==========================================================
    //             GESTAO DE EMPRESTIMOS E DEVOLUCOES
    // ==========================================================
    static void efetuarEmprestimo() {
        System.out.println("\n--- Efetuar Emprestimo ---");

        if (totalEmprestimos >= MAX_EMPRESTIMOS) {
            System.out.println(">> Limite de emprestimos registados atingido.");
            return;
        }

        int idL = lerInteiro("ID do livro: ");
        int idxLivro = encontrarIndiceLivro(idL);
        if (idxLivro == -1) {
            System.out.println(">> Livro nao encontrado.");
            return;
        }

        int idU = lerInteiro("ID do utilizador: ");
        int idxUtil = encontrarIndiceUtilizador(idU);
        if (idxUtil == -1) {
            System.out.println(">> Utilizador nao encontrado. Registe o utilizador primeiro.");
            return;
        }

        if (qtdDisponivel[idxLivro] <= 0) {
            System.out.println(">> Nao ha exemplares disponiveis para emprestimo deste livro.");
            return;
        }

        // Regista a linha na matriz de emprestimos
        emprestimos[totalEmprestimos][0] = idL;
        emprestimos[totalEmprestimos][1] = idU;
        emprestimos[totalEmprestimos][2] = 1; // 1 = ativo (nao devolvido)
        totalEmprestimos++;

        qtdDisponivel[idxLivro]--;

        System.out.println(">> Emprestimo registado com sucesso!");
        System.out.println("   Livro: " + tituloLivro[idxLivro] + " -> Utilizador: " + nomeUtilizador[idxUtil]);
    }

    static void efetuarDevolucao() {
        System.out.println("\n--- Efetuar Devolucao ---");

        int idL = lerInteiro("ID do livro: ");
        int idxLivro = encontrarIndiceLivro(idL);
        if (idxLivro == -1) {
            System.out.println(">> Livro nao encontrado.");
            return;
        }

        int idU = lerInteiro("ID do utilizador: ");

        // Procura o emprestimo ativo mais recente para este par livro/utilizador
        int linhaEncontrada = -1;
        for (int i = totalEmprestimos - 1; i >= 0; i--) {
            if (emprestimos[i][0] == idL && emprestimos[i][1] == idU && emprestimos[i][2] == 1) {
                linhaEncontrada = i;
                break;
            }
        }

        if (linhaEncontrada == -1) {
            System.out.println(">> Nao foi encontrado nenhum emprestimo ativo para esse livro/utilizador.");
            return;
        }

        emprestimos[linhaEncontrada][2] = 0; // marca como devolvido
        qtdDisponivel[idxLivro]++;

        System.out.println(">> Devolucao registada com sucesso!");
        System.out.println("   Livro: " + tituloLivro[idxLivro] + " agora tem " + qtdDisponivel[idxLivro] + " exemplar(es) disponivel(eis).");
    }

    // ==========================================================
    //                       ESTATISTICAS
    // ==========================================================
    static void mostrarEstatisticas() {
        System.out.println("\n--- Estatisticas da Biblioteca ---");

        if (totalLivros == 0) {
            System.out.println(">> Nao existem livros registados.");
            return;
        }

        // Conta quantas vezes cada livro foi requisitado (historico completo)
        int[] contagemPorLivro = new int[totalLivros];
        for (int i = 0; i < totalEmprestimos; i++) {
            int idL = emprestimos[i][0];
            int idx = encontrarIndiceLivro(idL);
            if (idx != -1) {
                contagemPorLivro[idx]++;
            }
        }

        // Determina o livro mais emprestado
        int indiceMax = -1;
        int maxEmprestimos = 0;
        for (int i = 0; i < totalLivros; i++) {
            if (contagemPorLivro[i] > maxEmprestimos) {
                maxEmprestimos = contagemPorLivro[i];
                indiceMax = i;
            }
        }

        System.out.println("Total de livros no catalogo: " + totalLivros);
        System.out.println("Total de utilizadores registados: " + totalUtilizadores);
        System.out.println("Total de emprestimos realizados (historico): " + totalEmprestimos);

        long emprestimosAtivos = 0;
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i][2] == 1) {
                emprestimosAtivos++;
            }
        }
        System.out.println("Emprestimos atualmente em curso: " + emprestimosAtivos);

        if (indiceMax != -1) {
            System.out.println("\nLivro mais requisitado: \"" + tituloLivro[indiceMax] + "\" ("
                    + autorLivro[indiceMax] + ") - " + maxEmprestimos + " emprestimo(s).");
        } else {
            System.out.println("\nAinda nao foi efetuado nenhum emprestimo.");
        }
    }

    // ==========================================================
    //                   FUNCOES AUXILIARES
    // ==========================================================
    static int encontrarIndiceLivro(int id) {
        for (int i = 0; i < totalLivros; i++) {
            if (idLivro[i] == id) {
                return i;
            }
        }
        return -1;
    }

    static int encontrarIndiceUtilizador(int id) {
        for (int i = 0; i < totalUtilizadores; i++) {
            if (idUtilizador[i] == id) {
                return i;
            }
        }
        return -1;
    }

    static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = sc.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println(">> Por favor, introduza um numero valido.");
            }
        }
    }

    static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }
}
