package core.api;

import android.content.Context;
import android.util.Log;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by arysuryawan on 8/6/16.
 */
public class Api extends BaseApi{
    private static final String TAG_API = "API: ";
    private static HttpUtilApi api = getHttpUtilApi();


    /**
     * LOGIN
     **/
    public static void updateProfile(String name, String token, Context context, Callback callback) {
        try {
            JsonObject params = Json.object().add("name", name);

            api.call("", params.toString(), HttpUtilApi.Method.POST, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static class ApiCallback implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG_API, "API failed!!");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.i(TAG_API, "API success!!");
        }
    }
}
