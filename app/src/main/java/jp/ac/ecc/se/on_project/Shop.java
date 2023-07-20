package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //変数の初期化
        RecyclerView normal_rec = findViewById(R.id.recyclerView);
        normal_rec.setLayoutManager(new LinearLayoutManager(this));
        Button kagubtn = findViewById(R.id.kagubtn);
        Button fukubtn = findViewById(R.id.fukubtn);
        Button kaubtn = findViewById(R.id.kaubtn);
        ImageView imgView = findViewById(R.id.imgView);

        //プリファレンスからユーザID取得

        TextView pointTextView = findViewById(R.id.point);
        int currentPoint = Integer.parseInt(pointTextView.getText().toString());

        // Decrease the point value and update the TextView
        currentPoint--;
        pointTextView.setText(String.valueOf(currentPoint));

        //↑sample用のデータ
        //↓本番用(DB接続)のデータ

        TestOpenHelper helper = new TestOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //アイテム表のINAME,PRICEを取得
        String sql = "SELECT INAME,PRICE,TYPE,IMAGE FROM ITEM WHERE ITEM_ID NOT IN (SELECT ITEM_ID FROM USERITEM WHERE USER_ID = 'aki123')";
        Cursor cursor = db.rawQuery(sql,null);

        //INAMEの値だけ格納するリストを作成
        List<String> ItemName = new ArrayList<String>();
        //PRICEの値だけ格納するリストを作成
        List<Integer> ItemPrice = new ArrayList<Integer>();
        //TYPEの値だけ格納するリストを作成
        List<Integer> ItemType = new ArrayList<Integer>();
        //IMAGEの値だけ格納するリストを作成
        List<String> ItemImage = new ArrayList<String>();
        //何個レコードがあるか数えるカウント
        int cnt=0;

        if(cursor.moveToFirst()){ //1レコードずつ取得
            do {
                cnt++;
                ItemName.add((cursor.getString(0)));
                ItemPrice.add((cursor.getInt(1)));
                ItemType.add((cursor.getInt(2)));
                ItemImage.add((cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        cursor.close();

        //最終的にRecyclerviewに表示させるためのデータを突っ込むリスト(家具)
        List<RowData> furniture_List = new ArrayList<>();
        //最終的にRecyclerviewに表示させるためのデータを突っ込むリスト(服)
        List<RowData> clothes_ItemList = new ArrayList<>();


        //取得したレコードをTypeごとに家具/服で振り分ける
        for(int i=0;i<cnt;i++){
            System.out.println("アイテムの種類：" + ItemType.get(i));
            System.out.println("アイテムのイメージ：" + ItemImage.get(i));
            switch (ItemType.get(i)){
                case 1:
                    furniture_List.add(new RowData(ItemName.get(i),ItemPrice.get(i),ItemImage.get(i)));
                    break;
                case 2:
                    clothes_ItemList.add(new RowData(ItemName.get(i),ItemPrice.get(i),ItemImage.get(i)));
                    break;
            }
        }

        ProductRecycleAdapter flAdapter = new ProductRecycleAdapter(furniture_List, imgView, this);
        ProductRecycleAdapter clAdapter = new ProductRecycleAdapter(clothes_ItemList, imgView, this);

        kagubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal_rec.setAdapter(flAdapter);
            }
        });
        
        fukubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal_rec.setAdapter(clAdapter);
            }
        });

        kaubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("Item_Table",Context.MODE_PRIVATE,null);
            }
        });
    }
}