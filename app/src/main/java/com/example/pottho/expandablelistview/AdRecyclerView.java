package com.example.pottho.expandablelistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pottho.expandablelistview.model.SubCategoryItem;

import java.util.ArrayList;
import java.util.List;

public class AdRecyclerView extends RecyclerView.Adapter<AdRecyclerView.MyViewHolder> {
    private List<SubCategoryItem> mContacts;
    private Context context;
    private LayoutInflater inflater;

    public AdRecyclerView(Context context) {
        mContacts = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<SubCategoryItem> mContacts) {
        this.mContacts = mContacts;
        notifyDataSetChanged();
    }

    @Override
    public AdRecyclerView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        AdRecyclerView.MyViewHolder holder = new AdRecyclerView.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdRecyclerView.MyViewHolder holder, int position) {
        SubCategoryItem mContact = mContacts.get(position);
        if (mContact != null) {
            holder.tvName.setText(mContact.getSubCategoryName());


        }

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.lblListItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });


        }
    }
}

