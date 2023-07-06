package jp.ac.ecc.se.on_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//絶対に触らないこと!!!

public class TestOpenHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    private static final int DATABASE_VERSION = 10;
    // データーベース名
    private static final String DATABASE_NAME = "ON_DB.db";

    //各テーブル情報を変数に格納
    private static final String UserInfo_Table =
            "CREATE TABLE USERINFO ( USER_ID VARCHAR(20)," +
                    "PASSWORD VARCHAR(14)," +
                    "USER_NAME VARCHAR(10)," +
                    "IMAGE VARCHAR(255)," +
                    "POINTS INT(20)," +
                    "PRIMARY KEY(USER_ID) )";

    private static final String Friends_Table =
            "CREATE TABLE FRIENDS ( USER_ID VARCHAR(20)," +
                    "FRIEND_ID	VARCHAR(10)," +
                    "PRIMARY KEY(USER_ID,FRIEND_ID)," +
                    "FOREIGN KEY (USER_ID) REFERENCES USERINFO(USER_ID)," +
                    "FOREIGN KEY (FRIEND_ID) REFERENCES USERINFO(USER_ID) )";

    private static final String Item_Table =
            "CREATE TABLE ITEM ( ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "INAME VARCHAR(20)," +
                    "PRICE INT(10) NOT NULL," +
                    "TYPE INT(1) NOT NULL," +
                    "CATEGORY INT(2) NOT NULL," +
                    "IMAGE INT(255) NOT NULL," +
                    "UNIQUE(INAME) )";

    private static final String UserItem_Table =
            "CREATE TABLE USERITEM ( USER_ID VARCHAR(20)," +
                    "ITEM_ID INT(10)," +
                    "FOREIGN KEY (USER_ID) REFERENCES USERINFO(USER_ID)," +
                    "FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ITEM_ID) )";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS On_DB"; //テーブルが存在している場合テーブルを削除する

    // コンストラクタ
    TestOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // context: コンテキスト
        // DATABASE_NAME: データベースファイル名
        // factory: カーソルのファクトリー
        // DATABASE_VERSION: データベースのバージョン

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        CreateUserInfoTable(db);
        CreateFriendsTable(db);
        CreateItemTable(db);
        CreateUserItemTable(db);
    }

    /***
     * ユーザー情報テーブル作成
     * @param db
     */
    private void CreateUserInfoTable(SQLiteDatabase db){
        db.execSQL(
                UserInfo_Table
        );

        // データ登録
        //userinfo
        db.execSQL("INSERT INTO userinfo VALUES ('haru123', '1234567891011','はる','',1000);");
        db.execSQL("INSERT INTO userinfo VALUES ('natsu123', '1234567891011','なつ','',2000);");
        db.execSQL("INSERT INTO userinfo VALUES ('aki123', '1234567891011','あき','',3000);");
        db.execSQL("INSERT INTO userinfo VALUES ('huyu123', '1234567891011','ふゆ','',4000);");
    }

    /***
     * 友達テーブル作成
     * @param db
     */
    private void CreateFriendsTable(SQLiteDatabase db){
        db.execSQL(
                Friends_Table
        );

        // データ登録
        //friends
        db.execSQL("INSERT INTO friends VALUES ('haru123', 'natsu123');");
        db.execSQL("INSERT INTO friends VALUES ('natsu123', 'haru123');");
        db.execSQL("INSERT INTO friends VALUES ('haru123', 'aki123');");
        db.execSQL("INSERT INTO friends VALUES ('aki123', 'haru123');");
        db.execSQL("INSERT INTO friends VALUES ('haru123', 'huyu123');");
        db.execSQL("INSERT INTO friends VALUES ('huyu123', 'haru123');");
    }

    /***
     * アイテムテーブル作成
     * @param db
     */
    private void CreateItemTable(SQLiteDatabase db){
        db.execSQL(
                Item_Table
        );

        // データ登録
        //Item
        db.execSQL("INSERT INTO item (INAME,PRICE,TYPE,CATEGORY,IMAGE) VALUES ('カラーボックス',100,1,4,'');");
        db.execSQL("INSERT INTO item (INAME,PRICE,TYPE,CATEGORY,IMAGE) VALUES ('うすがたテレビ',1000,1,8,'');");
        db.execSQL("INSERT INTO item (INAME,PRICE,TYPE,CATEGORY,IMAGE) VALUES ('はながらのシャツ',200,1,1,'');");
        db.execSQL("INSERT INTO item (INAME,PRICE,TYPE,CATEGORY,IMAGE) VALUES ('きいろのパンツ',100,1,2,'');");
        db.execSQL("INSERT INTO item (INAME,PRICE,TYPE,CATEGORY,IMAGE) VALUES ('オシャレなハット',100,1,3,'');");
        db.execSQL("INSERT INTO item (INAME,PRICE,TYPE,CATEGORY,IMAGE) VALUES ('ベージュのカーペット',300,1,5,'');");
        db.execSQL("INSERT INTO item (INAME,PRICE,TYPE,CATEGORY,IMAGE) VALUES ('あおいろのソファ',400,1,2,'');");
    }

    /***
     * ユーザー情報テーブル作成
     * @param db
     */
    private void CreateUserItemTable(SQLiteDatabase db){
        db.execSQL(
                UserItem_Table
        );

        // データ登録
        //User_Item
        db.execSQL("INSERT INTO useritem VALUES ('haru123', '1');");
        db.execSQL("INSERT INTO useritem VALUES ('haru123', '2');");
        db.execSQL("INSERT INTO useritem VALUES ('haru123', '3');");
        db.execSQL("INSERT INTO useritem VALUES ('haru123', '4');");
        db.execSQL("INSERT INTO useritem VALUES ('haru123', '5');");
        db.execSQL("INSERT INTO useritem VALUES ('natsu123', '1');");
        db.execSQL("INSERT INTO useritem VALUES ('natsu123', '3');");
        db.execSQL("INSERT INTO useritem VALUES ('natsu123', '2');");
        db.execSQL("INSERT INTO useritem VALUES ('aki123', '5');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // アップデートの判別、古いバージョンは削除して新規作成
        if (db != null) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }

    public void onDowngrade(SQLiteDatabase db,
                            int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }
}
