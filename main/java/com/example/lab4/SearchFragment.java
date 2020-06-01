package com.example.lab4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment
{
    private Button btn_search;
    private EditText et_name;
    private TextView tv_result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        btn_search = view.findViewById(R.id.btn_search);
        et_name = view.findViewById(R.id.et_search_name);
        tv_result = view.findViewById(R.id.tv_search_result);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchStudent();
            }
        });

        return view;
    }

    private void searchStudent() {
        String name = et_name.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "Name could not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        // Completed 7: Search the student in the database
        StudentDbHelper dbHelper = new StudentDbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = StudentInfoContract.Students.STUDENT_NAME + " LIKE ?";
        String[] selectionArgs = {"%" + name + "%"};
        String[] columns = {StudentInfoContract.Students.STUDENT_ID, StudentInfoContract.Students.STUDENT_NAME};
        Cursor cursor = db.query(StudentInfoContract.Students.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                StudentInfoContract.Students.STUDENT_ID
        );
        String result = "";
        while (cursor.moveToNext()){

            String result_id =  cursor.getString(cursor.getColumnIndex(StudentInfoContract.Students.STUDENT_ID));
            String result_name =  cursor.getString(cursor.getColumnIndex(StudentInfoContract.Students.STUDENT_NAME));

            System.out.println(result_id + result_name);
            result = result + "\n\n\n" + "Student ID: " + result_id + "\nName: " + result_name;

        }
        if (result.isEmpty())
        {
            result = "No student found!";
        }
        tv_result.setText(result);

        dbHelper.close();

    }
}
