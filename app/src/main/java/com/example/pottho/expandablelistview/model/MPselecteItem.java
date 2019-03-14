package com.example.pottho.expandablelistview.model;

import java.util.ArrayList;
import java.util.List;

public class MPselecteItem {
    private List<MSelectedItem>selectedItems=new ArrayList<>();

    public List<MSelectedItem> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<MSelectedItem> selectedItems) {
        this.selectedItems = selectedItems;
    }
}
