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
import br.ufg.inf.es.fs.contpatri.mobile.util.Conversores;

/**
 * Classe que adapta os dados à um layout definido na pasta res/layout para ser
 * utilizado em uma lista.
 * 
 * @author Rogério Tristão Junior
 * 
 */
public final class ListaColetaAdapter extends BaseAdapter {

	private transient List<Tombamento> listaTombamento;
	private transient LayoutInflater inflater;
	private transient ViewHolder holder;

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
	public Object getItem(int paramInt) {
		return listaTombamento.get(paramInt);
	}

	public long getItemId(int paramInt) {
		return paramInt;
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

		Tombamento tmb = listaTombamento.get(posicao);

		holder.alteracao.setText(Conversores.longToDate(tmb
				.getUltimaAlteracao()));
		holder.codigo.setText(String.valueOf(tmb.getCodigo()));
		holder.situacao.setText(tmb.getSituacao());

		return convertView;
	}

}
