package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Button kagubtn = findViewById(R.id.kagubtn);
        Button fukubtn = findViewById(R.id.fukubtn);
        Button kaubtn = findViewById(R.id.kaubtn);
        TestOpenHelper helper = new TestOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();


        // RecyclerViewに設定するリストを作成
        List<RowData> planetArrayList = new ArrayList<>();

        planetArrayList.add(new RowData("フローリングタイル","100PT"));
        planetArrayList.add(new RowData("丸カーペット","50PT"));
        planetArrayList.add(new RowData("テレビ","50PT"));
        planetArrayList.add(new RowData("カラーボックス1","20PT"));
        planetArrayList.add(new RowData("カラーボックス2","20PT"));
        planetArrayList.add(new RowData("ファンシー枕","40PT"));

        normal_rec.setLayoutManager(new LinearLayoutManager(this));
        // 作成したlistをアダプターに渡し、RecyclerViewにアダプターを設定する
        ProductRecycleAdapter adapter = new ProductRecycleAdapter(planetArrayList);
        normal_rec.setAdapter(adapter);

        TextView pointTextView = findViewById(R.id.point);
        int currentPoint = Integer.parseInt(pointTextView.getText().toString());

        // Decrease the point value and update the TextView
        currentPoint--;
        pointTextView.setText(String.valueOf(currentPoint));

        kagubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("Item_Table",Context.MODE_PRIVATE,null);
            }
        });
        
        fukubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("Item_Table",Context.MODE_PRIVATE,null);
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