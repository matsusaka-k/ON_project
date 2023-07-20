package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {

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
        ErrorMessage.put("PasswordMiss","パスワードが間違っています");

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ユーザが入力したデータを取得
                String confirm_userid = user_id.getText().toString();
                String comfirm_user_name = user_name.getText().toString();
                String comfirm_password = password.getText().toString();
                String comfirm_Repassword = Repassword.getText().toString();

                /**
                 *  ユーザが入力したデータが正しく入力されているか確認
                 */

                //0の場合...エラーなし 1の場合...エラーあり
                int errFlg = 0;

                //ユーザidの確認(20文字以内 半角英数字)
                if (!confirm_userid.matches("^[A-Za-z0-9]+$")) {
                    err_userid.setText("半角英数字で入力してください");
                    errFlg++;
                }

                if (!confirm_userid.matches("\\{6,20\\}")) {
                    if (err_userid.getText().toString() != null) {
                        err_userid.setText("半角英数字,6文字以上20文字以内で入力してください");
                    } else {
                        errFlg++;
                        err_userid.setText("6文字以上20文字以内で入力してください");
                    }
                }

                //パスワードの確認(８文字以上１４文字の半角英数字)
                //半角英数字ではない時
                if (!comfirm_password.matches("^[A-Za-z0-9]+$")) {
                    err_pass.setText("半角英数字で入力してください");
                    errFlg++;
                }
                //8文字以上14文字以内ではないとき
                if (!comfirm_password.matches("\\{8,14\\}")) {
                    if (err_pass.getText().toString() != null) {
                        err_userid.setText("半角英数字,8文字以上14文字以内で入力してください");
                    } else {
                        errFlg++;
                        err_userid.setText("8文字以上14文字以内で入力してください");
                    }
                }

                //二つ目のパスワードの確認
                //comfirm_password(一回目)とcomfirm_Repassword(2回目)が一致しない場合
                if (!comfirm_password.equals(comfirm_Repassword)) {
                    err_repass.setText("パスワードが間違っています");
                    errFlg++;

                }
                //ユーザネームの確認
                if (!comfirm_user_name.matches("\\{3,10\\}")) {
                    errFlg++;
                    err_username.setText("3文字以上10文字以内で入力してください");
                }

                //全て正しい場合、user_idとパスワードをプリファレンス/DBに登録
                if (errFlg == 0) {
                    //プリファレンスに登録(user_id)
                    SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("user_id", user_id.getText().toString()); //タイトルをエディターに保存
                    editor.commit();

                    //DBに登録
                    TestOpenHelper helper = new TestOpenHelper(getApplicationContext());
                    SQLiteDatabase db = helper.getReadableDatabase();
                    String sql = "INSERT INTO USERINFO VALUES (" + confirm_userid + "," + ")";

                }
            }
        });
    }
}