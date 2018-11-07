package industryproject.mit.deliveryoptimise.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.tu.loadingdialog.LoadingDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import industryproject.mit.deliveryoptimise.Constants;

public class GeneralUtil {

    /**
     * Convert the dip to px.
     * @param context
     * @param dp the number of dp.
     * @return
     */
    public static int dip2px(Context context, int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }

    /**
     * Create a loading dialog using third-party open resource.
     * @param context
     * @param message the dialog message
     * @return loading dialog instance
     */
    public static LoadingDialog getWaitDialog(Context context, String message){
        LoadingDialog.Builder loadBuilder =new LoadingDialog.Builder(context)
                .setMessage(message)
                .setCancelable(true)
                .setCancelOutside(true);
        return loadBuilder.create();
    }

    /**
     * Convert the second to minutes.
     * @param second second value.
     * @return minutes value.
     */
    public static String secondToMinutes(int second){
        return second / 60 + " Mins";
    }

    /**
     * Convert the millisecond to minutes.
     * @param ms millisecond value.
     * @return minutes value.
     */
    public static int msToMinutes(int ms){
        return ms / 6000;
    }

    /**
     * Convert the meter to kilometer and keep one decimal.
     * @param meter
     * @return
     */
    public static String mToKm(int meter){
        float distance = (float) meter / 1000;
        DecimalFormat decimalFormat = new DecimalFormat(".0");
        return decimalFormat.format(distance) + " Km";
    }

    /**
     * Convert the object to json string
     * @param src object
     * @param typeOfSrc object type
     * @return json string
     */
    public static String toJson(Object src, Type typeOfSrc){
        Gson gson = new Gson();
        return gson.toJson(src, typeOfSrc);
    }

    /**
     * Convert json string to object
     * @param json json string
     * @param type object type
     * @param <T>
     * @return object
     */
    public static<T> T fromJson(String json, Class<T> type){
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * Convert json string to object
     * @param json json string
     * @param type object type
     * @param <T>
     * @return object
     */
    public static<T> T fromJson(String json, Type type){
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * Storing the data in local by SharedPreferences.
     * @param context
     * @param key store key
     * @param data
     */
    public static void storDataBySP(Context context, String key, String data){
        SharedPreferences preferences = context.getSharedPreferences(Constants.SP_KEY, Context.MODE_PRIVATE);
        preferences.edit().putString(key, data).commit();
    }

    /**
     * Get the data from local by SharedPreferences.
     * @param context
     * @param key store key
     * @return data
     */
    public static String getDataFromSP(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(Constants.SP_KEY, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    /**
     * Get the current time by assign format
     * @param time current time
     * @return the time by assign format
     */
    public static String getCurrentTime(long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
        return simpleDateFormat.format(new Date(time));
    }

    /**
     * Calculate the difference between two times and convert to minutes.
     * @param start_time
     * @param end_time
     * @return difference
     */
    public static int calculateTime(long start_time, long end_time){
        return msToMinutes((int)(end_time - start_time));
    }

    public static Gson getGson() {
        return new GsonBuilder().serializeNulls().create();
    }
}
