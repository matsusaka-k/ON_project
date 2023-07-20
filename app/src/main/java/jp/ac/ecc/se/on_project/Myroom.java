package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Myroom extends AppCompatActivity {

    //服・家具・mainの画面を判断するフラグ
    int menuNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myroom);

        //変数を初期化
        ImageView ChangeFurniture = findViewById(R.id.ChangeFurniture);
        ImageView shopIcon = findViewById(R.id.shopIcon);
        ImageView back = findViewById(R.id.back);

        //ログインユーザーのユーザーID,パスワード(一応)をプリファレンスから取得
        SharedPreferences sp = getSharedPreferences("user_id", MODE_PRIVATE);
        String user_id = sp.getString("user_id", null); //キー、デフォルト値

        //画面遷移
        ChangeFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), redecorate.class);
                startActivity(intent); //finish();でActivityの表示終了
            }
        });

        shopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Shop.class);
                startActivity(intent); //finish();でActivityの表示終了
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //画面のアニメーション


    }
}