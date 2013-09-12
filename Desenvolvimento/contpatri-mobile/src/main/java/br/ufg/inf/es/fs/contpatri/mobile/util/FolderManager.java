/**
 * Esse documento é parte do código fonte e artefatos relacionados ao projeto
 * CONTPATRI, em desenvolvimento pela Fábrica de Software da UFG.
 *
 * Links relevantes: Fábrica de Software: http://fs.inf.ufg.br/ 
 * Instituto de Informática UFG: http://www.inf.ufg.br 
 * Projeto CONTPATRI DROPBOX: https://www.dropbox.com/home/CONTPATRI%20-%20012013 
 *
 * Copyleft © UFG.
 *
 * Licenciado sobre a licença GNU-GPL v3
 *
 * Você pode obter uma cópia da licença em http://www.gnu.org/licenses/gpl.html
 *
 * A menos que especificado ou exigido por legislação local, o software é
 * fornecido "da maneira que está", sem garantias ou condições de qualquer tipo,
 * nem expressas nem implícitas. Em caso de dúvidas referir a licença GNU-GPL.
 */
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
			Toast.makeText(contexto, "Insira um cart�o de mem�ria para logar na aplica��o", Toast.LENGTH_LONG).show();
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
