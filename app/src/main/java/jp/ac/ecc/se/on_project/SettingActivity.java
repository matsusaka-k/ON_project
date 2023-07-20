package jp.ac.ecc.se.on_project;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);



                Button btn3= findViewById(R.id.btn3);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 画面遷移先のアクティビティを指定してインテントを作成
                        Intent intent = new Intent(SettingActivity.this, Start.class);

                        // 画面遷移実行
                        startActivity(intent);
                    }
                });
            }






//        // import android.content.SharedPreferences;
//        Context context;
//        SharedPreferences pref = context.getSharedPreferences("my_settings", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("stringValue", "ほげ");
//        editor.putBoolean("booleanValue", true);
//        editor.putInt("intValue", 100);
//        editor.clean();

    }
