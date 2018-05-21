package app.regime.com.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.regime.com.R;

/**
 * Created by Muhammad Sajjad on 4/20/2018.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.breakfast_wednesday, R.drawable.breakfast_sunday,
            R.drawable.breakfast_sunday, R.drawable.breakfast_wednesday,
            R.drawable.breakfast_wednesday, R.drawable.breakfast_sunday,
            R.drawable.breakfast_sunday, R.drawable.breakfast_wednesday,
            R.drawable.breakfast_wednesday, R.drawable.breakfast_sunday,
            R.drawable.breakfast_sunday, R.drawable.breakfast_wednesday,
            R.drawable.breakfast_wednesday, R.drawable.breakfast_sunday,
            R.drawable.breakfast_sunday, R.drawable.breakfast_wednesday,
    };

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public GridViewAdapter(Context mainActivity, String[] osNameList, int[] osImages) {
        // TODO Auto-generated constructor stub
        result=osNameList;
        context=mainActivity;
        imageId=osImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView os_text;
        ImageView os_img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.gridview_menu, null);
        holder.os_text =(TextView) rowView.findViewById(R.id.gridview_text);
        holder.os_img =(ImageView) rowView.findViewById(R.id.gridview_image);

        holder.os_text.setText(result[position]);
        holder.os_img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }

}