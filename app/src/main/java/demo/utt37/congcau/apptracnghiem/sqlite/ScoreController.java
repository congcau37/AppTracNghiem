package demo.utt37.congcau.apptracnghiem.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import demo.utt37.congcau.apptracnghiem.sqlite.DBHelper;

public class ScoreController {
    private DBHelper dbHelper;

    public ScoreController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertScore(String name, int score, String room){

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("name",name);
        contentValues.put("score",score);
        contentValues.put("room",room);
        sqLiteDatabase.insert("tbscore",null,contentValues);
        sqLiteDatabase.close();
    }
    public Cursor getScore() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("tbscore",null,null,null,null,null,"_id DESC", null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
           return cursor;
    }
}
