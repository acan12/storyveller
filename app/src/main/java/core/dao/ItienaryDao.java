package core.dao;

import android.content.Context;

/**
 * Created by arysuryawan on 8/6/16.
 */
public class ItienaryDao extends BaseDao {
    protected ItienaryDao(Context context) {
        super(context);
    }

    public static ItienaryDao instanceObject(Context context) {
        return new ItienaryDao(context);
    }

}
