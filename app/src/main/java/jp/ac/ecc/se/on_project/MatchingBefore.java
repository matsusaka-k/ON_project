package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchingBefore extends AppCompatActivity {


    public TextView timer;
    public TextView message;
    public TextView userid;
    public TextView username;
    public Button sleepButton;
    public Button wakeButton;
    public Handler handler;
    public Runnable runnable;
    public long startTime;
    public long timeInMillis;
    public boolean isRunning;
    public Integer sleepTimeSec;
    public Integer wakeTimeSec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_before);

        // 睡眠開始からの経過時間
        timer = findViewById(R.id.timer);
        // マッチング相手の一言
        message = findViewById(R.id.message);
        // マッチング相手のid
        userid = findViewById(R.id.userId);
        // マッチング相手の名前
        username = findViewById(R.id.userName);
        // 睡眠開始ボタン
        sleepButton = findViewById(R.id.sleep);
        // 起きた時に押してもらうボタン
        wakeButton = findViewById(R.id.wake);





        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                // 　　　　　　　　ある時点の時間をミリ秒で取得
                //             画面開いた時間　　　　　　　　　　　　　経過時刻
//                timeInMillis = System.currentTimeMillis() - startTime;
//                updateTimer();
//                //
//                handler.postDelayed(this, 1000);
            }
        };




        sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // サンプル
                sleepTimeSec = 5400;
                // 寝た時間
//                sleepTimeSec = getSec();
//                //sleepTimer();
//                sleepTimeSec = 28800;
//                wakeTimeSec = 5400;
//                long l = (wakeTimeSec - sleepTimeSec);
//                long p = l / 3600;
//                System.out.println("差分：" + p);
            }
        });

        wakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // サンプル
                wakeTimeSec = 28800;
                // 起きた時間
//                wakeTimeSec = getSec();
                // 差分計算
                long data = wakeTimeSec - sleepTimeSec;
                updateTimer();
                System.out.println("差分：" + data + "秒");

                // トースト
//                String toastMessage = "トースト";
//                // lambda式
//                wakeButton.setOnClickListener( v -> {
//                    Toast toast = Toast.makeText(MatchingBefore.this, toastMessage , Toast.LENGTH_LONG);
//                    toast.show();
//                });

//
////                wakeTimer();
                // データごと遷移
                Intent intent = new Intent(getApplication(), MatchingAfter.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });
    }

    public static String getDate(){
        //取得する日時のフォーマットを指定
        final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //時刻をミリ秒で取得
        final Date date = new Date(System.currentTimeMillis());
        //日時を指定したフォーマットで取得
        return df.format(date);
    }

    public static Integer getSec(){
        String date = getDate();
        // 表示したい文字の〇番目
        String time = date.substring(11);
        System.out.println(time);
        String[] t = time.split(":");
        // 時
        Integer hourSec = Integer.parseInt(t[0]) * 3600;
        // 分
        Integer minSec = Integer.parseInt(t[1]) * 60;
        // 秒
        Integer sec = Integer.parseInt(t[2]);
        // 時+分+秒
        return hourSec + minSec + sec;
    }

    public void updateTimer() {
        // 時・分・秒ごとに計算
        int seconds = (int) (timeInMillis / 1000) % 60;
        int minutes = (int) ((timeInMillis / (1000 * 60)) % 60);
        int hours = (int) ((timeInMillis / (1000 * 60 * 60)) % 24);

        // タイマー
        String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timer.setText(timeFormatted);
    }


//    ・DB接続(マッチング相手の情報)
//    ・↑のやつも画面遷移時



}
