import java.util.*;

public class BancoApp {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Digital");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistema Bancário ===");
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Criar Conta Poupança");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir via PIX");
            System.out.println("6. Criar Investimento");
            System.out.println("7. Ver Histórico de Transações");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do titular: ");
                    String nome = scanner.nextLine();
                    Conta cc = new ContaCorrente(nome);
                    banco.adicionarConta(cc);
                    System.out.println("Conta Corrente criada. Número: " + cc.getNumero());
                    break;
                case 2:
                    System.out.print("Nome do titular: ");
                    nome = scanner.nextLine();
                    Conta cp = new ContaPoupanca(nome);
                    banco.adicionarConta(cp);
                    System.out.println("Conta Poupança criada. Número: " + cp.getNumero());
                    break;
                case 3:
                    System.out.print("Número da conta: ");
                    int numero = scanner.nextInt();
                    System.out.print("Valor do depósito: ");
                    double valor = scanner.nextDouble();
                    Conta conta = banco.buscarConta(numero);
                    if (conta != null) {
                        conta.depositar(valor);
                        System.out.println("Depósito realizado!");
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                case 4:
                    System.out.print("Número da conta: ");
                    numero = scanner.nextInt();
                    System.out.print("Valor do saque: ");
                    valor = scanner.nextDouble();
                    conta = banco.buscarConta(numero);
                    if (conta != null) {
                        if (conta.sacar(valor)) {
                            System.out.println("Saque realizado!");
                        } else {
                            System.out.println("Saldo insuficiente.");
                        }
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                case 5:
                    System.out.print("Conta origem: ");
                    int origem = scanner.nextInt();
                    System.out.print("Conta destino (PIX): ");
                    int destino = scanner.nextInt();
                    System.out.print("Valor da transferência: ");
                    valor = scanner.nextDouble();
                    Conta cOrigem = banco.buscarConta(origem);
                    Conta cDestino = banco.buscarConta(destino);
                    if (cOrigem != null && cDestino != null) {
                        if (cOrigem.transferir(cDestino, valor)) {
                            System.out.println("Transferência realizada via PIX!");
                        } else {
                            System.out.println("Saldo insuficiente.");
                        }
                    } else {
                        System.out.println("Conta(s) não encontrada(s).");
                    }
                    break;
                case 6:
                    System.out.print("Número da conta: ");
                    numero = scanner.nextInt();
                    System.out.print("Valor do investimento: ");
                    valor = scanner.nextDouble();
                    conta = banco.buscarConta(numero);
                    if (conta != null) {
                        Investimento inv = new Investimento("CDB", valor, 0.12); // 12% ao ano
                        conta.adicionarInvestimento(inv);
                        System.out.println("Investimento criado!");
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                case 7:
                    System.out.print("Número da conta: ");
                    numero = scanner.nextInt();
                    conta = banco.buscarConta(numero);
                    if (conta != null) {
                        conta.mostrarHistorico();
                    } else {
                        System.out.println("Conta não encontrada.");
                    }
                    break;
                case 8:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}