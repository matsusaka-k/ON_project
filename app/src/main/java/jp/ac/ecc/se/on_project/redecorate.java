package jp.ac.ecc.se.on_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class redecorate extends AppCompatActivity {

    //AllDelete中にリストを押してないか判断するフラッグ true=削除処理中,false=それ以外
    boolean AllDeleteFlg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redecorate);

        //変数の初期化
        ListView ItemList = findViewById(R.id.itemlist);
        ImageView TV_position = findViewById(R.id.TV_position);
        ImageView desk_position = findViewById(R.id.desk_position);
        ImageView accessory_position = findViewById(R.id.accessory_position);
        ImageView sofa_position = findViewById(R.id.sofa_position);
        ImageView plant_position = findViewById(R.id.plant_position);
        ImageView carpet_position = findViewById(R.id.carpet_position);

        //ショップへ遷移するボタン　
        ImageView changeClothes = findViewById(R.id.changeClothes);
        //着せ替えに遷移するボタン
        ImageView shopIcon = findViewById(R.id.shopIcon);

        //家具の位置情報を持つレイアウト
        ConstraintLayout positions = findViewById(R.id.positions);

        //全部消すボタン
        ImageView AllDelete = findViewById(R.id.AllDelete);
        //消しゴム
        ImageView eraser = findViewById(R.id.eraser);
        //一旦非表示にする
        eraser.setVisibility(View.INVISIBLE);

        //ログインユーザーのユーザーID,パスワード(一応)をプリファレンスから取得
        SharedPreferences sp = getSharedPreferences("user_id",MODE_PRIVATE);
        String user_id = sp.getString("user_id",null); //キー、デフォルト値

        TestOpenHelper helper = new TestOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        //DBから user_item表の 列名user_id をキーとして item表のレコードを取得
        String sql = "SELECT * FROM ITEM WHERE TYPE=1 AND ITEM_ID IN (SELECT DISTINCT ITEM_ID FROM USERITEM WHERE USER_ID =" + "'haru123'" + ")";
        Cursor cursor = db.rawQuery(sql,null);

        //DBから得られるデータを格納するためのリスト
        //アイテム名
        List<String> ItemName = new ArrayList<String>();
        //カテゴリー
        List<Integer> ItemCategory = new ArrayList<Integer>();
        //画像
        List<String> ItemImage = new ArrayList<String>();

        //カラムが何列あるか取得(レコード数)を取得するための変数
        int recoadCnt = 0;

        if (cursor.moveToFirst()) { //1レコードずつ取得
            do {
                recoadCnt++;
                //dbから受けっとったデータの分だけ追加
                //列名:iname
                ItemName.add((cursor.getString(1)));
                //列名:category
                ItemCategory.add((cursor.getInt(4)));
                //列名:image
                ItemImage.add((cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close(); //db接続終了
        System.out.println(recoadCnt);



        ArrayAdapter ListAdapter= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, ItemName);
        ItemList.setAdapter(ListAdapter);

        ItemList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position="+position);
                //画像のリソースIDを取得
                int resID = getResources().getIdentifier(ItemImage.get(position), "drawable", getPackageName());
                System.out.println("resID="+resID);
                System.out.println("itemCategory="+ItemCategory.get(position));
                if(AllDeleteFlg==false) { //家具画像の削除処理中ではなかったら...
                    switch (ItemCategory.get(position)) {
                        case 1:
                            desk_position.setImageResource(resID);
                            break;
                        case 2:
                            sofa_position.setImageResource(resID);
                            break;
                        case 3:
                            //chair_position.setImageResource(resID);
                            break;
                        case 4:
                            //shelh_position.setImageResource(resID);
                            break;
                        case 5:
                            carpet_position.setImageResource(resID);
                            break;
                        case 6:
                            accessory_position.setImageResource(resID);
                            break;
                        case 7:
                            plant_position.setImageResource(resID);
                            break;
                        case 8:
                            TV_position.setImageResource(resID);
                            break;
                    }
                }
            }
        });

        AllDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AnimationSetクラス...addAnimationメソッドで組み合わせたいアニメーションを追加する
                AnimationSet animSet = new AnimationSet(true);
                animSet.setInterpolator(new LinearInterpolator());

                //削除処理中なのでフラグをtrue(削除処理中)にする
                AllDeleteFlg=true;

                //消しゴムの画像を表示
                eraser.setVisibility(View.VISIBLE);

                /**
                 * 消しゴムを上下にかけるアニメーション開始
                 */
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        Animation.ABSOLUTE,0.0f,
                        Animation.ABSOLUTE,0.0f,
                        Animation.ABSOLUTE,40.0f,
                        Animation.ABSOLUTE,500.0f
                );
                //アニメーション時間
                translateAnimation.setDuration(400);
                //繰り返し回数
                translateAnimation.setRepeatCount(2);
                //アニメーションが終わってもその位置で表示
                translateAnimation.setFillAfter(false);
                eraser.startAnimation(translateAnimation);


                /**
                 * 消しゴムを上下にかけるアニメーション終了
                 */

                //全ての家具画像をnullにする
                desk_position.setImageDrawable(null);
                sofa_position.setImageDrawable(null);
                carpet_position.setImageDrawable(null);;
                carpet_position.setImageDrawable(null);
                accessory_position.setImageDrawable(null);
                plant_position.setImageDrawable(null);
                TV_position.setImageDrawable(null);
                //消しゴム非表示
                eraser.setVisibility(View.INVISIBLE);

                //削除処理が終わったのでフラグをfalse(削除処理外)にする
                AllDeleteFlg=false;
            }
        });

        //着せ替え画面に遷移
        changeClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), changeClothes.class);
                startActivity(intent); //finish();でActivityの表示終了
            }
        });

        shopIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), Myroom.class);
                startActivity(intent); //finish();でActivityの表示終了
            }
        });

    }
}