package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class Signup extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 画面パーツ情報登録
        EditText user_id = findViewById(R.id.UserId);
        EditText user_name = findViewById(R.id.userName);
        EditText password = findViewById(R.id.Password);
        EditText Repassword = findViewById(R.id.password_comfirm);
        Button create_button = findViewById(R.id.CreationButton);
        TextView err_userid = findViewById(R.id.err_userid);
        TextView err_pass = findViewById(R.id.err_pass);
        TextView err_repass = findViewById(R.id.err_repass);
        TextView err_username = findViewById(R.id.err_username);

        //エラーメッセージ用連想配列の初期化
        HashMap<String, String> ErrorMessage = new HashMap<String, String>();
        //エラーメッセージ追加
        ErrorMessage.put("FullWidthAlphabet","半角英数字で入力してください");
        ErrorMessage.put("TooLongChara","文字以内で入力してください");
        ErrorMessage.put("TooShortChara","文字数が短すぎます");
        ErrorMessage.put("UnfavorableChara","好ましくない言葉が含まれています");
        ErrorMessage.put("PasswordMiss","パスワードが間違っています");

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ユーザが入力したデータを取得
                final String confirm_userid = user_id.getText().toString();
                final String comfirm_user_name = user_name.getText().toString();
                final String comfirm_password = password.getText().toString();
                final String comfirm_Repassword = Repassword.getText().toString();

                //ユーザが入力したデータが正しく入力されているか確認
                //ユーザidの確認(20文字以内 半角英数字)
//                if(confirm_userid){
//
//                }

                //パスワードの確認(８文字以上１４文字の半角英数字)

                //ユーザーネームの確認(10文字以内　常識的にダメなやつ×)


                //全て正しい場合、プリファレンス/DBに登録 不正なデータが含まれている場合エラーメッセージを対応するhintプロパティに表示

            }
        });
    }
}