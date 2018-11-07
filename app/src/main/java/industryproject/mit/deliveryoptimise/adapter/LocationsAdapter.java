package industryproject.mit.deliveryoptimise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;

/**
 * For display the delivery locations
 */
public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>{
    private DeliveryLocations locations;
    private Context mContext;

    public LocationsAdapter(Context context, DeliveryLocations locations){
        this.locations = locations;
        mContext = context;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Load the layout
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_location, parent, false);
        LocationViewHolder viewHolder = new LocationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        holder.tv_location.setText(locations.getWayPoints().get(position).toString());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return locations.getWayPoints().size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder{
        TextView tv_location;

        public LocationViewHolder(View itemView) {
            super(itemView);
            tv_location = itemView.findViewById(R.id.tv_location);
        }
    }
}


