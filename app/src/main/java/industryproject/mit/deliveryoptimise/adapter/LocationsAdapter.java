package industryproject.mit.deliveryoptimise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.UAddress;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationViewHolder> implements View.OnClickListener{
    private List<UAddress> addresses;
    private Context mContext;
    private int type;
    private OnItemClickListener listener;

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onItemClick(view, (int) view.getTag());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public LocationsAdapter(Context context, List<UAddress> addresses){
        this.addresses = addresses;
        mContext = context;
        this.type = type;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_location, parent, false);
        LocationViewHolder viewHolder = new LocationViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        holder.tv_location.setText(addresses.get(position).toString());
        holder.tv_location_type.setText(addresses.get(position).getTypeName());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder{
        TextView tv_location, tv_location_type;

        public LocationViewHolder(View itemView) {
            super(itemView);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_location_type = itemView.findViewById(R.id.tv_location_type);
        }
    }
}


