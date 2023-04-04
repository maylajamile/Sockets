package atv2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServidorImpl extends UnicastRemoteObject implements ServidorInterface {
    private static final long serialVersionUID = 1L;
	private List<Veiculo> veiculos;

    public ServidorImpl() throws RemoteException {
        super();
        this.veiculos = new ArrayList<Veiculo>();
    }

	@Override
	public List<String> pesquisar(String modelo) throws RemoteException {
		List<String> encontrados = new ArrayList<>();
		for (Veiculo veiculo : veiculos) {
			if (veiculo.getMarcaVeiculo().equals(modelo)) {
				encontrados.add(veiculo.toString());
			}
		}
		return encontrados;
	}

	@Override
	public void adicionarVeiculo(Veiculo veiculo) throws RemoteException {
		veiculos.add(veiculo);		
	}
}
