import java.util.*;

public abstract class Conta {
    private static int contador = 1000;
    private int numero;
    private String titular;
    private double saldo;
    private List<String> historico;
    private List<Investimento> investimentos;

    public Conta(String titular) {
        this.titular = titular;
        this.numero = contador++;
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
        this.investimentos = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            historico.add("Depósito de R$" + valor);
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            historico.add("Saque de R$" + valor);
            return true;
        }
        return false;
    }

    public boolean transferir(Conta destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            historico.add("Transferência PIX para conta " + destino.getNumero() + " de R$" + valor);
            destino.historico.add("Recebido via PIX da conta " + this.getNumero() + " de R$" + valor);
            return true;
        }
        return false;
    }

    public void adicionarInvestimento(Investimento inv) {
        if (saldo >= inv.getValor()) {
            saldo -= inv.getValor();
            investimentos.add(inv);
            historico.add("Investimento criado: " + inv.getTipo() + " de R$" + inv.getValor());
        }
    }

    public void mostrarHistorico() {
        System.out.println("Histórico da Conta " + numero + ":");
        for (String h : historico) {
            System.out.println("- " + h);
        }
        if (!investimentos.isEmpty()) {
            System.out.println("Investimentos:");
            for (Investimento i : investimentos) {
                System.out.println("  * " + i);
            }
        }
    }
}