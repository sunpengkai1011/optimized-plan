package industryproject.mit.deliveryoptimise;

import java.util.HashMap;
import java.util.Map;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

public class Constants {
    public static UserInfo userInfo;

    public static final String GoogleMap_BASE_URL = "https://maps.googleapis.com/";
    public static final String APIKEY = "AIzaSyA8eJEaPxmkqOSRBC-pNoCQzemiJfIjo5U";
    public static final String DEPARTURE_TIME = "now";
    public static final boolean OPTIMAIZEWAYPOINTS = true;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    public static final String SP_KEY = "CLEANING_SERVICE";

    //Response code
    public static final int RESPONSE_CODE_SUCCESSFUL = 100;
    public static final int RESPONSE_CODE_FAIL = 101;

    //Intent
    public static final String KEY_INTENT_USERINFO = "userinfo";
    public static final String KEY_INTENT_ADDRESSES = "addresses";
    public static final int INTENT_REQUEST_LOGIN_TO_REGISTER = 202;

    public static Map<String, Long> realTime_depart = new HashMap<>();
    public static Map<String, Long> realTime_arrive = new HashMap<>();

    public static final int TYPE_LOCATION_ORIGIN = 1;
    public static final int TYPE_LOCATION_DESTINATION = 2;
    public static final int TYPE_LOCATION_WAYPOINT = 3;
}
