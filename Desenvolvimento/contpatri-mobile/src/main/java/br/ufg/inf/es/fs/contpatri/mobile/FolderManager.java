package br.ufg.inf.fs.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;



import android.os.Environment;


public class FolderManager {
	private final String folderName = "CONTIPATRI-";
	private boolean mExternalStorageAvailable = false;
	private boolean mExternalStorageWriteable = false;
	public static String caminho;
	
	public boolean criarDiretorioParaUsuario(String usuario){
		
		verificaUsabildiadeExternalStorage();
		if(mExternalStorageAvailable){
		 File rootPath = new File(Environment.getExternalStorageDirectory(), folderName+usuario);
	        if(!rootPath.exists()) {
	            rootPath.mkdirs();
	            
	        }
	        caminho = rootPath.getPath();
	        return true;
		}else{
			return false;
	}
	}
	
	
	
	private void verificaUsabildiadeExternalStorage(){
	
	String state = Environment.getExternalStorageState();

	if (Environment.MEDIA_MOUNTED.equals(state)) {
	    // We can read and write the media
	    mExternalStorageAvailable = mExternalStorageWriteable = true;
	} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	    // We can only read the media
	    mExternalStorageAvailable = true;
	    mExternalStorageWriteable = false;
	} else {
	    // Something else is wrong. It may be one of many other states, but all we need
	    //  to know is we can neither read nor write
	    mExternalStorageAvailable = mExternalStorageWriteable = false;
	}
	}
	public static List<File> listaDeArquivos(String comparador){
		List<File> listaDeArquivos = new ArrayList<File>();
	  File file = new File(caminho) ;       
	    File list[] = file.listFiles();

	    for( int i=0; i< list.length; i++)
	    {
	    	if(list[i].getName().endsWith(comparador))
	       listaDeArquivos.add(list[i]);
	    }
		return listaDeArquivos;
		
	}
	
	
	public static String callURL(String myURL) {
		System.out.println("Requested URL:" + myURL);
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
