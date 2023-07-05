package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchingAfter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_after);

        // 画面パーツ情報登録
        // 何も入力しないテキスト
        TextView GreetingText = findViewById(R.id.GreetingText);
        TextView ObtainPointText = findViewById(R.id.ObtainPointText);
        // データが入るテキスト
        TextView PointData = findViewById(R.id.PointData);

        // ポイント画像
        ImageView PointView = findViewById(R.id.PointView);

        // マッチングした相手側の画面上パーツ
        // マッチングした相手のアイコン
        ImageView MatchingUserView = findViewById(R.id.MatchingUserView);

        // マッチングした相手の名前
        TextView MatchingUserName = findViewById(R.id.MatchingUserName);
        // マッチングした相手のID
        TextView MatchingUserID = findViewById(R.id.MatchingUserID);

        // マッチングした相手の一言
        TextView MatchingUsercomment = findViewById(R.id.MatchingUsercomment);

        //toastで使用するサンプルのボタン
        // Button button = findViewById(R.id.button);



        // マッチング終了時にマイルーム画面に遷移
        // ※時間経過又はボタンタップで次の画面に遷移

//        String toastMessage = "トースト";
        Handler delayHandler = new Handler();
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 　　　　　　　　　　　　　　　　　　　　　　　　　　　   ↓画面遷移するところの名前を書く
                startActivity(new Intent( getApplicationContext(),Myroom.class ));
                finish();

                // lambda式
//                button.setOnClickListener( v -> {
//                    Toast toast = Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG);
//                    toast.show();
//                });

                //睡眠時間をポイントに変換
                // ↑のポイントを画面上に表示

                // 3600秒×寝た時間×0.01 = ポイント
                // 前画面から時間を取得する　型 → long型
                long sleepTime = 7200;
                double p;
                p = sleepTime * 0.01;
                PointData.setText(( int ) p );

            }
            // 切り替わる時間設定
        }, 5000);
    }
}