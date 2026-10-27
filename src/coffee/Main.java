
package coffee;

import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);

    private static Produto[] produtos = new Produto[] {
        new Produto(1, "Cafe expresso", 5.00, "Cafe"),
        new Produto(2, "Cappuccino", 8.00, "Cafe"),
        new Produto(3, "Chocolate quente", 7.00, "Bebida"),
        new Produto(4, "Pao de queijo", 4.00, "Comida"),
        new Produto(5, "Bolo de chocolate", 6.00, "Comida")
    };

    private static Adicional[] adicionais = new Adicional[]{
        new Adicional(1, "Leite extra", 2.00),
        new Adicional(2, "Chantilly", 2.50),
        new Adicional(3, "Caramelo", 1.50)
    };

    private static Pedido pedidoAtual;
    private static int proximoCodigoPedido = 1;

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu();
            opcao = input.nextInt();

            switch (opcao) {
                case 1:
                    mostrarCardapio();
                    break;
                case 2:
                    criarPedido();
                    break;
                case 3:
                    adicionarProdutoAoPedido();
                    break;
                case 4:
                    consultarPedido();
                    break;
                case 5:
                    finalizarPedido();
                    break;
                case 6:
                    cancelarPedido();
                    break;
                case 0:
                    System.out.println("\nObrigado por visitar o Cafe Aurora!");
                    break;
                default:
                    System.out.println("\nOpcao invalida!");
            }

        } while (opcao != 0);

        input.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n===== CAFE AURORA =====");
        System.out.println("1 - Ver cardapio");
        System.out.println("2 - Criar pedido");
        System.out.println("3 - Adicionar produto ao pedido");
        System.out.println("4 - Consultar pedido");
        System.out.println("5 - Finalizar pedido");
        System.out.println("6 - Cancelar pedido");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
    }

    public static void mostrarCardapio() {
        System.out.println("\n===== CARDAPIO =====");

        for (int i = 0; i < produtos.length; i++) {
            System.out.println(produtos[i].exibirDados());
        }

        System.out.println("===== ADICIONAIS =====");

        for (int i = 0; i < adicionais.length; i++) {
            System.out.println(adicionais[i].exibirDados());
        }
    }

    public static void criarPedido() {
        if (pedidoAtual != null && pedidoAtual.getStatus().equals("Aberto")) {
            System.out.println("\nVoce ja possui um pedido aberto!");
            return;
        }

        pedidoAtual = new Pedido(proximoCodigoPedido, 10);
        proximoCodigoPedido++;

        System.out.println("\nPedido criado com sucesso!");
        System.out.println("Codigo: " + pedidoAtual.getCodigo());
    }

    public static void adicionarProdutoAoPedido() {
        if (pedidoAtual == null || !pedidoAtual.getStatus().equals("Aberto")) {
            System.out.println("\nCrie um pedido aberto primeiro!");
            return;
        }

        mostrarCardapio();

        System.out.print("Digite o codigo do produto: ");
        int codigoProduto = input.nextInt();

        Produto produtoEscolhido = null;

        for (int i = 0; i < produtos.length; i++) {
            if (produtos[i].getCodigo() == codigoProduto) {
                produtoEscolhido = produtos[i];
            }
        }

        if (produtoEscolhido == null) {
            System.out.println("\nProduto nao encontrado!");
            return;
        }

        System.out.print("Digite a quantidade: ");
        int quantidade = input.nextInt();

        if (quantidade <= 0) {
            System.out.println("\nQuantidade invalida!");
            return;
        }

        System.out.print("Quantos adicionais deseja? ");
        int quantidadeAdicionais = input.nextInt();

        if (quantidadeAdicionais < 0 || quantidadeAdicionais > adicionais.length) {
            System.out.println("\nQuantidade de adicionais invalida!");
            return;
        }

        ItemPedido item = new ItemPedido(produtoEscolhido, quantidade, quantidadeAdicionais);

        for (int i = 0; i < quantidadeAdicionais; i++) {
            System.out.print("Digite o codigo do adicional: ");
            int codigoAdicional = input.nextInt();

            Adicional adicionalEscolhido = null;

            for (int j = 0; j < adicionais.length; j++) {
                if (adicionais[j].getCodigo() == codigoAdicional) {
                    adicionalEscolhido = adicionais[j];
                }
            }

            if (adicionalEscolhido == null) {
                System.out.println("\nAdicional nao encontrado!");
                return;
            }

            item.getAdicionais()[i] = adicionalEscolhido;
        }

        if (pedidoAtual.adicionarItem(item)) {
            System.out.println("\nProduto adicionado ao pedido!");
        } else {
            System.out.println("\nNao foi possivel adicionar o produto.");
        }
    }

    public static void consultarPedido() {
        if (pedidoAtual == null) {
            System.out.println("\nNenhum pedido foi criado!");
            return;
        }

        System.out.println("\nPedido: " + pedidoAtual.getCodigo());
        System.out.println("Status: " + pedidoAtual.getStatus());
        System.out.println(pedidoAtual.exibirResumo());
    }

    public static void finalizarPedido() {
        if (pedidoAtual == null) {
            System.out.println("\nNenhum pedido foi criado!");
            return;
        }

        if (pedidoAtual.finalizarPedido()) {
            System.out.println("\nPedido finalizado!");
            System.out.println("Total: R$ " + pedidoAtual.calcularTotal());
        } else {
            System.out.println("\nNao foi possivel finalizar o pedido.");
        }
    }

    public static void cancelarPedido() {
        if (pedidoAtual == null) {
            System.out.println("\nNenhum pedido foi criado!");
            return;
        }

        if (pedidoAtual.cancelarPedido()) {
            System.out.println("\nPedido cancelado!");
        } else {
            System.out.println("\nNao foi possivel cancelar o pedido.");
        }
    }
}
