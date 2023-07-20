package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MatchingBefore2 extends AppCompatActivity {

    //計測開始した時間
    long startTime;
    //計測完了した時間
    long stopTime;
    //一回目 true 二回目 false
    boolean sleepFlg=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_before2);


        Button sleepButton = findViewById(R.id.Sleep);

        //DBに接続してマッチングした相手の情報を取ってくる(sample ID:aki123)
        TestOpenHelper helper = new TestOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String usersql = "SELECT * FROM USERINFO WHERE USER_ID =" + "'haru123'";
        Cursor cursor = db.rawQuery(usersql,null);

//        if(cursor.moveToFirst()){
//            do{
//                //DBから取得したデータを各変数に格納
//                MatchingUserID.setText(cursor.getString(0));
//                MatchingUserName.setText(cursor.getString(2));
//                //MatchingUsercomment.setText(cursor.getString(5));
//                //MatchingUserView
//            }while(cursor.moveToNext());
//        }
//        cursor.close();


        sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sleepFlg==true){
                    //一回目の場合
                    //ボタンを押すと時間の計測がスタート
                    startTime = System.currentTimeMillis();
                    sleepFlg=false;
                }else{
                    //二回目の場合
                    stopTime = System.currentTimeMillis();
                    long time = stopTime-startTime;
                    System.out.println("timeは"+time);

                    Intent intent = new Intent(getApplicationContext(),MatchingAfter.class);
                    intent.putExtra("data",time);
                    startActivity(intent);
                }
            }
        });
    }
}