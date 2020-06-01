package com.example.lab4;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UpdateFragment extends Fragment {

    private Button btn_update;
    private EditText et_name, et_email, et_std_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        btn_update = view.findViewById(R.id.btn_update);
        et_email = view.findViewById(R.id.et_email);
        et_name = view.findViewById(R.id.et_name);
        et_std_id = view.findViewById(R.id.et_std_id);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudentInfo();
            }
        });
        return view;
    }

    private void updateStudentInfo() {
        String id = et_std_id.getText().toString();
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();

        if (id.isEmpty() || name.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a valid record", Toast.LENGTH_SHORT).show();
            return;
        }
        // Completed 5: Update the student info in the database
        StudentDbHelper dbHelper = new StudentDbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(StudentInfoContract.Students.STUDENT_ID, id);
        cv.put(StudentInfoContract.Students.STUDENT_EMAIL, email);
        String selection = StudentInfoContract.Students.STUDENT_NAME  + " =? ";
        String[] selectionArgs = {name};

        // return number of row that is been updated
        int count = db.update(StudentInfoContract.Students.TABLE_NAME,
                cv,
                selection,
                selectionArgs
                );
        db.close();
        // if there is row updated
        if (count > 0) {
            Toast.makeText(getActivity(), "Student " + name + " info is updated successfully", Toast.LENGTH_SHORT).show();
            et_email.setText("");
            et_name.setText("");
            et_std_id.setText("");
        } else {
            Toast.makeText(getActivity(), "No Student named " + name + " in the database.", Toast.LENGTH_SHORT).show();
        }
    }
}
