package atv3;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

public class Conexao extends Thread {
	private InputStream entrada;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	private JTextArea tela;

	public Conexao(Socket cliente, JTextArea texto) {
		this.tela = texto;
		try {

			entrada = cliente.getInputStream();
			inputStreamReader = new InputStreamReader(entrada);
			bufferedReader = new BufferedReader(inputStreamReader);

		} catch (IOException e) {
			System.out.println("Erro IO Conexao: " + e.getMessage());
		}
	}

	public void run() {
		try {
			while (true) {
				String recebido = bufferedReader.readLine();

				if (recebido != null) {
					tela.append(recebido + "\n");
				}
			}
		} catch (EOFException e) {
			System.out.println("Conexao: EOFException " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Conexao: IOException " + e.getMessage());
		}
	}
}
