package industryproject.mit.deliveryoptimise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.map.LegInfo;

/**
 * For displaying the information of the every route.
 */
public class LegRouteAdapter extends RecyclerView.Adapter<LegRouteAdapter.LegRouteViewHolder> implements View.OnClickListener{
    private Context mContext;
    private List<LegInfo> legs;
    private OnItemClickListener listener;

    public LegRouteAdapter(Context context, List<LegInfo> legs){
        mContext = context;
        this.legs = legs;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onItemClick(view, (Integer) view.getTag());
        }
    }

    /**
     * Create the interface for listen the item clicked event.
     */
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    @Override
    public LegRouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Load the layout
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_leg_route, parent, false);
        LegRouteViewHolder viewHolder = new LegRouteViewHolder(view);
        //Set the clicked listener for view.
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LegRouteViewHolder holder, int position) {
        //Initialise the view.
        holder.tv_origin.setText(legs.get(position).getStart_address());
        holder.tv_destination.setText(legs.get(position).getEnd_address());
        holder.tv_distance.setText(legs.get(position).getDistance().getText());
        holder.tv_duration.setText(legs.get(position).getDuration().getText());
        holder.itemView.setTag(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return legs.size();
    }

    class LegRouteViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_origin, tv_destination, tv_distance, tv_duration;

        public LegRouteViewHolder(View itemView) {
            super(itemView);
            tv_origin = itemView.findViewById(R.id.tv_origin);
            tv_destination = itemView.findViewById(R.id.tv_destination);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_duration = itemView.findViewById(R.id.tv_duration);
        }
    }
}
