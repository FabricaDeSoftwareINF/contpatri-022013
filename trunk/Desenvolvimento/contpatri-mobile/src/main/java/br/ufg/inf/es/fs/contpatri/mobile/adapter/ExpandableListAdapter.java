package br.ufg.inf.es.fs.contpatri.mobile.adapter;

import java.util.List;
import java.util.Map;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public final class ExpandableListAdapter extends BaseExpandableListAdapter {

	private transient Activity contexto;
	private transient Map<String, List<String>> sublocalCollections;
	private transient List<String> sublocais;

	public ExpandableListAdapter(final Activity context, final List<String> laptops,
			final Map<String, List<String>> laptopCollections) {
		contexto = context;
		sublocalCollections = laptopCollections;
		sublocais = laptops;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return sublocalCollections.get(sublocais.get(groupPosition)).get(
				childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(final int groupPosition, final int childPosition,
			final boolean isLastChild, View convertView, final ViewGroup parent) {
		final String sublocal = (String) getChild(groupPosition, childPosition);
		LayoutInflater inflater = contexto.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.child_item, null);
		}

		Button botaoColetar = (Button) convertView
				.findViewById(R.id.coletarButton);

		Button botaoRelatorio = (Button) convertView
				.findViewById(R.id.relatorioButton);

		Button botaoEnviar = (Button) convertView
				.findViewById(R.id.enviarButton);

		botaoColetar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});

		botaoRelatorio.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});

		botaoEnviar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});

		return convertView;
	}

	public int getChildrenCount(int groupPosition) {
		return sublocalCollections.get(sublocais.get(groupPosition)).size();
	}

	public Object getGroup(int groupPosition) {
		return sublocais.get(groupPosition);
	}

	public int getGroupCount() {
		return sublocais.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String laptopName = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) contexto
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.group_item, null);
		}
		TextView item = (TextView) convertView.findViewById(R.id.laptop);
		item.setTypeface(null, Typeface.BOLD);
		item.setText(laptopName);
		return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}
	
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}