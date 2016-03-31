package lee.afk.http;

public interface HttpOperationListener {
	void onHttpResult(HttpResult result);
	void onHttpProgress(int type, int progerss);
}
