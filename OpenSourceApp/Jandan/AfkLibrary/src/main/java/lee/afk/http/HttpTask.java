package lee.afk.http;

import android.os.AsyncTask;

public class HttpTask {
	private HttpOperationTask mTask;
	public HttpTask(HttpOperationListener listener,String url, int type) {
		mTask = new HttpOperationTask();
		mTask.execute(new Object[]{listener,url,type});
	}
	
	class HttpOperationTask extends AsyncTask<Object, Integer, Void> {
		
		private HttpOperationListener listener;
		private int type;
		@Override
		protected Void doInBackground(Object... params) {
			listener = (HttpOperationListener) params[0];
			String url = (String) params[1];
			type = (Integer) params[2];
			HttpOperation.httpGet(listener, type, url);
			return null;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			if(listener != null){
				listener.onHttpProgress(type, values[0]);
			}
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}

	}
}
