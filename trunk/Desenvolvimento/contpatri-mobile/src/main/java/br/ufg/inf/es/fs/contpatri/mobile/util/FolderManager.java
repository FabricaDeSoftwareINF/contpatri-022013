package br.ufg.inf.es.fs.contpatri.mobile.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public final class FolderManager {

	public static transient final String NOME_PASTA = "CONTPATRI";

	public static boolean criaDiretorio(final Context contexto) {
		if (Armazenamento.Externo.getEstadoValido()) {
			File rootPath = new File(Environment.getExternalStorageDirectory(),
					NOME_PASTA);
			if (!rootPath.exists()) {
				rootPath.mkdirs();
			}
			return true;
		} else {
			Toast.makeText(contexto, "Insira um cartão de memória para logar na aplicação", Toast.LENGTH_LONG).show();
			return false;
		}
	}

	public static List<File> getArquivos(final String caminho, final String comparador) {
		
		List<File> listaDeArquivos = new ArrayList<File>();
		File list[] = new File(caminho).listFiles();

		for (int i = 0; i < list.length; i++) {
			if (list[i].getName().endsWith(comparador))
				listaDeArquivos.add(list[i]);
		}
		
		return listaDeArquivos;

	}

	public static String callURL(String myURL) {

		StringBuilder sb = new StringBuilder();

		try {

			FileReader file = new FileReader(myURL);
			BufferedReader bufferedReader = new BufferedReader(file);
			if (bufferedReader != null) {
				int cp;
				while ((cp = bufferedReader.read()) != -1) {
					sb.append((char) cp);
				}
				bufferedReader.close();
			}
			file.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL,
					e);
		}

		return sb.toString();
	}

}
