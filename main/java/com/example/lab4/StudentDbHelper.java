package com.example.lab4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.lab4.StudentInfoContract;

import androidx.annotation.Nullable;

public class StudentDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="students.db";
    private static final int DATABASE_VERSION = 1;

    /**
     *
     * @param context
     */
    public StudentDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * method creates the database when called
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_STUDENT_INFO_TABLE = "CREATE TABLE " +
                StudentInfoContract.Students.TABLE_NAME + "( " +
                StudentInfoContract.Students._ID  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                StudentInfoContract.Students.STUDENT_NAME + " TEXT NOT NULL, " +
                StudentInfoContract.Students.STUDENT_ID + " TEXT NOT NULL, " +
                StudentInfoContract.Students.STUDENT_EMAIL + " TEXT" +
                ");";
        db.execSQL(SQL_CREATE_STUDENT_INFO_TABLE);
        Log.v("Database operation", "Table Created!");
    }

    /**
     * update the database with most up-to-data schema
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + StudentInfoContract.Students.TABLE_NAME);
        onCreate(db);
    }
}
