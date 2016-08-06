package core.dao;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by arysuryawan on 6/24/16.
 */
public class BaseDao {
    protected static Realm realm;

    protected BaseDao(Context context) {
        setupRealm(context);
    }

    protected Realm setupRealm(Context context) {
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


    protected static RealmResults getObjectsRealm(Class clazz) {
        RealmResults objects = realm.where(clazz).findAll();

        return objects;
    }


    protected static RealmResults getObjectByKeyRealm(String key, String value, Class clazz) {
        RealmResults items = realm.where(clazz)
                .beginGroup()
                .equalTo(key, value)
                .endGroup()
                .findAll();

        return items;
    }

    protected static void closeRealm() {
        realm.close();
    }

    protected static void deleteRealm(Class clazz) {
        try {
            realm.beginTransaction();
            realm.delete(clazz);
            realm.commitTransaction();
        } catch (Exception e) {
        }
    }
}
