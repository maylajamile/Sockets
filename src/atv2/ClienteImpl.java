package atv2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClienteImpl extends UnicastRemoteObject implements ClienteInterface {
    private static final long serialVersionUID = 1L;
	private ServidorInterface servidor;

    public ClienteImpl(ServidorInterface servidor) throws RemoteException {
        super();
        this.servidor = servidor;
    }
    
	@Override
	public List<String> pesquisar(String modelo) throws RemoteException {
		return this.servidor.pesquisar(modelo);
	}

	@Override
	public void adicionarVeiculo(Veiculo veiculo) throws RemoteException {
		this.servidor.adicionarVeiculo(veiculo);
	}

}