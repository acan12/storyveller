package core.parser;

import com.eclipsesource.json.Json;

import core.IConfig;

/**
 * Created by arysuryawan on 8/6/16.
 */
public class BaseParser {
    public static final int OK = 1;
    public static final int FAIL = 0;

    public static Object[] getParser(String results) {

        int apiStatus = Json.parse(results).asObject().getInt(IConfig.API_DATA_RESULT_NAME, OK);
        String apiMessage = Json.parse(results).asObject().getString(IConfig.API_MESSAGE_RESULT_NAME, "");
        String apiData = Json.parse(results).asObject().getString(IConfig.API_DATA_RESULT_NAME, "");

        return new Object[]{apiStatus == OK, apiMessage + " ", apiData};
    }
}
