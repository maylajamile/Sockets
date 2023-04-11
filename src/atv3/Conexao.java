package atv3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;

public class Conexao extends Thread {
	private InputStream entrada;
	private InputStreamReader inputStreamReader;
	private BufferedReader bufferedReader;
	private static ArrayList<BufferedWriter> clientes = new ArrayList<BufferedWriter>();
	private Socket cliente;

	public Conexao(Socket cliente) {
		this.cliente = cliente;
		try {
			entrada = cliente.getInputStream();
			inputStreamReader = new InputStreamReader(entrada);
			bufferedReader = new BufferedReader(inputStreamReader);
		} catch (Exception e) {
			System.out.println("[Servidor] - Erro de Conexao: " + e.getMessage());
			close();
		}
	}

	public void run() {
		try {
			OutputStream ou = this.cliente.getOutputStream();
			Writer ouw = new OutputStreamWriter(ou);
			BufferedWriter bfw = new BufferedWriter(ouw);
			clientes.add(bfw);

			String mensagem = bufferedReader.readLine();

			if (mensagem == null) {
				close();
			} else {
				while (mensagem != null) {
					mensagem = bufferedReader.readLine();
					if (mensagem != null) {
						enviarMensagem(bfw, mensagem);
					}
				}
			}

		} catch (Exception e) {
			System.out.println("[Servidor] - Erro ao ler as mensagens: " + e.getMessage());
			close();
		}
	}

	private void enviarMensagem(BufferedWriter bfw, String mensagem) {
		BufferedWriter bwS;

		try {
			for (BufferedWriter bw : clientes) {
				bwS = (BufferedWriter) bw;
				if (!(bfw == bwS)) {
					bw.write(mensagem + "\n");
					bw.flush();
				}
			}
		} catch (Exception e) {
			System.out.println("[Servidor] - Erro ao enviar a mensagem. " + e.getMessage());
		}
	}

	private void close() {
		try {
			bufferedReader.close();
			inputStreamReader.close();
			entrada.close();
		} catch (IOException e) {
			System.out.println("[Servidor] - Erro ao fechar stream de entrada: " + e.getMessage());
		}
		interrupt();
	}
}
