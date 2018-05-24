package app.regime.com.ui.adapter;

/**
 * Created by Administrator on 4/4/2018.
 */


import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import app.regime.com.R;
import app.regime.com.model.Category;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    //static public boolean notify;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    int size;


/*
    public interface UpdateList {
        void Notify(int index);

        void ClickGroupView(int index);

        void delete(int position);

        void ChangeQuantity(int position);

    }
*/

    private Context _context;
    String fragment;
    private List<Category> _listDataHeader; // header titles
    // child data in format of header title, child title

    public ExpandableListAdapter(Context context, List<Category> listDataHeader, String fragment) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.fragment = fragment;
        //   notifyDataSetChanged();

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataHeader.get(groupPosition).items.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, final ViewGroup parent) {
        Log.d("", "" + getChild(groupPosition, childPosition));

        //    final String childText = (String) getChild(groupPosition, childPosition);
        String item = _listDataHeader.get(groupPosition).items.get(childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.meal_list_item, null);
        }

        if (item != null) {
            TextView name = (TextView) convertView
                    .findViewById(R.id.tv_name_item);
            TextView rank = (TextView) convertView
                    .findViewById(R.id.tv_rank);
            ImageView check1 = (ImageView) convertView.findViewById(R.id.img_check1);
            ImageView check2 = (ImageView) convertView.findViewById(R.id.img_check1);
            if (fragment.equals("Details")) {
                rank.setVisibility(View.GONE);
                check2.setVisibility(View.VISIBLE);
            }
            name.setText(item);
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataHeader.get(groupPosition).items.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (_listDataHeader.size() > groupPosition)
            return this._listDataHeader.get(groupPosition);
        else
            return null;
    }

    @Override
    public int getGroupCount() {

        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded,
                             View convertView, final ViewGroup parent) {
        final ExpandableListView listView = (ExpandableListView) parent;

        final Category headerTitle = (Category) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.meal_list_group, null);
        }
        TextView name = (TextView) convertView
                .findViewById(R.id.tv_title_group);

        name.setText(headerTitle.getCategoryName());
       /* if(_listDataHeader.size()>groupPosition)
        if (_listDataHeader.get(groupPosition).expand) {

            if (name != null)
                name.setBackgroundColor(ContextCompat.getColor(_context, R.color.light_blue));
            if (headerTitle.getModifiers().size() > 0)
                listView.expandGroup(groupPosition);
        } else {
            if (name != null)
                name.setBackgroundColor(ContextCompat.getColor(_context, R.color.gray));
            listView.collapseGroup(groupPosition);
        }
        price.setText(decimalFormat.format(headerTitle.getPrice()));
        name.setTypeface(null, Typeface.BOLD);
        name.setText(headerTitle.getName());
        quantity.setText("" + headerTitle.getQuantity());

        name.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             *//*   if (!isExpanded)
                    listView.expandGroup(groupPosition);
                else
                    listView.collapseGroup(groupPosition);*//*
                updateList.ClickGroupView(groupPosition);
                //  expandView(parent, groupPosition, v, isExpanded);

            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callback.show_modifier(headerTitle, groupPosition);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateList.delete(groupPosition);
            }
        });
        quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList.ChangeQuantity(groupPosition);
            }
        });*/
        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class ViewHolder {
        private HashMap<Integer, View> storedViews = new HashMap<Integer, View>();

        public ViewHolder() {
        }

        /**
         * @param view The view to add; to reference this view later, simply refer to its id.
         * @return This instance to allow for chaining.
         */
        public ViewHolder addView(View view) {
            int id = view.getId();
            storedViews.put(id, view);
            return this;
        }

        public View getView(int id) {
            return storedViews.get(id);
        }
    }
}