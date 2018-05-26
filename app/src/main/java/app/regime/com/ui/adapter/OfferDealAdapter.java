package app.regime.com.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.regime.com.R;
import app.regime.com.model.Category;

/**
 * Created by Taha on 3/11/2018.
 */

public class OfferDealAdapter extends RecyclerView.Adapter<OfferDealAdapter.ViewHolder> {

    private Context context;
    private List<Category> category = new ArrayList<>();

    public OfferDealAdapter(Context context, List<Category> category) {
        this.context = context;
        this.category = category;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cardView = inflater.inflate(R.layout.item_child_offer_deals, null, false);
        ViewHolder viewHolder = new ViewHolder(cardView);
        viewHolder.modelName = (TextView) cardView.findViewById(R.id.textViewCategory);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        TextView modelTextView = (TextView) holder.modelName;
        modelTextView.setText(category.get(position).getCategoryName());


    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView modelName, textViewTotalCost;
        RecyclerView recyclerViewFlooringChildItemMaterialType;

        public ViewHolder(View itemView) {
            super(itemView);
            modelName = (TextView) itemView.findViewById(R.id.textViewCategory);
//            recyclerViewFlooringChildItemMaterialType = (RecyclerView) itemView.findViewById(R.id.recyclerViewFlooringChildItemMaterialType);
        }
    }

}
