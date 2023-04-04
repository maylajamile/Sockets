package atv2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClienteInterface extends Remote {
    public List<String> pesquisar(String modelo) throws RemoteException;
    public void adicionarVeiculo(Veiculo veiculo) throws RemoteException;
}
