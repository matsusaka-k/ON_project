package jp.ac.ecc.se.on_project;

public class RowData {
    String itemName;
    int point;
    String itemImage;

    RowData(String itemName, int point, String itemImage){
        this.itemName = itemName;
        this.point = point;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public int getPoint() {
        return point;
    }

    public String getItemImage() {
        return itemImage;
    }
}
