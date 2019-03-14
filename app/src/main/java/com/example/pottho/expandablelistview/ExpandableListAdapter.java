package com.example.pottho.expandablelistview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pottho.expandablelistview.model.DataItem;
import com.example.pottho.expandablelistview.model.SubCategoryItem;

import java.util.HashMap;
import java.util.List;

public abstract class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<DataItem> _listDataHeader; // header titles
    // child data in format of header title, child title

    public ExpandableListAdapter(Context context, List<DataItem> listDataHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        notifyDataSetChanged();
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
    public int getChildrenCount(int groupPosition) {
        return _listDataHeader.get(groupPosition).getSubCategory().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    //

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataHeader.get(groupPosition).getSubCategory().get(childPosititon);

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final ViewHolderChild viewHolderChild;
        final SubCategoryItem childText = _listDataHeader.get(groupPosition).getSubCategory().get(childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);

            viewHolderChild = new ViewHolderChild();

            viewHolderChild.tvSubCategoryName = convertView.findViewById(R.id.lblListItem);
            viewHolderChild.cbSubCategory = convertView.findViewById(R.id.cb);
//            viewHolderChild.viewDivider = convertView.findViewById(R.id.viewDivider);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
//        CheckBox cb = convertView.findViewById(R.id.cb);
//        TextView txtListChild = convertView
//                .findViewById(R.id.lblListItem);
        viewHolderChild.cbSubCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                onCheckedItem(buttonView, groupPosition, childPosition, isChecked);
            }
        });

        if (childText.isCheck()) {
            viewHolderChild.cbSubCategory.setChecked(true);
            notifyDataSetChanged();
        } else {
            viewHolderChild.cbSubCategory.setChecked(false);
            notifyDataSetChanged();
        }


        viewHolderChild.tvSubCategoryName.setText(childText.getSubCategoryName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onClickItem(groupPosition, childPosition, view);
            }
        });
        return convertView;
    }

    public abstract void onClickItem(int groupPosition, View view);


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        DataItem headerTitle = (DataItem) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getCategoryName());

        lblListHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem(groupPosition, v);
            }
        });

        return convertView;
    }


//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    public abstract void onCheckedItem(CompoundButton buttonView, int groupPosition, int childPosition, boolean isChecked);

    private class ViewHolderChild {

        TextView tvSubCategoryName;
        CheckBox cbSubCategory;
        View viewDivider;
    }
}
