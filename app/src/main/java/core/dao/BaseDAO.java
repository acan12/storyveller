package core.dao;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by arysuryawan on 6/24/16.
 */
public class BaseDAO {
    protected static Realm realm;

    public BaseDAO(Context context) {
        setupRealm(context);
    }

    protected static Realm setupRealm(Context context) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .name("beepacker.realm")
                .build();
        // Get a Realm instance for this thread
        realm = Realm.getInstance(realmConfig);

        return realm;
    }

    protected static RealmObject saveToRealm(RealmObject object) {
        realm.beginTransaction();
        RealmObject obj = realm.copyToRealm(object);
        realm.commitTransaction();


        return obj;
    }

    protected static RealmResults getObjects(Class clazz) {
        RealmResults objects = realm.allObjects(clazz);

        return objects;
    }

    public static RealmResults getDataByKey(String key, String value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .beginGroup()
                .contains(key, value)
                .endGroup()
                .findAll();

        return items;
    }

    public static RealmResults getDataByKeyByValueBoolean(String key, Boolean value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .equalTo(key, value).findAll();

        return items;
    }

    private static void close() {
        realm.close();
    }

    protected static void clear(Class clazz) {
        try {
            realm.beginTransaction();
            realm.clear(clazz);
            realm.commitTransaction();
        } catch (Exception e) {
        }
    }

    protected static void removeAll() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();

        close();
    }

    protected static void removeAllObject(Class clazz) {
        realm.beginTransaction();
        while (getObjects(clazz).size() > 0) {
            getObjects(clazz).removeLast();
        }
        realm.commitTransaction();
    }
}
