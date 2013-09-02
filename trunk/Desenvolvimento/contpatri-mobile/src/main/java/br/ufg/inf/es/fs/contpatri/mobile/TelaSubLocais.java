package br.ufg.inf.fs.contripatri;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View.OnClickListener;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;


import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
import br.ufg.inf.es.fs.contpatri.mobile.R;
import br.ufg.inf.fs.controller.FolderManager;
import br.ufg.inf.fs.controller.InventarioControler;

public class TelaSubLocais extends Activity {
	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> sublocalCollection;
	ExpandableListView expListView;
	ListView lista;
	ArrayAdapter<File> aparelhosAdapter;
	Button bSincronizar;
	InventarioControler inventControl = new InventarioControler();
	String caminho;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_sub_locais);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("AÇÃO").setItems(R.array.acao_array,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0) {
							mudaActivityColeta(caminho);
						} else if (which == 1) {
							inventControl.enviaColeta(caminho);
						} else if (which == 2) {
							// mudar interface relatorio
						}

					}
				});

		final AlertDialog dialog = builder.create();

		bSincronizar = (Button) findViewById(R.id.bSincronizar);

		bSincronizar.setOnClickListener(bListener);

		aparelhosAdapter = new ArrayAdapter<File>(this, R.layout.message,
				FolderManager.listaDeArquivos("JSON"));

		lista.setAdapter(aparelhosAdapter);

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.i("ENTROO AKEEE UHUULL", "aeeae");

				caminho = aparelhosAdapter.getItem(arg2).getPath();

				dialog.show();

				// salvar o path na variavel para passar pro intent

				// colokei o codigo do poker aki pra lembrar - kamuie
				/*
				 * 
				 * 
				 * }
				 */
			}
		});

	}

	OnClickListener bListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>() {

				ProgressDialog dialog;

				@Override
				protected void onPreExecute() {
					super.onPreExecute();
					dialog = ProgressDialog.show(TelaSubLocais.this, "Aguarde",
							"Sincronizando, Por Favor Aguarde...");
				}

				@Override
				protected Void doInBackground(String... params) {
					String urlString = params[0];

					HttpClient httpclient = new DefaultHttpClient();
					HttpGet httpget = new HttpGet(urlString);

					try {
						HttpResponse response = httpclient.execute(httpget);

						HttpEntity entity = response.getEntity();

						if (entity != null) {
							InputStream instream = entity.getContent();

							String str = jsonString(instream);
							
							 JSONArray jsonA = new JSONArray(str);
							
							 for(int i=0;i<jsonA.length();i++){
								
								 jsonA.getJSONObject(i).getJSONArray("inventario");
								 File output = new File(FolderManager.caminho+jsonA.getJSONObject(i).get("local").toString());

									if (output.canWrite()) {
										BufferedWriter out = null;
										try {
											out = new BufferedWriter(new FileWriter(output, false));

											out.write(jsonA.toString());

										} catch (IOException e) {
											
										} finally {
											if (out != null) {
												try {
													out.close();
												} catch (IOException e) {
													
												}
											}
										}

									}
							 }
							
							
							
							
							
							instream.close();

						}
					} catch (Exception e) {
						Log.e("DEVMEDIA", "Falha ao acessar Web service", e);
					}

					return null;
				}

				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);
					dialog.dismiss();
				}

			};
		}
	};

	private void mudaActivityColeta(String path) {
		Intent intent = new Intent(this, ColetaActivity.class);

		intent.putExtra("urijson", path);

		startActivity(intent);
		TelaSubLocais.this.finish();
	}

	private String jsonString(InputStream is) throws IOException {

		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int lidos;
		while ((lidos = is.read(bytes)) > 0) {
			baos.write(bytes, 0, lidos);
		}
		return new String(baos.toByteArray());
	}

}
