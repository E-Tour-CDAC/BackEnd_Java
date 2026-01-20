package com.example.dto;

import java.util.List;

public class CategoryDTO {

    private String catCode;
    private String categoryName;
    private String imagePath;
    private Boolean jumpFlag;

    // only present if jumpFlag = false
    private List<CategoryDTO> children;

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getJumpFlag() {
        return jumpFlag;
    }

    public void setJumpFlag(Boolean jumpFlag) {
        this.jumpFlag = jumpFlag;
    }

    public List<CategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDTO> children) {
        this.children = children;
    }
}
