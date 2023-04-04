package atv2;

import java.rmi.Naming;

public class Cliente {
	public static void main(String[] args) {
		try {
			ServidorInterface servidor = (ServidorInterface) Naming.lookup("rmi://localhost/ServidorInterface");

			ClienteInterface cliente = new ClienteImpl(servidor);
			Naming.rebind("//localhost/ClienteInterface", cliente);

			Veiculo veiculo = new Veiculo("Mayla", 2023, "Fiat Uno", 20000.00);
			cliente.adicionarVeiculo(veiculo);
			Veiculo veiculo2 = new Veiculo("Rannier", 2023, "Fiat Uno", 20000.00);
			cliente.adicionarVeiculo(veiculo2);

			System.out.println(cliente.pesquisar("Fiat Uno"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
