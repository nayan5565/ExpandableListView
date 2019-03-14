package com.example.pottho.expandablelistview;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.pottho.expandablelistview.model.DataItem;
import com.example.pottho.expandablelistview.model.MPselecteItem;
import com.example.pottho.expandablelistview.model.MSelectedItem;
import com.example.pottho.expandablelistview.model.SubCategoryItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableActivityAnother extends AppCompatActivity {

    private Button btn;
    private ExpandableListView lvCategory;

    private ArrayList<DataItem> arCategory;
    private ArrayList<SubCategoryItem> arSubCategory;
    public static List<String> seletecedItem;
    public static List<MPselecteItem> mPselecteItems;
    private List<MSelectedItem> mSelectedItems, mSelectedItems2, mSelectedItems3;

    private ArrayList<HashMap<String, String>> parentItems;
    private ArrayList<ArrayList<HashMap<String, String>>> childItems;
    private MyCategoriesExpandableListAdapter myCategoriesExpandableListAdapter;
    private String startIme = "", endTime = "";
    private int groupPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        btn = findViewById(R.id.btn);
        mSelectedItems3 = new ArrayList<>();
        mSelectedItems2 = new ArrayList<>();
        mSelectedItems = new ArrayList<>();
        seletecedItem = new ArrayList<>();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popup();
//                Intent intent = new Intent(ExpandableActivityAnother.this, CheckedActivity.class);
//                startActivity(intent);
            }
        });
        setData();

        setupReferences();
    }

    private void setData() {
        MSelectedItem mSelectedItem = new MSelectedItem();
        mSelectedItem.setPhone("045453435");
        mSelectedItem.setEmail("sa@gmail.com");
        mSelectedItem.setName("nayan");
        mSelectedItems2.add(mSelectedItem);
    }

    private void getCheckedData() {
        mSelectedItems.clear();
        for (int i = 0; i < MyCategoriesExpandableListAdapter.parentItems.size(); i++) {

            String isChecked = MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.IS_CHECKED);

            if (isChecked.equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
//                tvParent.setText(tvParent.getText() + MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME));
            }

            for (int j = 0; j < MyCategoriesExpandableListAdapter.childItems.get(i).size(); j++) {

                String isChildChecked = MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.IS_CHECKED);

                if (isChildChecked.equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
//                    tvChild.setText(tvChild.getText() + " , " + MyCategoriesExpandableListAdapter.parentItems.get(i).get(ConstantManager.Parameter.CATEGORY_NAME) + " " + (j + 1));

//                    MPselecteItem mPselecteItem = new MPselecteItem();
//                    mPselecteItem.setSelectedItems(groupPosition).setStartTime(startIme);
//                    mPselecteItem.getSelectedItems().get(groupPosition).setEndTime(endTime);
//                    mPselecteItem.getSelectedItems().get(groupPosition).setName(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_CATEGORY_NAME));
//                    mPselecteItem.getSelectedItems().get(groupPosition).setEmail(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_EMAIL));
//                    mPselecteItem.getSelectedItems().get(groupPosition).setPhone(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_PHONE));
//                    mPselecteItem.setSelectedItems(mPselecteItem.getSelectedItems());
//                    mPselecteItems.add(mPselecteItem);


                    MSelectedItem mSelectedItem = new MSelectedItem();
                    mSelectedItem.setName(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_CATEGORY_NAME));
                    mSelectedItem.setEmail(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_EMAIL));
                    mSelectedItem.setPhone(MyCategoriesExpandableListAdapter.childItems.get(i).get(j).get(ConstantManager.Parameter.SUB_PHONE));
                    mSelectedItem.setStartTime(startIme);
                    mSelectedItem.setEndTime(endTime);
                    mSelectedItems.add(mSelectedItem);
                }

            }

        }


    }

    private void Popup() {
        getCheckedData();
        mSelectedItems3.clear();
//        setData();
//        mSelectedItems.addAll(mPselecteItems.get(groupPosition).getSelectedItems());
        mSelectedItems.addAll(mSelectedItems2);
        mSelectedItems3.addAll(mSelectedItems);
        String checkedData = new Gson().toJson(mSelectedItems3);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup);
        TextView tvText = dialog.findViewById(R.id.tvText);
        tvText.setText(checkedData);
        dialog.show();

    }

    private void timePopup() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup2);
        final EditText edtStartTime = dialog.findViewById(R.id.edtStartTime);
        final EditText edtEndTime = dialog.findViewById(R.id.edtEndTime);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIme = edtStartTime.getText().toString();
                endTime = edtEndTime.getText().toString();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void setupReferences() {

        lvCategory = findViewById(R.id.lvCategory);
        arCategory = new ArrayList<>();
        arSubCategory = new ArrayList<>();
        parentItems = new ArrayList<>();
        childItems = new ArrayList<>();

        DataItem dataItem = new DataItem();
        dataItem.setCategoryId("1");
        dataItem.setCategoryName("Adventure");

        arSubCategory = new ArrayList<>();
        for (int i = 1; i < 6; i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Adventure: " + i);
            subCategoryItem.setPhone("0167233001" + i);
            subCategoryItem.setEmail("Adventure" + i + "gmail.com");
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("2");
        dataItem.setCategoryName("Art");
        arSubCategory = new ArrayList<>();
        for (int j = 1; j < 6; j++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(j));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Art: " + j);
            subCategoryItem.setPhone("0191355596" + j);
            subCategoryItem.setEmail("Art" + j + "gmail.com");
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("3");
        dataItem.setCategoryName("Cooking");
        arSubCategory = new ArrayList<>();
        for (int k = 1; k < 6; k++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(k));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Cooking: " + k);
            subCategoryItem.setPhone("0171277881" + k);
            subCategoryItem.setEmail("Cooking" + k + "gmail.com");

            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        Log.e("TAG", "setupReferences: " + arCategory.size());

        for (DataItem data : arCategory) {
//                        Log.i("Item id",item.id);
            ArrayList<HashMap<String, String>> childArrayList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> mapParent = new HashMap<String, String>();

            mapParent.put(ConstantManager.Parameter.CATEGORY_ID, data.getCategoryId());
            mapParent.put(ConstantManager.Parameter.CATEGORY_NAME, data.getCategoryName());

            int countIsChecked = 0;
            for (SubCategoryItem subCategoryItem : data.getSubCategory()) {

                HashMap<String, String> mapChild = new HashMap<String, String>();
                mapChild.put(ConstantManager.Parameter.SUB_ID, subCategoryItem.getSubId());
                mapChild.put(ConstantManager.Parameter.SUB_CATEGORY_NAME, subCategoryItem.getSubCategoryName());
                mapChild.put(ConstantManager.Parameter.SUB_EMAIL, subCategoryItem.getEmail());
                mapChild.put(ConstantManager.Parameter.SUB_PHONE, subCategoryItem.getPhone());
                mapChild.put(ConstantManager.Parameter.CATEGORY_ID, subCategoryItem.getCategoryId());
                mapChild.put(ConstantManager.Parameter.IS_CHECKED, subCategoryItem.getIsChecked());

                if (subCategoryItem.getIsChecked().equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {

                    countIsChecked++;
                }
                childArrayList.add(mapChild);
            }

            if (countIsChecked == data.getSubCategory().size()) {

                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_TRUE);
            } else {
                data.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            }

            mapParent.put(ConstantManager.Parameter.IS_CHECKED, data.getIsChecked());
            childItems.add(childArrayList);
            parentItems.add(mapParent);

        }

        ConstantManager.parentItems = parentItems;
        ConstantManager.childItems = childItems;

        myCategoriesExpandableListAdapter = new MyCategoriesExpandableListAdapter(this, parentItems, childItems, true) {
            @Override
            public void onClickItem(int groupPosition, View view) {
                timePopup();
            }
//            @Override
//            public void onCheckedItem(CompoundButton buttonView, boolean isChecked, int groupPosition) {
//                if (buttonView.isChecked()) {
//                    parentItems.get(groupPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
//
//                    for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
//                        childItems.get(groupPosition).get(i).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
//                        seletecedItem.add(childItems.get(groupPosition).get(i).get(ConstantManager.Parameter.SUB_CATEGORY_NAME));
//                    }
//                    notifyDataSetChanged();
//
//                } else {
//                    parentItems.get(groupPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
//                    for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
//                        childItems.get(groupPosition).get(i).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
//                        seletecedItem.remove(childItems.get(groupPosition).get(i).get(ConstantManager.Parameter.SUB_CATEGORY_NAME));
//                    }
//                    notifyDataSetChanged();
//                }
//            }

//            @Override
//            public void onCheckedItem(CompoundButton buttonView, int groupPosition, int childPosition, boolean isChecked) {
//                if (buttonView.isChecked()) {
//                    count = 0;
//                    seletecedItem.add(childItems.get(groupPosition).get(childPosition).get(ConstantManager.Parameter.SUB_CATEGORY_NAME));
//                    childItems.get(groupPosition).get(childPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
//                    notifyDataSetChanged();
//                } else {
//                    count = 0;
//                    seletecedItem.remove(childItems.get(groupPosition).get(childPosition).get(ConstantManager.Parameter.SUB_CATEGORY_NAME));
//                    childItems.get(groupPosition).get(childPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
//                    notifyDataSetChanged();
//                }

//                for (int i = 0; i < childItems.get(groupPosition).size(); i++) {
//                    if (childItems.get(groupPosition).get(i).get(ConstantManager.Parameter.IS_CHECKED).equalsIgnoreCase(ConstantManager.CHECK_BOX_CHECKED_TRUE)) {
//                        count++;
//                    }
//                }
//                if (count == childItems.get(groupPosition).size()) {
//                    parentItems.get(groupPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_TRUE);
//                    notifyDataSetChanged();
//                } else {
//                    parentItems.get(groupPosition).put(ConstantManager.Parameter.IS_CHECKED, ConstantManager.CHECK_BOX_CHECKED_FALSE);
//                    notifyDataSetChanged();
//                }
//
//
//                ConstantManager.childItems = childItems;
//                ConstantManager.parentItems = parentItems;
//            }
        };
        lvCategory.setAdapter(myCategoriesExpandableListAdapter);
    }
}
