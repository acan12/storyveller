package core.dao;

import android.content.Context;

import core.model.User;
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
        if(!password.equals(null))user.setPasswordSHA(SecurityUtil.sha256(password));
        saveToRealm(user);
    }
}
