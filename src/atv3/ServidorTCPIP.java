package atv3;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServidorTCPIP extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextArea texto;
	private JLabel lblServidor;
	private JPanel pnlContent;
	
	public ServidorTCPIP() {
		pnlContent = new JPanel();
		texto = new JTextArea(27, 30);
		texto.setEditable(false);
		lblServidor = new JLabel("Histórico");
		JScrollPane scroll = new JScrollPane(texto);
		texto.setLineWrap(true);
		pnlContent.add(lblServidor);
		pnlContent.add(scroll);

		setTitle("Servidor");
		setContentPane(pnlContent);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		ServidorTCPIP tela = new ServidorTCPIP();
		
        try{
        	int porta = 12345;
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor ativo na porta: " + porta);
			
            while (true) {
				Socket cliente = servidor.accept();
				new Conexao(cliente, tela.texto).start();
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
