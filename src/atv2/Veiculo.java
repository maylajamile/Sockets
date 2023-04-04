package atv2;

import java.io.Serializable;

public class Veiculo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nomeCliente;
	private int ano;
	private String marcaVeiculo;
	private double preco;

	public Veiculo(String nomeCliente, int ano, String marcaVeiculo, double preco) {
		this.nomeCliente = nomeCliente;
		this.ano = ano;
		this.marcaVeiculo = marcaVeiculo;
		this.preco = preco;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public int getAno() {
		return ano;
	}

	public String getMarcaVeiculo() {
		return marcaVeiculo;
	}

	public double getPreco() {
		return preco;
	}

	@Override
	public String toString() {
		return nomeCliente + " (" + ano + ") - " + marcaVeiculo + " - R$" + preco + "\n";
	}
}