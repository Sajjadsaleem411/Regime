package app.regime.com.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import app.regime.com.R;
import app.regime.com.api.RetroProvider;
import app.regime.com.model.Title;

/**
 * Created by Muhammad Sajjad on 5/26/2018.
 */

public class TitleHorizontalAdapter extends RecyclerView.Adapter<TitleHorizontalAdapter.MyViewHolder> {


    List<Title> horizontalList = Collections.emptyList();
    Context context;
    CallbackTitle callbackTitle;
    public interface CallbackTitle {

        void ClickChildView(int index);


    }

    public TitleHorizontalAdapter(List<Title> horizontalList, Context context,CallbackTitle callbackTitle) {
        this.horizontalList = horizontalList;
        this.context = context;
        this.callbackTitle=callbackTitle;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        View view;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title_item);
            this.view = (View) view.findViewById(R.id.title_bt_view);
        }
    }


    @Override
    public TitleHorizontalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meals_title_item, parent, false);

        return new TitleHorizontalAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final TitleHorizontalAdapter.MyViewHolder holder, final int position) {
    //    Picasso.with(context).load(RetroProvider.BASE_URL+""+horizontalList.get(position)).into(holder.imageView);
        //   Glide.with(context).load(RetroProvider.BASE_URL+""+horizontalList.get(position)).into(holder.imageView);
        holder.title.setText(horizontalList.get(position).getName());
        if(horizontalList.get(position).isSelect()){
            holder.view.setVisibility(View.VISIBLE);
        }
        else {
            holder.view.setVisibility(View.GONE);

        }
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                callbackTitle.ClickChildView(position);
              /*  String list = horizontalList.get(position).txt.toString();
                Toast.makeText(context, list, Toast.LENGTH_SHORT).show();
           */
            }

        });

    }


    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}

