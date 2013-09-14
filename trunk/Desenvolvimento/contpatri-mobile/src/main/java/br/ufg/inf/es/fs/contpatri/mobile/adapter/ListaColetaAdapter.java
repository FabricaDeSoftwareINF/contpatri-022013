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
package br.ufg.inf.es.fs.contpatri.mobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.es.fs.contpatri.mobile.tombamento.Tombamento;
import br.ufg.inf.es.fs.contpatri.mobile.util.Armazenamento;
import br.ufg.inf.es.fs.contpatri.mobile.util.Conversores;

/**
 * Classe que adapta os dados à um layout definido na pasta res/layout para ser
 * utilizado em uma lista.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class ListaColetaAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private List<Tombamento> listaTombamento;
	private ViewHolder holder;

	/**
	 * Design Pattern indicado pela Google para melhorar a performance.
	 * 
	 * @author Rogério Tristão Junior
	 * 
	 */
	static class ViewHolder {
		private TextView codigo;
		private TextView situacao;
		private TextView alteracao;
	}

	/**
	 * Construtor para instanciar o Adapter e inicializar as variáveis.
	 * 
	 * @param contexto
	 *            contexto usado para inicializar o redimensionador de layouts (
	 *            <code>LayoutInflater</code>)
	 * @param tombamentos
	 *            lista de objetos do tipo <code>Tombamento</code> para que o
	 *            adapter pegue os dados e adapte no layout
	 */
	public ListaColetaAdapter(final Context contexto,
			final List<Tombamento> tombamentos) {
		inflater = LayoutInflater.from(contexto);
		listaTombamento = tombamentos;
	}

	@Override
	public int getCount() {
		return listaTombamento.size();
	}

	@Override
	public Object getItem(final int paramInt) {
		return listaTombamento.get(paramInt);
	}

	@Override
	public long getItemId(final int id) {
		return id;
	}

	@Override
	public View getView(final int posicao, final View view,
			final ViewGroup vGroup) {

		View convertView = view;

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.linha_lista_sub_locais,
					null);
			holder = new ViewHolder();

			holder.alteracao = (TextView) convertView
					.findViewById(R.id.txtUltimaAlteracao);
			holder.codigo = (TextView) convertView.findViewById(R.id.txtCodigo);
			holder.situacao = (TextView) convertView
					.findViewById(R.id.txtSituacao);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final Tombamento tmb = listaTombamento.get(posicao);

		holder.alteracao.setText(Conversores.longToDate(tmb
				.getUltimaAlteracao()));
		holder.codigo.setText(String.valueOf(tmb.getCodigo()));
		holder.situacao.setText(tmb.getSituacao());

		return convertView;
	}

	@Override
	public void notifyDataSetChanged() {
		listaTombamento = Armazenamento.Externo.getListaTombamentos();
		super.notifyDataSetChanged();
	}

}
