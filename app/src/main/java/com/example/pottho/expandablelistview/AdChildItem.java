package com.example.pottho.expandablelistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.pottho.expandablelistview.model.SubCategoryItem;

import java.util.ArrayList;
import java.util.List;

public abstract class AdChildItem extends RecyclerView.Adapter<AdChildItem.MyViewHolder> {
    private List<SubCategoryItem> subCategoryItems;
    private Context context;
    private LayoutInflater inflater;

    public AdChildItem(Context context) {
        subCategoryItems = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<SubCategoryItem> subCategoryItems) {
        this.subCategoryItems = subCategoryItems;
        notifyDataSetChanged();
    }

    @Override
    public AdChildItem.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_child, parent, false);
        AdChildItem.MyViewHolder holder = new AdChildItem.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AdChildItem.MyViewHolder holder, int position) {
        SubCategoryItem subCategoryItem = subCategoryItems.get(position);
        if (subCategoryItem != null) {
            holder.tvName.setText(subCategoryItem.getSubCategoryName());
            if (subCategoryItem.isCheck()) {
                holder.checkbox.setChecked(true);
            } else {
                holder.checkbox.setChecked(false);
            }
        }


    }

    @Override
    public int getItemCount() {
        return subCategoryItems.size();
    }

    public abstract void onClickItem(int itemPosition, View view);

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private CheckBox checkbox;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            checkbox = itemView.findViewById(R.id.checkbox);
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem(getAdapterPosition(), v);
                }
            });


        }
    }
}
