package demo.utt37.congcau.apptracnghiem.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import demo.utt37.congcau.apptracnghiem.model.User;
import demo.utt37.congcau.apptracnghiem.model.Question;
import demo.utt37.congcau.apptracnghiem.model.QuestionModel;

/**
 * Created by cong on 1/29/2018.
 */

public class QuestionController {
    private DBHelper dbHelper;

    public QuestionController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<QuestionModel> getQuestion(int num_exam, String subject) {

        ArrayList<QuestionModel> questionModelArrayList = new ArrayList<QuestionModel>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM test WHERE num_exam= '"+num_exam+"' AND subject='"+subject+"'",null);
        cursor.moveToFirst();
        do {
            QuestionModel item;
            item = new QuestionModel(
                    cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),""
            );
            questionModelArrayList.add(item);
        } while (cursor.moveToNext());
        return questionModelArrayList;
    }
    public Cursor getSearchQuestion(String key) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM test WHERE question LIKE '%"+key+"%'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor getQuestionBySubject(String key) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM test WHERE subject LIKE '%"+key+"%'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public ArrayList<String> getExam() {
        ArrayList<String> examArrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT DISTINCT num_exam FROM test;",null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                examArrayList.add(cursor.getString( cursor.getColumnIndex("num_exam") ));
            } while (cursor.moveToNext());
        }
        return examArrayList;
    }

    public ArrayList<User> getUser() {
        ArrayList<User> examArrayList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM user;",null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                examArrayList.add(new User(cursor.getString( cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("password"))));
            } while (cursor.moveToNext());
        }
        return examArrayList;
    }

    public boolean insertQuestion(Question question) {
        boolean result = false;

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("question", question.getQuestion());
            values.put("ans_a", question.getAns_a());
            values.put("ans_b", question.getAns_b());
            values.put("ans_c", question.getAns_c());
            values.put("result", question.getResult());
            values.put("num_exam", question.getNum_exam()+"");
            values.put("subject", question.getSubject());
            values.put("image", question.getImage());
            long rs = db.insert("test", null, values);
            if (rs > 0) {
                result = true;
            }

        return result;
    }

}

