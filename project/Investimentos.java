public class Investimento {
    private String tipo;
    private double valor;
    private double taxaJuros;

    public Investimento(String tipo, double valor, double taxaJuros) {
        this.tipo = tipo;
        this.valor = valor;
        this.taxaJuros = taxaJuros;
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public double getTaxaJuros() { return taxaJuros; }

    @Override
    public String toString() {
        return tipo + " | Valor: R$" + valor + " | Juros: " + (taxaJuros * 100) + "% a.a.";
    }
}