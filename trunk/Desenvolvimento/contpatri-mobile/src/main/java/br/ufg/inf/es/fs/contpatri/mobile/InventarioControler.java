package br.ufg.inf.fs.controller;

import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.os.Looper;

public class InventarioControler {

	 public void enviaColeta(final String jsonUrl) {
	        Thread t = new Thread() {

	            private URI URL;

				public void run() {
	                Looper.prepare(); //For Preparing Message Pool for the child Thread
	                HttpClient client = new DefaultHttpClient();
	                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
	                HttpResponse response;
	                JSONObject json = new JSONObject();

	                try {
	                    HttpPost post = new HttpPost(URL);
	                  
	                    StringEntity se = new StringEntity( FolderManager.callURL(jsonUrl));  
	                    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	                    post.setEntity(se);
	                    response = client.execute(post);

	                    /*Checking response */
	                    if(response!=null){
	                        InputStream in = response.getEntity().getContent(); //Get the data in the entity
	                    }

	                } catch(Exception e) {
	                    e.printStackTrace();
	                 
	                }

	                Looper.loop(); //Loop in the message queue
	            }
	        };
	        t.start();

}
	 
	 
	 
}
