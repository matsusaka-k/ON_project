package jp.ac.ecc.se.on_project;

import static java.lang.Math.floor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchingAfter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_after);


        // 画面パーツ情報登録
        //あいさつ文
        TextView GreetingText = findViewById(R.id.GreetingText);
        //ポイントを格納する
        TextView ObtainPointText = findViewById(R.id.ObtainPointText);
        // データが入るテキスト
        TextView PointData = findViewById(R.id.PointData);
        // ポイント画像
        ImageView PointView = findViewById(R.id.PointView);

        // マッチングした相手側の画面パーツ
        // 相手のアイコン
        ImageView MatchingUserView = findViewById(R.id.MatchingUserView);
        // 相手の名前
        TextView MatchingUserName = findViewById(R.id.MatchingUserName);
        // 相手のID
        TextView MatchingUserID = findViewById(R.id.MatchingUserID);
        // 相手の一言
        TextView MatchingUsercomment = findViewById(R.id.MatchingUsercomment);

        //DBに接続してマッチングした相手の情報を取ってくる(sample ID:aki123)
        TestOpenHelper helper = new TestOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String usersql = "SELECT * FROM USERINFO WHERE USER_ID =" + "'haru123'";
        Cursor cursor = db.rawQuery(usersql,null);

        if(cursor.moveToFirst()){
            do{
                //DBから取得したデータを各変数に格納
                MatchingUserID.setText(cursor.getString(0));
                MatchingUserName.setText(cursor.getString(2));
                //MatchingUsercomment.setText(cursor.getString(5));
                //MatchingUserView
            }while(cursor.moveToNext());
        }
        cursor.close();



        //睡眠時間をポイントに変換
        // 前の画面からデータを受け取る
        // long型の値を受けとる
        long sleepTime = getIntent().getLongExtra("data", 0);
        //long data = (long) getIntent().getSerializableExtra("data");

        //sleepTimeに格納されている秒数の値を時間(hour)に変換
        double sleepHourTime = sleepTime/3600;
        // 3600秒×寝た時間×0.01 = ポイント
        double point = sleepTime * 0.01;
        //小数第一位で切り捨て
        floor(point);
        System.out.println(point);

        // pointDataに計算したpointを格納して表示させる
        PointData.setText(Integer.toString((int) point));

        //取得したポイントをDBに格納
       //String pointsql = "UPDATE USERINFO SET POINTS =" + point + "WHERE USER_ID = " + "'haru123'";
        ContentValues values = new ContentValues();
        values.put("POINTS",point);
        db.update("USERINFO",values,"USER_NAME = ?",new String[]{"haru123"});


        Handler delayHandler = new Handler();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),Myroom.class);
                startActivity(intent);
            }
            // 画面が切り替わる時間設定
        }, 1000000);
    }
}