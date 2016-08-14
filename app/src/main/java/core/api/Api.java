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

    // register / reset users
    private static final String USER_API_URL = "users";
    private static final String USER_DETAIL_API_URL = "users/{{id}}";
    private static final String RESET_ACCOUNT_API_URL = "reset";
    private static final String VERIFY_ACCOUNT_API_URL = "verify/{{token}}";

    // login session
    private static final String LOGIN_API_URL = "sessions";
    private static final String LOGOUT_API_URL = "sessions/{{uid}}}";



    private static final String TAG_API = "API: ";
    private static HttpUtilApi api = getHttpUtilApi();


    /**
     * REGISTER
     **/
    public static void registerUser(String email, String firstname, String lastname, String pass, String passconfirm, Context context, Callback callback) {
        try {
            JsonObject subparams = Json.object()
                    .add("email", email)
                    .add("first_name", firstname)
                    .add("last_name", lastname)
                    .add("password", pass)
                    .add("password_confirmation", passconfirm);

            JsonObject params = Json.object().add("user", subparams);

            api.call(USER_API_URL, params.toString(), HttpUtilApi.Method.POST, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * LOGIN
     **/
    public static void loginUser(String email, String pass, Context context, Callback callback) {
        try {
            JsonObject subparams = Json.object()
                    .add("email", email)
                    .add("password", pass);

            JsonObject params = Json.object().add("user", subparams);

            api.call(LOGIN_API_URL, params.toString(), HttpUtilApi.Method.POST, callback);
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
