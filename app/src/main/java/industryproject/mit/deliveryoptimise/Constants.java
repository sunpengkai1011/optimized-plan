package industryproject.mit.deliveryoptimise;

import java.util.HashMap;
import java.util.Map;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

/**
 * Defining the constant
 */
public class Constants {
    public static UserInfo userInfo;

    public static final String GoogleMap_BASE_URL = "https://maps.googleapis.com/";
    public static final String APIKEY = "AIzaSyA8eJEaPxmkqOSRBC-pNoCQzemiJfIjo5U";
    public static final String DEPARTURE_TIME = "now";
    public static final boolean OPTIMAIZEWAYPOINTS = true;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    public static final String SP_KEY = "CLEANING_SERVICE";
    public static final String SP_KEY_ROUTES = "ROUTES";

    //Response code
    public static final int RESPONSE_CODE_SUCCESSFUL = 200;

    //Close Type
    public static final int CLOSETYPE_LOGOUT = 1;

    //Address Type
    public static final int ADDRESS_TYPE_ORIGIN = 1;
    public static final int ADDRESS_TYPE_DESTINATION = 2;
    public static final int ADDRESS_TYPE_WAYPOINT = 3;

    //Intent
    public static final String KEY_INTENT_USERINFO = "userinfo";
    public static final String KEY_INTENT_USERNAME = "username";
    public static final String KEY_INTENT_ADDRESSES = "addresses";
    public static final String KEY_INTENT_LEG = "leg";
    public static final String KEY_INTENT_POSITION = "position";
    public static final String KEY_INTENT_CLOSETYPE = "close_type";
    public static final int INTNET_REUQEST_MAP_TO_DEATIL = 200;
    public static final int INTENT_REQUEST_LOGIN_TO_REGISTER = 202;

    //Status
    public static final int STATUS_INIT = 0;
    public static final int STATUS_DEPARTED = 1;
    public static final int STATUS_ARRIVED = 2;

    public static Map<String, Long> realTime_depart = new HashMap<>();
    public static Map<String, Long> realTime_arrive = new HashMap<>();

    public static final int TYPE_LOCATION_ORIGIN = 1;
    public static final int TYPE_LOCATION_DESTINATION = 2;
    public static final int TYPE_LOCATION_WAYPOINT = 3;

    public static final String TYPE_NAME_ORIGIN = "origin";
    public static final String TYPE_NAME_DESTINATION = "destination";
    public static final String TYPE_NAME_WAYPOINT = "way point";
}
