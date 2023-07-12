package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                    stopTime = System.currentTimeMillis() + 10000;
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