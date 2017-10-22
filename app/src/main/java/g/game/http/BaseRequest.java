package g.game.http;

import g.game.Game;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import android.util.Log;



public class BaseRequest {
	private static final String ENCODE_TYPE = "UTF-8";
	private static final String VER_NAME = "1.0";
	

	public static String postRequest(String url, List<NameValuePair> params) {
		if (url != null) {
			url += "?version=" + VER_NAME;
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Game.HTTP.TIMEOUT);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Game.HTTP.TIMEOUT);
			HttpPost httpPost = new HttpPost(url);
			try {
				if(params != null && !params.isEmpty()) {
					HttpEntity httpEntity = new UrlEncodedFormEntity(params,ENCODE_TYPE);
					httpPost.setEntity(httpEntity);
				}
				HttpResponse response = client.execute(httpPost);
				if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK) {
					return null;
				}
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, ENCODE_TYPE);
			} catch (Exception e) {
				Log.e("BaseRequest", "", e);
			}
		}
		return null;
	}
	
	public static String getRequest(String url) {
		if (url != null) {
			if (url.contains("?")) {
				url += "&version=" + VER_NAME;
			} else {
				url += "?version=" + VER_NAME;
			}
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, Game.HTTP.TIMEOUT);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, Game.HTTP.TIMEOUT);
			HttpGet get = new HttpGet(url);
			try {
				HttpResponse response = client.execute(get);
				if(response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK) {
					return null;
				}
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, ENCODE_TYPE);
			} catch (Exception e) {
				Log.e("BaseRequest", "", e);
			}
		}
		return null;
	}
}
