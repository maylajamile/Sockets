package atv1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClienteUDP extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblOperacao;
	private JLabel lblResultado;
	private JTextField txtOperacao;
	private JTextField txtResultado;
	private JButton btnCalcular;
	private JPanel pnlContent;

	public ClienteUDP() {
		pnlContent = new JPanel();
		lblOperacao = new JLabel("Digite a operação no formato [num1 sinal num2]:");
		lblResultado = new JLabel("Resultado da operação:");
		txtOperacao = new JTextField(20);
		txtResultado = new JTextField(30);
		txtResultado.setEnabled(false);

		btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DatagramSocket dSocket = null;
				InetAddress host = null;
				try {
					dSocket = new DatagramSocket();
					host = InetAddress.getByName("localhost");
				} catch (SocketException | UnknownHostException e1) {
					e1.printStackTrace();
				}

				int porta = 12345;

				byte buffer[] = null;

				String operacao = txtOperacao.getText();
				buffer = new byte[65535];
				buffer = operacao.getBytes();

				DatagramPacket request = new DatagramPacket(buffer, buffer.length, host, porta);

				try {
					dSocket.send(request);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				buffer = new byte[65535];

				DatagramPacket receive = new DatagramPacket(buffer, buffer.length);

				try {
					dSocket.receive(receive);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				String resultado = new String(buffer, 0, receive.getLength()).trim();
				txtResultado.setDisabledTextColor(Color.GREEN);
				txtResultado.setText(resultado);

			}
		});

		pnlContent.add(lblOperacao);
		pnlContent.add(txtOperacao);
		pnlContent.add(lblResultado);
		pnlContent.add(txtResultado);
		pnlContent.add(btnCalcular);

		setContentPane(pnlContent);
		setTitle("Calculadora");
		setSize(390, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

		new ClienteUDP();
	}

}
