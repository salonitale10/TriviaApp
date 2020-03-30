package com.trivia.saloni.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TRIVIA_APP";

    private static final String USER_TABLE_NAME = "USER_TABLE";
    private static final String USER_TABLE_COL1 = "ID";
    private static final String USER_TABLE_COL2 = "USER_NAME";


    private static final String QUESTION_TABLE_NAME = "QUESTION_TABLE";
    private static final String QUESTION_TABLE_COL1 = "ID";
    private static final String QUESTION_TABLE_COL2 = "QUESTION";
    private static final String QUESTION_TABLE_COL3 = "ANSWER";
    private static final String QUESTION_TABLE_COL4 = "USER_ID";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE "+USER_TABLE_NAME+"("+USER_TABLE_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USER_TABLE_COL2+" TEXT NOT NULL)";
        db.execSQL(createUserTable);

        String createQuestionTable = "CREATE TABLE "+QUESTION_TABLE_NAME+"("+QUESTION_TABLE_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+QUESTION_TABLE_COL2+" TEXT NOT NULL, "+QUESTION_TABLE_COL3+" TEXT NOT NULL, "+QUESTION_TABLE_COL4+" INTEGER NOT NULL, FOREIGN KEY("+QUESTION_TABLE_COL4+") REFERENCES "+USER_TABLE_NAME+"("+USER_TABLE_COL1+"))";
        db.execSQL(createQuestionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE " + QUESTION_TABLE_NAME);
        onCreate(db);
    }


    public void addQueryData(String userName, List<QueryQuestion> queryQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues columnValues = new ContentValues();
        columnValues.put(USER_TABLE_COL2, userName);



        long userId = db.insert(USER_TABLE_NAME, null, columnValues);

        if(userId != -1) {
            for(QueryQuestion queryQuestion: queryQuestions)
            {
                columnValues = new ContentValues();

                columnValues.put(QUESTION_TABLE_COL2, queryQuestion.getQuestion());
                columnValues.put(QUESTION_TABLE_COL3, queryQuestion.getSelectedAnswer());
                columnValues.put(QUESTION_TABLE_COL4, userId);
                db.insert(QUESTION_TABLE_NAME, null, columnValues);
            }
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getHistoryData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String getDataQuery = "SELECT USER."+USER_TABLE_COL2+", QUESTION."+QUESTION_TABLE_COL2+", QUESTION."+QUESTION_TABLE_COL3+" FROM "+QUESTION_TABLE_NAME+" QUESTION JOIN "+USER_TABLE_NAME+" USER ON QUESTION."+QUESTION_TABLE_COL4+" = USER."+USER_TABLE_COL1;
        Cursor data = db.rawQuery(getDataQuery, null);
        return data;
    }

    public Cursor getUserData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String getDataQuery = "SELECT * FROM "+USER_TABLE_NAME;
        Cursor data = db.rawQuery(getDataQuery, null);
        return data;
    }

    public Cursor getGameHistoryForUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String getDataQuery = "SELECT * FROM "+QUESTION_TABLE_NAME+" WHERE "+QUESTION_TABLE_COL4+" = ?";
        Cursor data = db.rawQuery(getDataQuery, new String[] {String.valueOf(userId)});
        return data;
    }

}