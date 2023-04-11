package atv3;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
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
	private ServerSocket servidor;

	public ServidorTCPIP() {
		pnlContent = new JPanel();
		texto = new JTextArea(22, 25);
		texto.setEditable(false);
		texto.setLineWrap(true);
		texto.setFont(new Font("Arial", Font.PLAIN, 16));
		lblServidor = new JLabel("Histórico");
		JScrollPane scroll = new JScrollPane(texto);

		JButton btnDesligar = new JButton("Desligar");
		btnDesligar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					servidor.close();
					File arquivo = new File("usuarios.csv");
					if (arquivo.exists()) {
						arquivo.delete();
					}
					dispose();
				} catch (Exception ex) {
					System.out.println("[Servidor] - Não foi possível desligar o servidor. " + ex.getMessage());
				}
			}
		});

		pnlContent.add(lblServidor);
		pnlContent.add(scroll);
		pnlContent.add(btnDesligar);

		setTitle("Servidor");
		setContentPane(pnlContent);
		setLocationRelativeTo(null);
		setResizable(false);
		setPreferredSize(new Dimension(400, 490));
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		ServidorTCPIP servidor = new ServidorTCPIP();
		servidor.iniciar();

	}

	private void iniciar() {
		try {
			int porta = 12345;
			servidor = new ServerSocket(12345);
			texto.setText("[Servidor] - Servidor ativo na porta: " + porta);

			while (true) {
				Socket cliente = servidor.accept();
				new Conexao(cliente).start();
			}
		} catch (Exception e) {
			System.out.println("[Servidor] - Servidor desligado.");
		}
	}
}
