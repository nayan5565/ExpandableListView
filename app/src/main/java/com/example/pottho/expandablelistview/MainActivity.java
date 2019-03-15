package com.example.pottho.expandablelistview;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pottho.expandablelistview.model.DataItem;
import com.example.pottho.expandablelistview.model.MSelectedItem;
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
    private String startIme = "", endTime = "";
    private List<MSelectedItem> mSelectedItems, mSelectedItems2, mSelectedItems3;
    private int indexPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedStrings = new ArrayList<String>();
        mSelectedItems3 = new ArrayList<>();
        mSelectedItems2 = new ArrayList<>();
        mSelectedItems = new ArrayList<>();
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
                Popup();
            }
        });

        // preparing list data
        setupReferences();
        listAdapter = new ExpandableListAdapter(this, arCategory) {
            @Override
            public void onClickItem(int groupPosition, View view) {
//                childPopup(groupPosition);
//                Toast.makeText(
//                        getApplicationContext(),
//                        arCategory.get(groupPosition).getCategoryName()
//                                + " : "
//                                + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onClickItem(int groupPosition, int childPosition, View view) {
                if (arCategory.get(groupPosition).getSubCategory().get(childPosition).isCheck()) {

                    arCategory.get(groupPosition).getSubCategory().get(childPosition).setCheck(false);
                    selectedStrings.remove(arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName());
                    if (isPhone(arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName())) {
                        mSelectedItems.remove(indexPos);
                    }

                    Toast.makeText(MainActivity.this, "Removed: " + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT).show();
                } else {
                    MSelectedItem mSelectedItem = new MSelectedItem();
                    mSelectedItem.setName(arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName());
                    mSelectedItem.setEmail(arCategory.get(groupPosition).getSubCategory().get(childPosition).getEmail());
                    mSelectedItem.setPhone(arCategory.get(groupPosition).getSubCategory().get(childPosition).getPhone());
                    mSelectedItem.setStartTime(startIme);
                    mSelectedItem.setEndTime(endTime);
                    mSelectedItems.add(mSelectedItem);
                    arCategory.get(groupPosition).getSubCategory().get(childPosition).setCheck(true);
                    selectedStrings.add(arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName());
                    Toast.makeText(MainActivity.this, "Added: " + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCheckedItem(CompoundButton buttonView, int groupPosition, int childPosition, boolean isChecked) {

                if (buttonView.isChecked()) {
                    arCategory.get(groupPosition).getSubCategory().get(childPosition).setCheck(true);
                    selectedStrings.add(arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName());
                    Toast.makeText(
                            getApplicationContext(),
                            arCategory.get(groupPosition).getCategoryName()
                                    + " : "
                                    + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT)
                            .show();
//                    notifyDataSetChanged();
                } else {
                    arCategory.get(groupPosition).getSubCategory().get(childPosition).setCheck(false);
                    selectedStrings.remove(arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName());
                    Toast.makeText(
                            getApplicationContext(),
                            arCategory.get(groupPosition).getCategoryName()
                                    + " : "
                                    + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT)
                            .show();
//                    notifyDataSetChanged();
                }

//                listAdapter.notifyDataSetChanged();
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
//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        arCategory.get(groupPosition).getCategoryName()
//                                + " : "
//                                + arCategory.get(groupPosition).getSubCategory().get(childPosition).getSubCategoryName(), Toast.LENGTH_SHORT)
//                        .show();
//                return false;
//            }
//        });
    }

    private boolean isPhone(String phone) {
        for (int i = 0; i < mSelectedItems.size(); i++) {
            if (phone.equals(mSelectedItems.get(i).getName())) {
                indexPos = i;
                return true;
            }
        }

        return false;
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

    private void Popup() {
        String checkedData = new Gson().toJson(mSelectedItems);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup);
        TextView tvText = dialog.findViewById(R.id.tvText);
        tvText.setText(checkedData);
        dialog.show();

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

    private void childPopup(final int pos) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.child_popup);

        RecyclerView rvSub = dialog.findViewById(R.id.rvSub);
        rvSub.setLayoutManager(new LinearLayoutManager(this));
        TextView tvParent = dialog.findViewById(R.id.tvParent);

        AdChildItem adChildItem = new AdChildItem(this) {
            @Override
            public void onClickItem(int itemPosition, View view) {
                if (arCategory.get(pos).getSubCategory().get(itemPosition).isCheck()) {
                    arCategory.get(pos).getSubCategory().get(itemPosition).setCheck(false);
                    selectedStrings.remove(arCategory.get(pos).getSubCategory().get(itemPosition).getSubCategoryName());
                    Toast.makeText(MainActivity.this, "Removed: " + arCategory.get(pos).getSubCategory().get(itemPosition).getSubCategoryName(), Toast.LENGTH_SHORT).show();
                } else {
                    arCategory.get(pos).getSubCategory().get(itemPosition).setCheck(true);
                    selectedStrings.add(arCategory.get(pos).getSubCategory().get(itemPosition).getSubCategoryName());
                    Toast.makeText(MainActivity.this, "Added: " + arCategory.get(pos).getSubCategory().get(itemPosition).getSubCategoryName(), Toast.LENGTH_SHORT).show();
                }

            }


        };
        rvSub.setAdapter(adChildItem);
        Log.e("ChildItem", "size: " + arCategory.get(pos).getSubCategory().size());
        if (arCategory.get(pos).getSubCategory().size() > 0) {
            adChildItem.addData(arCategory.get(pos).getSubCategory());
            tvParent.setText(arCategory.get(pos).getCategoryName());
        }

        Button btnOk = dialog.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }
}
