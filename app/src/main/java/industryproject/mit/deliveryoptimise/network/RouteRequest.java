package industryproject.mit.deliveryoptimise.network;

import android.content.Context;

import com.android.tu.loadingdialog.LoadingDialog;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;
import industryproject.mit.deliveryoptimise.network.service.RouteService;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * For the route request.
 */
public class RouteRequest {
    private RequestCallBack mCallBack; //The call back of the route request
    private LoadingDialog dialog;
    private String origin;
    private String destination;
    private String waypoints;

    public RouteRequest(Context context, RequestCallBack callBack, String origin, String destination, String waypoints){
        mCallBack = callBack;
        this.origin = origin;
        this.destination = destination;
        this.waypoints = "optimize:true|" + waypoints;
        this.dialog = GeneralUtil.getWaitDialog(context, "Waiting...");
    }

    public void getRequest(){
        dialog.show();
        //Use the Retrofit to send the request.
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.GoogleMap_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RouteService request = retrofit.create(RouteService.class);
        Call<RouteResponse> call = request.getRouteInfo(origin, destination, waypoints, Constants.DEPARTURE_TIME, Constants.APIKEY, Constants.OPTIMAIZEWAYPOINTS);
        call.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
                //The request successful and call the callback method.
                dialog.dismiss();
                mCallBack.requestCallBack(response.body());
            }

            @Override
            public void onFailure(Call<RouteResponse> call, Throwable t) {
                //The request failed and call the callback method.
                dialog.dismiss();
                mCallBack.requestFailure(call);
            }
        });
    }
}
