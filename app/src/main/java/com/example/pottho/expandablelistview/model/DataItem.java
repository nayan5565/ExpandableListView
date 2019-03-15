package com.example.pottho.expandablelistview.model;

import java.util.List;

public class DataItem {
    private String categoryId;
    private String categoryName;
    private String isChecked = "NO";
    private List<SubCategoryItem> subCategory;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public DataItem() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public List<SubCategoryItem> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategoryItem> subCategory) {
        this.subCategory = subCategory;
    }
}
