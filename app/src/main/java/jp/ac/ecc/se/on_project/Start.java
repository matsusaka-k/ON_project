package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Start extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start);

            //変数の初期化
            ImageView Background = findViewById(R.id.background);
            ImageView AppTitle = findViewById(R.id.zzzfrienz);
            TextView ConceptText = findViewById(R.id.conceptText); //コンセプトの一文「睡眠時間で繋がる遊ぶ」
            ImageView ScreenTap = findViewById(R.id.start_tap); //「画面をタップ」

            //プリファレンスの変数にuser_idの値が入っているか確認(サインアップ前の初期値null)
            SharedPreferences sp = getSharedPreferences("LogIn",MODE_PRIVATE);
            String user_id_frag = sp.getString("user_id",null); //キー、デフォルト値

            //既にサインアップ済みの場合は2秒ディレイをかけてMyroomに画面遷移
            if(null != user_id_frag){
                ScreenTap.setVisibility(View.INVISIBLE); //非表示

                //Handlerクラスをインスタンス化　postDelaydメソッド呼び出し
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplication(), Myroom.class);
                        startActivity(intent); //finish();でActivityの表示終了
                    }
                }, 2000); // 遅らせたい時間(ミリ秒)
            }else {
                Background.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplication(), Signup.class);
                        startActivity(intent); //finish();でActivityの表示終了
                    }
                });
            }
        }
}