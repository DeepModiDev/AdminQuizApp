package com.deepmodi.adminquizapp.Model;

public class Category {

    private String itemName;
    private String itemdesc;
    private String itemImage;
    private String itemId;

    public Category() {
    }

    public Category( String itemId,String itemName, String itemdesc) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemdesc = itemdesc;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
