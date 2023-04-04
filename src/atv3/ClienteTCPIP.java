package atv3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

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

	public ClienteTCPIP() {
		pnlContent = new JPanel();
		texto = new JTextArea(22, 30);
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

					String recebido = txtMsg.getText();

					if (recebido.equals("#QUIT")) {
						socket.close();
						dispose();
					} else if (recebido.equals("#USERS")) {
						// TODO - implementar lista de usuarios
					} else {
						bufferedWriter.write(txtNome.getText() + " > " + recebido + "\n");
						texto.append(txtNome.getText() + " > " + recebido + "\n");
						txtMsg.setText("");
						bufferedWriter.flush();
					}

				} catch (IOException ex) {
					ex.printStackTrace();
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
		setResizable(true);
		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		ClienteTCPIP clienteTCPIP = new ClienteTCPIP();
		clienteTCPIP.conectar();

	}

	private void conectar() {
		try {
			socket = new Socket("localhost", 12345);
			outputStream = socket.getOutputStream();
			writer = new OutputStreamWriter(outputStream);
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(txtMsg.getText());
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
