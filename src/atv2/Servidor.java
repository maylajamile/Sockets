package atv2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
	public static void main(String[] args) {
        try {
            ServidorInterface server = new ServidorImpl();

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ServidorInterface", server);

            System.out.println("Servidor RMI iniciado.");
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor RMI:");
            e.printStackTrace();
        }
    }
}
