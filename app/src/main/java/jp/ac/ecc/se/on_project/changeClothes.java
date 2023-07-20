package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class changeClothes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_clothes);

        //変数の初期化
        ListView ItemList = findViewById(R.id.itemlist);
        ImageView back = findViewById(R.id.back);
        ImageView go_furniture = findViewById(R.id.goFurniture);
        ImageView TakeOffHat = findViewById(R.id.TakeOffHat);

        //ログインユーザーのユーザーID,パスワード(一応)をプリファレンスから取得
        SharedPreferences sp = getSharedPreferences("user_id",MODE_PRIVATE);
        String user_id = sp.getString("user_id",null); //キー、デフォルト値

        TestOpenHelper helper = new TestOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //DBから user_item表の 列名user_id をキーとして item表のレコードを取得
        String sql = "SELECT * FROM ITEM WHERE TYPE=2 AND ITEM_ID IN (SELECT DISTINCT ITEM_ID FROM USERITEM WHERE USER_ID =" + "'haru123'" + ")";
        Cursor cursor = db.rawQuery(sql,null);

        //DBから得られるデータを格納するためのリスト
        //アイテム名
        List<String> ItemName = new ArrayList<String>();
        //カテゴリー
        List<Integer> ItemCategory = new ArrayList<Integer>();
        //画像
        List<String> ItemImage = new ArrayList<String>();

        //カラムが何列あるか取得(レコード数)を取得するための変数
        int recoadCnt = 0;

        if (cursor.moveToFirst()) { //1レコードずつ取得
            do {
                recoadCnt++;
                //dbから受けっとったデータの分だけ追加
                //列名:iname
                ItemName.add((cursor.getString(1)));
                //列名:category
                ItemCategory.add((cursor.getInt(4)));
                //列名:image
                ItemImage.add((cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close(); //db接続終了
        System.out.println(recoadCnt);



        ArrayAdapter ListAdapter= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ItemName);
        ItemList.setAdapter(ListAdapter);

        ItemList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position="+position);
                //画像のリソースIDを取得
                int resID = getResources().getIdentifier(ItemImage.get(position), "drawable", getPackageName());
                System.out.println("resID="+resID);
                System.out.println("itemCategory="+ItemCategory.get(position));
                switch (ItemCategory.get(position)) {
                    case 1:
                        break;
                }
            }
        });

        //Myroomに戻る
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //模様替えのページに遷移
        go_furniture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), changeClothes.class);
                startActivity(intent); //finish();でActivityの表示終了
            }
        });

        //帽子を外す
        TakeOffHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hat_positionの画像をnull
            }
        });


    }
}