package com.example.pottho.expandablelistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.pottho.expandablelistview.model.DataItem;
import com.example.pottho.expandablelistview.model.SubCategoryItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private ArrayList<String> selectedStrings;
    private Button btn, btnExpandable;
    private ArrayList<DataItem> arCategory;
    private ArrayList<SubCategoryItem> arSubCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedStrings = new ArrayList<String>();
        // get the listview
        expListView = findViewById(R.id.lvExp);
        btnExpandable = findViewById(R.id.btnExpandable);
        btnExpandable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExpandableActivityAnother.class);
                startActivity(intent);
            }
        });
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkedData = new Gson().toJson(selectedStrings);
                Toast.makeText(
                        getApplicationContext(), checkedData, Toast.LENGTH_SHORT)
                        .show();
            }
        });

        // preparing list data
        setupReferences();
        listAdapter = new ExpandableListAdapter(this, arCategory) {
            @Override
            public void onClickItem(int groupPosition, int childPosition, View view) {
                Toast.makeText(
                        getApplicationContext(),
                        arCategory.get(groupPosition).getCategoryName()
                                + " : "
                                + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onCheckedItem(CompoundButton buttonView, int groupPosition, int childPosition, boolean isChecked) {
                Toast.makeText(
                        getApplicationContext(),
                        arCategory.get(groupPosition).getCategoryName()
                                + " : "
                                + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT)
                        .show();
                if (isChecked) {
//                    buttonView.setChecked(true);
//                    arCategory.get(groupPosition).getSubCategory().get(childPosition).setIsChecked("yes");
                    selectedStrings.add(arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName());
                } else {
//                    buttonView.setChecked(true);
//                    arCategory.get(groupPosition).getSubCategory().get(childPosition).setIsChecked("no");
                    selectedStrings.remove(arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName());
                }

                listAdapter.notifyDataSetChanged();
            }


        };

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
//        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // Toast.makeText(getApplicationContext(),
//                // "Group Clicked " + listDataHeader.get(groupPosition),
//                // Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        // Listview Group expanded listener
//        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });

        // Listview Group collasped listener
//        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        arCategory.get(groupPosition).getCategoryName()
                                + " : "
                                + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    private void setupReferences() {

        arCategory = new ArrayList<>();
        arSubCategory = new ArrayList<>();

        DataItem dataItem = new DataItem();
        dataItem.setCategoryId("1");
        dataItem.setCategoryName("Top 250");

        arSubCategory = new ArrayList<>();
        for (int i = 1; i < 6; i++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(i));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Top 250: " + i);
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("2");
        dataItem.setCategoryName("Now Showing");
        arSubCategory = new ArrayList<>();
        for (int j = 1; j < 6; j++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(j));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Now Showing: " + j);
            arSubCategory.add(subCategoryItem);
        }
        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);

        dataItem = new DataItem();
        dataItem.setCategoryId("3");
        dataItem.setCategoryName("Coming Soon..");
        arSubCategory = new ArrayList<>();
        for (int k = 1; k < 6; k++) {

            SubCategoryItem subCategoryItem = new SubCategoryItem();
            subCategoryItem.setCategoryId(String.valueOf(k));
            subCategoryItem.setIsChecked(ConstantManager.CHECK_BOX_CHECKED_FALSE);
            subCategoryItem.setSubCategoryName("Coming Soon..: " + k);
            arSubCategory.add(subCategoryItem);
        }

        dataItem.setSubCategory(arSubCategory);
        arCategory.add(dataItem);


        Log.e("TAG", "setupReferences: " + arCategory.size());


    }
}
