package core.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by arysuryawan on 5/15/16.
 */
public class Trip extends RealmObject {
    @PrimaryKey
    private String name;

    private RealmList<Itienary> itienaries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Itienary> getItienaries() {
        return itienaries;
    }

    public void setItienaries(RealmList<Itienary> itienaries) {
        this.itienaries = itienaries;
    }
}
