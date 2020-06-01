package com.example.lab4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewAllFragment extends Fragment {

    private TextView tv_all_student;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container,false);

        tv_all_student = view.findViewById(R.id.tv_all_student_info);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getAllStudentInfo();
    }

    private void getAllStudentInfo() {
        // Completed 4: Retrieve the student info saved from the database
        StudentDbHelper dbHelper = new StudentDbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(StudentInfoContract.Students.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                StudentInfoContract.Students.STUDENT_ID
                );
        String allStudentInfo = "";
        while (cursor.moveToNext()){
            String id =  cursor.getString(cursor.getColumnIndex(StudentInfoContract.Students.STUDENT_ID));
            String name =  cursor.getString(cursor.getColumnIndex(StudentInfoContract.Students.STUDENT_NAME));
            String email =  cursor.getString(cursor.getColumnIndex(StudentInfoContract.Students.STUDENT_EMAIL));

            System.out.println(id + name+ email);
            allStudentInfo = allStudentInfo + "\n\n\n" + "Student ID: " + id + "\nName: " + name + "\nEmail: " + email;

        }
        if (allStudentInfo.isEmpty()) {
            allStudentInfo = "No student info saved in the database so far!";
        }
        tv_all_student.setText(allStudentInfo);

        dbHelper.close();
    }

}
