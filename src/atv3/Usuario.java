package atv3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {

	public Usuario(String nome) {
		salvarUsuario(nome);
	}

	private void salvarUsuario(String nome) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("usuarios.csv", true));
			bufferedWriter.write(nome);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			System.out.println("[Cliente] - Não foi possível salvar nome de usuário. " + e.getMessage());
		}
	}

	public String listarUsuarios() {
		List<String> usuarios = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.csv"))) {
			String linha;
			while ((linha = reader.readLine()) != null) {
				usuarios.add(linha);
			}
		} catch (IOException e) {
			System.out.println("[Cliente] - Não foi possível listar usuários. " + e.getMessage());
		}

		String nomes = "";
		for (String usuario : usuarios) {
			if (!nomes.contains(usuario)) {
				nomes += usuario + "\n";
			}
		}

		return nomes;
	}

	public void removerUsuario(String usuarioExcluido) {
		File arquivoAntigo = new File("usuarios.csv");
		File arquivoNovo = new File("usuariosTemp.csv");

		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("usuariosTemp.csv", true));
			Scanner scanner = new Scanner(new File("usuarios.csv"));

			while (scanner.hasNext()) {
				String nome = scanner.next();
				String usuarioAtual = nome;
				if (!usuarioExcluido.equals(usuarioAtual)) {
					bufferedWriter.write(usuarioAtual + "\n");
				}
			}

			bufferedWriter.flush();
			bufferedWriter.close();
			scanner.close();
			arquivoAntigo.delete();
			File arquivo = new File("usuarios.csv");
			arquivoNovo.renameTo(arquivo);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
