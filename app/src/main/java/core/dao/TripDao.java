package core.dao;

import android.content.Context;

/**
 * Created by arysuryawan on 8/6/16.
 */
public class TripDao extends BaseDAO {
    protected TripDao(Context context) {
        super(context);
    }

    public static TripDao instanceObject(Context context) {
        return new TripDao(context);
    }
}
