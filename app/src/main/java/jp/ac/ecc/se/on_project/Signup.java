package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 画面パーツ情報登録
        // 文字
        TextView login = findViewById(R.id.Text);
        // USERID
        TextView UserID = findViewById(R.id.UserId);
        // パスワード
        TextView Password = findViewById(R.id.Password);
        // アカウント作成ボタン
        Button CreationButton = findViewById(R.id.CreationButton);

        // 登録ユーザーID　８文字以上　かぶってたらトーストでだす
        SQLiteDatabase db;

        //  Cursor c = db.rawQuery("");

        // パスワード　８文字以上１４文字の半角英数字　


        // アカウント作成したらログイン画面に戻る　遷移
        CreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}