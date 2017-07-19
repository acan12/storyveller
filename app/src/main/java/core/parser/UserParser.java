package core.parser;

import android.content.Context;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

import core.IConfig;
import core.dao.UserDAO;
import core.model.User;
import core.util.PreferenceUtil;

/**
 * Created by arysuryawan on 8/14/16.
 */
public class UserParser extends BaseParser {

    public static void setUserParser(String results, Context context) {
        JsonObject userJson = Json.parse(results).asObject().get("user").asObject();
        int uid             = userJson.getInt("id", 0);
        String email        = userJson.getString("email", "");
        String gender       = userJson.getString("gender", "");
        String token        = userJson.getString("authentication_token", "");
        String username     = userJson.getString("username", "");
//        String firstname    = userJson.getString("first_name", "");
//        String lastname     = userJson.getString("last_name", "");
        String avatar       = userJson.getString("avatar_small", "");

        User user = User.getInstance();
        user.setUid(uid);
        user.setEmail(email);
        user.setGender(gender);
        user.setToken(token);
        user.setDisplayName(username);
        user.setAvatar(avatar);

        String password = PreferenceUtil.getPreferenceString(IConfig.USER_PASSWORD_KEY, context);
        UserDAO.saveUser(user, password, context);

    }
}
