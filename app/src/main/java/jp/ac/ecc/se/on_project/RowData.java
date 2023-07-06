package jp.ac.ecc.se.on_project;

public class RowData {
    String itemName;
    String point;

    RowData(String itemName, String point){
        this.itemName = itemName;
        this.point = point;
    }

    public String getItemName() {
        return itemName;
    }

    public String getPoint() {
        return point;
    }
}
