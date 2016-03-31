package lee.afk.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

import lee.afk.afkutils.log.LeeLog;

public class HttpOperation {
	/**
	 * RequestMethod
	 */
	private static String GET = "GET";
	private static String POST = "POST";

	/**
	 * 编码
	 */
	@SuppressWarnings("unused")
	private final static String UTF8 = "UTF-8";
	@SuppressWarnings("unused")
	private final static String GBK = "GBK";

	public static void httpGet(HttpOperationListener listener, int type, String url) {
		httpOperation(listener, url, null, -1, false, "");
	}

	public static void httpPost(HttpOperationListener listener, int type, String url, String paramsStr) {
		httpOperation(listener, url, null, -1, true, paramsStr);
	}

	/**
	 * 
	 * http 网络请求操作
	 * 
	 * @param listener
	 * @param urlStr
	 * @param proxy
	 * @param timeout
	 * @param isPost
	 * @param paramsStr
	 * @throws IOException
	 */
	private static void httpOperation(HttpOperationListener listener, String urlStr, Proxy proxy, int timeout,
			boolean isPost, String paramsStr) {
		HttpResult httpResult = new HttpResult();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection httpURLConnection = null;
			if (proxy != null)
				httpURLConnection = (HttpURLConnection) url.openConnection(proxy);
			else
				httpURLConnection = (HttpURLConnection) url.openConnection();

			/**
			 * 设置超时
			 */
			if (timeout <= 0) {
				timeout = HttpSettings.DEFAULT_TIME_OUT;
			}
			httpURLConnection.setConnectTimeout(timeout);

			/**
			 * 设置允许输出
			 * 
			 * 这里设置为如果是post方式，就允许输出 如果非post方式（目前只考虑到get 和 post），则不允许输出
			 */
			httpURLConnection.setDoOutput(isPost);

			/**
			 * 设置不允许使用缓存
			 */
			httpURLConnection.setUseCaches(false);

			/**
			 * 设置POST or GET
			 */
			httpURLConnection.setRequestMethod(isPost ? POST : GET);

			/**
			 * 设置文件字符集
			 */
			// httpURLConnection.setRequestProperty("Charset", UTF8);

			/**
			 * 设置文件长度
			 */
			// httpURLConnection.setRequestProperty("Content-Length",
			// String.valueOf(data.length));

			/**
			 * 设置文件类型
			 */
			// httpURLConnection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");

			/**
			 * 获取响应码
			 */
			int responseCode = httpURLConnection.getResponseCode();
			httpResult.setResponseCode(responseCode);

			/**
			 * 若响应码不为200 则退出
			 */
			if (responseCode != 200) {
				httpResult.setResponseCode(200);
				httpURLConnection.disconnect();
				return;
			}

			if (isPost) {
				PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
				printWriter.print(paramsStr);
				printWriter.flush();
				printWriter.close();
			}

			httpResult.setResult(reader(httpURLConnection.getInputStream()));

			httpResult.setSuccess(true);
		} catch (IOException e) {
			httpResult.setSuccess(false);
			httpResult.setExceptionMessage(e.getMessage());
			LeeLog.e(e);
		}

		if (listener != null) {
			listener.onHttpResult(httpResult);
		}
	}

	public static String reader(InputStream inputStream) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append(line);
		}

		inputStreamReader.close();
		bufferedReader.close();
		return stringBuffer.toString();
	}
}
