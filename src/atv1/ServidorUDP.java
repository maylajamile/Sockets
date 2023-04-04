package atv1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class ServidorUDP {

	public static void main(String[] args) {
		System.out.println("Servidor Iniciado...");
		int porta = 12345;
		DatagramSocket dSocket = null;
		try {
			dSocket = new DatagramSocket(porta);
		} catch (SocketException e) {
			e.printStackTrace();
		}

		byte[] buffer = null;

		DatagramPacket receive = null;
		DatagramPacket request = null;

		while (true) {
			buffer = new byte[65535];

			receive = new DatagramPacket(buffer, buffer.length);

			try {
				dSocket.receive(receive);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String operacao = new String(buffer, 0, buffer.length);

			operacao = operacao.trim();

			System.out.println("Operação: " + operacao);

			StringTokenizer sTokenizer = new StringTokenizer(operacao);

			int num1 = Integer.parseInt(sTokenizer.nextToken());
			String sinal = sTokenizer.nextToken();
			int num2 = Integer.parseInt(sTokenizer.nextToken());

			int resultado = 0;
			switch (sinal) {
			case "+":
				resultado = num1 + num2;
				break;
			case "-":
				resultado = num1 - num2;
				break;
			case "*":
				resultado = num1 * num2;
				break;
			case "/":
				resultado = num1 / num2;
				break;
			}

			System.out.println("Enviando resultado...");
			String resultadoOperacao = Integer.toString(resultado);

			buffer = resultadoOperacao.getBytes();

			int portaCliente = receive.getPort();

			try {
				request = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), portaCliente);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			try {
				dSocket.send(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
