package core.dao;

import android.content.Context;

import core.model.User;

/**
 * Created by arysuryawan on 6/24/16.
 */
public class UserDAO extends BaseDAO{

    public UserDAO(Context context) {
        super(context);
    }

    public static void saveUser(User user, Context context){
        setupRealm(context);
        saveToRealm(user);
    }
}
