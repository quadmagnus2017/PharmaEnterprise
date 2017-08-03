package com.quadmagnus.pharma.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quadmagnus.pharma.R;
import com.quadmagnus.pharma.model.companysearchmodel.CompanySearchModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CompanySearchRecyclerAdapter extends RecyclerView.Adapter<CompanySearchRecyclerAdapter.MyViewHolder> {

    Context mContext;
    List<CompanySearchModel> mListClassic;
    private ArrayList<CompanySearchModel> arraylist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;

        public MyViewHolder(View view) {
            super(view);
            /*title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);*/
            txtTitle = (TextView) view.findViewById(R.id.txt_search_company);

        }
    }


    public CompanySearchRecyclerAdapter(List<CompanySearchModel> mListClassic) {
        this.mContext = mContext;
        this.mListClassic = mListClassic;
        this.arraylist = new ArrayList<CompanySearchModel>();
        this.arraylist.addAll(mListClassic);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_search_company, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CompanySearchModel companySearchModel = mListClassic.get(position);
        holder.txtTitle.setText(companySearchModel.getName());
    }

    @Override
    public int getItemCount() {
        return mListClassic.size();
    }

    // Filter Class
    public void filter(String charText) {

        Log.e("charText====>>>", "" + charText);
        charText = charText.toUpperCase(Locale.getDefault());
        mListClassic.clear();
        if (charText.length() == 0) {
            mListClassic.addAll(arraylist);
        } else {
            for (CompanySearchModel wp : arraylist) {
                if (wp.getName().toUpperCase(Locale.getDefault()).contains(charText)) {
                    mListClassic.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}