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
package br.ufg.inf.es.fs.contpatri.mobile.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.TombamentoDAO;

/**
 * Classe que controla as ações do evento da tela de Coleta do aplicativo.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class ColetaController {

	private final Activity activity;
	private final TombamentoDAO tmbDAO;

	/**
	 * Construtor padrão para instanciar a classe e inicializar as variáveis.
	 * 
	 * @param actv
	 *            <code>Activity</code> necessária para fechar a tela atual caso
	 *            tenha sucesso na gravação do arquivo e para a construção de um
	 *            <code>Dialog</code>
	 */
	public ColetaController(final Activity actv) {
		activity = actv;
		tmbDAO = new TombamentoDAO(actv);
	}

	/**
	 * Método que grava em um arquivo do tipo <b>.json</b> e no formato do JSON,
	 * o objeto Tombamento.
	 * 
	 * @param tombamento
	 *            objeto que terá suas informações gravadas no arquivo
	 */
	public void gerarColeta(final Tombamento tombamento) {

		tmbDAO.abrirConexao();
		tmbDAO.inserir(tombamento);
		tmbDAO.fecharConexao();

		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		AlertDialog dialog;
		builder.setTitle("Sucesso");
		builder.setMessage("Tombamento " + tombamento.getCodigo()
				+ " gerado com sucesso!");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				activity.finish();
			}
		});
		dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();

	}

}
