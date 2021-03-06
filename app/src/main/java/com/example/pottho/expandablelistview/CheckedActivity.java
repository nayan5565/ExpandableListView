package com.example.pottho.expandablelistview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.pottho.expandablelistview.model.MSelectedItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CheckedActivity extends AppCompatActivity {
    private TextView tvParent, tvChild;
    private List<String> selectedItem;
    private List<MSelectedItem> mSelectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked);
        selectedItem = new ArrayList<>();
        mSelectedItems = new ArrayList<>();

        tvParent = findViewById(R.id.parent);
        tvChild = findViewById(R.id.child);


        for (int i = 0; i < MyCategoriesExpandableListAdapter.parentItems.size(); i++) {

            String isChecked = MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.IS_CHECKED);

            if (isChecked.equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
                tvParent.setText(tvParent.getText() + MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME));
            }

            for (int j = 0; j < MyCategoriesExpandableListAdapter.childItems.get(i).size(); j++) {

                String isChildChecked = MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.IS_CHECKED);

                if (isChildChecked.equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
//                    tvChild.setText(tvChild.getText() + " , " + MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME) + " " + (j + 1));
                    selectedItem.add(MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME) + " " + (j + 1));
                    MSelectedItem mSelectedItem = new MSelectedItem();
                    mSelectedItem.setName(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_CATEGORY_NAME));
                    mSelectedItem.setEmail(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_EMAIL));
                    mSelectedItem.setPhone(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_PHONE));
                    mSelectedItems.add(mSelectedItem);
                }

            }

        }

        String checkedData = new Gson().toJson(mSelectedItems);
        tvChild.setText(checkedData);

    }
}
