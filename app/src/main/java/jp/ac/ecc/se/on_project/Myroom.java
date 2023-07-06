package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Myroom extends AppCompatActivity {
    int menuNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myroom);

        //ログインユーザーのユーザーID,パスワードをプリファレンスから取得

        //変数の初期化
        ImageView shop = findViewById(R.id.shop_icon);
        ImageView back = findViewById(R.id.back_icon); //プロトタイプ版なし
        ImageView myroom = findViewById(R.id.myroom_icon);
        ImageView recoad = findViewById(R.id.recoad_icon); //プロトタイプ版なし
        ImageView setting = findViewById(R.id.setting_icon); //プロトタイプ版なし
        RecyclerView itemlist = findViewById(R.id.itemlist);

        // RecyclerViewに設定するリストを作成


        //itemlistのAdapter
//
//
//        //DBからアイテム情報を取得する
//        TestOpenHelper helper = new TestOpenHelper(this);
//        SQLiteDatabase db = helper.getReadableDatabase();
//
//        //テストデータとしてharu123から取得
//        String testUser = "haru123";
//
//        Cursor c = db.rawQuery("SELECT ITEM_ID FROM USERITEM WHERE USER_ID = " + testUser,null);
//        boolean isEof = c.moveToFirst();
//        while (isEof) {
//
//        }
//        c.close();
//
//        //ログイン画面から遷移してきたときに


        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ショップの画面に遷移
            }
        });

        myroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menuNum == 0){
                    itemlist.setVisibility(View.INVISIBLE); //アイテムリスト非表示
                    Toast.makeText(getApplicationContext(), "今0番だよ：" + menuNum, Toast.LENGTH_LONG).show();
                }else if(menuNum == 1){
                    itemlist.setVisibility(View.VISIBLE); //アイテムリスト表示
                    Toast.makeText(getApplicationContext(), "今1番だよ：" + menuNum, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "今2番だよ：" + menuNum, Toast.LENGTH_LONG).show();
                }

                menuNum++;
                if(menuNum > 2){
                    menuNum = 0;
                }
            }
        });

    }

}