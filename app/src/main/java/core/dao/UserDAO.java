package core.dao;

import android.content.Context;

import core.model.User;
import core.util.PreferenceUtil;
import core.util.SecurityUtil;

/**
 * Created by arysuryawan on 6/24/16.
 */
public class UserDao extends BaseDao {

    protected UserDao(Context context) {
        super(context);
    }

    public static UserDao instanceObject(Context context) {
        return new UserDao(context);
    }

    public static void saveUser(User user, String password, Context context){
        instanceObject(context);
        if(!password.equals(""))user.setPasswordSHA(SecurityUtil.sha256(password));
        saveToRealm(user);

        // clear preference app
        PreferenceUtil.clearPreference(context);
    }

    public static User getUser(Context context) {
        instanceObject(context);
        User user = null;
        try {
            user = (User) getObjectsRealm(User.class).last();
        } catch (Exception e) {
            user = new User();
        }

        return user;
    }

    public static boolean isPasswordMatch(String password, Context context) {
        instanceObject(context);
        User user = (User) getObjectsRealm(User.class).first();

        boolean ok = SecurityUtil.sha256(password).equals(user.getPasswordSHA());
        return ok;
    }

    public static void addLoginStatus(boolean loginStatus, Context context){
        instanceObject(context);

        realm.beginTransaction();
        User user = (User) getObjectsRealm(User.class).first();
        user.setLoginStatus(loginStatus);
        realm.commitTransaction();
    }

    public static void deleteUser(Context context) {
        instanceObject(context);
        deleteRealm(User.class);
    }
}
