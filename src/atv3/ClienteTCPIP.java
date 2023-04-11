package atv3;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClienteTCPIP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea texto;
	private JPanel pnlContent;
	private JLabel lblCliente;
	private JTextField txtMsg;
	private JLabel lblMsg;
	private JTextField txtNome;
	private JLabel lblNome;
	private JButton btnEnviar;
	private OutputStream outputStream;
	private Writer writer;
	private BufferedWriter bufferedWriter;
	private Socket socket;
	private String mensagem = null;

	public ClienteTCPIP() {
		pnlContent = new JPanel();
		texto = new JTextArea(19, 25);
		texto.setFont(new Font("Arial", Font.PLAIN, 16));
		texto.setEditable(false);
		lblCliente = new JLabel("Histórico");
		JScrollPane scroll = new JScrollPane(texto);
		texto.setLineWrap(true);

		txtMsg = new JTextField(22);
		lblMsg = new JLabel("Mensagem:");
		txtNome = new JTextField(24);
		lblNome = new JLabel("Usuário:");

		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					Usuario usuario = new Usuario(txtNome.getText());
					String mensagem = txtMsg.getText();
					String nome = txtNome.getText();

					if (mensagem.equalsIgnoreCase("#QUIT")) {
						bufferedWriter.write("[Cliente] - Usuário " + txtNome.getText() + " se desconectou do chat.");
						usuario.removerUsuario(txtNome.getText());
						bufferedWriter.close();
						writer.close();
						outputStream.close();
						socket.close();
						System.exit(0);
					} else if (mensagem.equalsIgnoreCase("#USERS")) {
						texto.append("[Cliente] - Os usuários conectados neste chat são: \n" + usuario.listarUsuarios()
								+ "\n");
					} else {
						bufferedWriter.write(nome + " > " + mensagem + "\n");
						texto.append(nome + " > " + mensagem + "\n");
					}

					bufferedWriter.flush();
					txtMsg.setText("");

				} catch (Exception ex) {
					System.out.println("[Cliente] - Erro ao enviar mensagem. " + ex.getMessage());
				}
			}
		});

		pnlContent.add(lblCliente);
		pnlContent.add(scroll);
		pnlContent.add(lblNome);
		pnlContent.add(txtNome);
		pnlContent.add(lblMsg);
		pnlContent.add(txtMsg);
		pnlContent.add(btnEnviar);

		setTitle("Chat");
		setContentPane(pnlContent);
		setLocationRelativeTo(null);
		setResizable(false);
		setPreferredSize(new Dimension(400, 490));
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		ClienteTCPIP cliente = new ClienteTCPIP();
		cliente.conectar();
		cliente.receberMensagens();
	}

	private void conectar() {
		try {
			socket = new Socket("localhost", 12345);
			outputStream = socket.getOutputStream();
			writer = new OutputStreamWriter(outputStream);
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(mensagem + "\n");
			bufferedWriter.flush();

		} catch (Exception e) {
			texto.append("[Cliente] - Não foi possível fazer conexão com o servidor. \n");
		}

	}

	private void receberMensagens() {
		try {
			Scanner scanner = new Scanner(socket.getInputStream());

			while (!"#QUIT".equalsIgnoreCase(mensagem)) {
				if (scanner.hasNextLine()) {
					mensagem = scanner.nextLine();
					if (mensagem != null) {
						texto.append(mensagem + "\n");
					}
				}
			}
		} catch (Exception e) {
			texto.append("[Cliente] - Não foi possível receber as mensagens do chat. \n");
		}
	}
}
