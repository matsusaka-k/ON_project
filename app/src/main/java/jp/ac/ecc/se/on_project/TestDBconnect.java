package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestDBconnect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dbconnect);

        //サンプル用

        //変数の初期化
        TextView sampletext = findViewById(R.id.sampletext);
        Button searchbutton = findViewById(R.id.searchbutton);
        Button addbutton = findViewById(R.id.addbutton);
        Button updatebutton = findViewById(R.id.updatebutton);
        Button deletebutton = findViewById(R.id.deletebutton);

        TestOpenHelper helper = new TestOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.query("userinfo", new String[] { "USER_ID", "PASSWORD" }, null, null, null, null, null);
                boolean isEof = c.moveToFirst();
                while (isEof) {
                    sampletext.setText(String.format("ユーザーID：%s、パスワード：%s", c.getString(0), c.getString(1)));
                    isEof = c.moveToNext();
                }
                c.close();
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("USER_ID","siki123");
                values.put("PASSWORD","1234567891011");
                values.put("USER_NAME","四季");
                values.put("IMAGE","");
                values.put("POINTS",5000);
                long id = db.insert("userinfo", null, values);
                sampletext.setText(String.format("登録完了。行番号：%d",  id));
            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("PASSWORD", "aaaaaa");
                db.update("userinfo", values, "USER_ID = 'siki123'", null);
                sampletext.setText("更新完了。検索してパスワードが「aaaaaa」になっていればOK");
            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("userinfo", "USER_ID = 'siki123'", null);
                sampletext.setText("削除完了。検索して「huyu123」が表示されればOK");
            }
        });
    }
}