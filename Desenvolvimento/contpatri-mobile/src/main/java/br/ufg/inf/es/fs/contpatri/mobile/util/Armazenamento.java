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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;
import br.ufg.inf.es.fs.contpatri.mobile.nucleo.NucleoApp;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;

/**
 * Classe que gerencia o armazenamento interno e externo para uso do aplicativo.
 * 
 * @author Rogério Tristão Junior
 * @author Muryllo Tiraza
 * @author Fellipe Cesar
 * @author Thiago Fernandes
 * 
 */
public final class Armazenamento {

	/**
	 * Classe com operações do dispositivo de armazenamento externo.
	 * 
	 * @author Rogério Tristão Junior
	 * @author Muryllo Tiraza
	 * @author Fellipe Cesar
	 * @author Thiago Fernandes
	 * 
	 */
	public static class Externo {

		/**
		 * Retorna se o dispositivo externo está disponível.
		 * 
		 * @return verdadeiro para dispositivo externo estiver disponível para
		 *         leitura e gravação, caso contrário será falso.
		 */
		public static boolean getEstadoValido() {
			if (Environment.MEDIA_MOUNTED.equals(Environment
					.getExternalStorageState())) {
				return true;
			} else {
				return false;
			}
		}

		public static List<Tombamento> getListaTombamentos() {

			StringBuilder sb;
			List<Tombamento> lista = new ArrayList<Tombamento>();
			File[] listaArquivos = new File(NucleoApp.LOCAL_COLETAS)
					.listFiles();
			InputStream fis;

			for (File arquivo : listaArquivos) {
				try {

					fis = new FileInputStream(arquivo);

					int conteudo;
					sb = new StringBuilder();
					while ((conteudo = fis.read()) != -1) {
						sb.append((char) conteudo);
					}

					Tombamento tmb = new Tombamento();
					tmb.fromJson(sb.toString());

					lista.add(tmb);

					fis.close();
				} catch (final FileNotFoundException e) {
					Log.e(Armazenamento.class.getSimpleName(), "", e);
				} catch (final IOException e) {
					Log.e(Armazenamento.class.getSimpleName(), "", e);
				}
			}

			return lista;
		}

		public static void excluirArquivoTombamento(final long codigo) {
			String id = String.valueOf(codigo);
			new File(NucleoApp.LOCAL_COLETAS + id + ".json").delete();
		}

	}

}
