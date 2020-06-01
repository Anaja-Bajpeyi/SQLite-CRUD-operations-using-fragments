package com.example.lab4;

import android.provider.BaseColumns;

public class StudentInfoContract {

    private StudentInfoContract() {
    }

    // define the student table content
    public static class Students implements BaseColumns {
        public static final String TABLE_NAME = "students_info";
        public static final String STUDENT_ID = "student_id";
        public static final String STUDENT_NAME = "name";
        public static final String STUDENT_EMAIL = "email";
    }
}
