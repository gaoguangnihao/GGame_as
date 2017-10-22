package g.game.http;
import java.util.List;	

import org.apache.http.NameValuePair;

import android.os.AsyncTask;

public class AsyncHttpRequest extends AsyncTask<String, Void, String> {
	
	public static final String GET = "get";
	public static final String POST = "post";
	
	private String url;
	private List<NameValuePair> values;
	private HttpHandler handler;
	private int requestCode;
	
	public AsyncHttpRequest(HttpHandler ctx, String url, List<NameValuePair> values){
		this.handler = ctx;
		this.url = url;
		this.values = values;
	}
	
	@Override
	protected String doInBackground(String... params) {
		String result = null;
		if (params[0] != null) {
			requestCode = Integer.parseInt(params[0]);
			String request = "";
			if(params.length > 1) {
				request = params[1];
			};
			
			if (GET.equals(request)) {
				result = BaseRequest.getRequest(url);
			} else {
				result = BaseRequest.postRequest(url, values);
			}
		} else {
			result = BaseRequest.postRequest(url, values);
		}
		return result;
	}

	
	@Override
	protected void onPostExecute(String result) { 
		if(handler!= null && !isCancelled()){
		    handler.callBackFunction(result, requestCode);
	    }
	}
	
	@Override
	protected void onCancelled() {
	    super.onCancelled();
	}

	public interface HttpHandler {
	
		public Object callBackFunction(String result, int requestCode);
	}

}
