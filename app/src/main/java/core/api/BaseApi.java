package core.api;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by arysuryawan on 8/6/16.
 */
public class BaseApi {
    private static HttpUtilApi httpUtilApi;

    protected static final String API_DOMAIN_URL = "http://128.199.98.84/api/";


    protected static HttpUtilApi getHttpUtilApi() {
        if (httpUtilApi == null) {
            httpUtilApi = new HttpUtilApi(API_DOMAIN_URL);
        }
        return httpUtilApi;
    }

    static class HttpUtilApi {
        private String url;
        private OkHttpClient client;

        private Call call;
        private Request request;
        private Response response;
        private RequestBody body;
        private StringBuffer result;

        public enum Method {GET, POST, PUT, DELETE}


        public HttpUtilApi(String url) {
            this.url = url;
        }


        private OkHttpClient setupClient() {
            if (client == null) this.client = new OkHttpClient();

            return this.client;
        }

        private static final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");


        public StringBuffer call(String apiUrl, String paramsJson, Method method, Callback callback) throws IOException {
            setupClient();

            switch (method) {
                case GET:
                    request = new Request.Builder()
                            .url(url + apiUrl)
                            .get()
                            .build();

                    call = client.newCall(request);
                    call.enqueue(callback);
                    break;

                case POST:
                    body = RequestBody.create(JSON, paramsJson);
                    request = new Request.Builder()
                            .url(url + apiUrl)
                            .post(body)
                            .build();
                    call = client.newCall(request);
                    call.enqueue(callback);
                    break;

                case PUT:
                    body = RequestBody.create(JSON, paramsJson);
                    request = new Request.Builder()
                            .url(url + apiUrl)
                            .put(body)
                            .build();
                    call = client.newCall(request);
                    call.enqueue(callback);
                    break;

                case DELETE:
                    body = RequestBody.create(JSON, paramsJson);
                    request = new Request.Builder()
                            .url(url + apiUrl)
                            .delete(body)
                            .build();
                    call = client.newCall(request);
                    call.enqueue(callback);
                    break;

                default:
                    result = new StringBuffer("");


            }
            return result;
        }
    }
}
