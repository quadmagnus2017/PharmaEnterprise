package com.quadmagnus.pharma.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.model.classicsearchmodel.ClassicSearchModel;
import com.quadmagnus.pharma.model.patentsearchmodel.PatentSearchModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by mohsin on 6/7/17.
 */

public class PatentSearchAdapter extends BaseAdapter {


    Context mContext;
    List<PatentSearchModel> mListClassic;
    private ArrayList<PatentSearchModel> arraylist;

    public PatentSearchAdapter(Context mContext, List<PatentSearchModel> mListClassic) {
        this.mContext = mContext;
        this.mListClassic = mListClassic;
        this.arraylist = new ArrayList<PatentSearchModel>();
        this.arraylist.addAll(mListClassic);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mListClassic.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return mListClassic.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_patent, null);
            // Locate the TextViews in listview_item.xml
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title_patent);
            holder.txtQuantity = (TextView) convertView.findViewById(R.id.txt_quantity_patent);
            holder.txtCompany = (TextView) convertView.findViewById(R.id.txt_comapny_patent);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // Set the results into TextViews
        holder.txtTitle.setText(mListClassic.get(position).getName());
        holder.txtQuantity.setText(mListClassic.get(position).getQuantity());
        holder.txtCompany.setText(mListClassic.get(position).getCompany());


        return convertView;
    }

    public class ViewHolder {
        TextView txtTitle, txtQuantity, txtCompany;

    }

    // Filter Class
    public void filter(String charText) {

        Log.e("charText====>>>", "" + charText);
        charText = charText.toUpperCase(Locale.getDefault());
        mListClassic.clear();
        if (charText.length() == 0) {
            mListClassic.addAll(arraylist);
        } else {
            for (PatentSearchModel wp : arraylist) {
                if (wp.getName().toUpperCase(Locale.getDefault()).contains(charText)) {
                    mListClassic.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
