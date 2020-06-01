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

public class AddFragment extends Fragment {

    private Button btn_save;
    private EditText et_name, et_email, et_std_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        btn_save = view.findViewById(R.id.btn_save);
        et_email = view.findViewById(R.id.et_email);
        et_name = view.findViewById(R.id.et_name);
        et_std_id = view.findViewById(R.id.et_std_id);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudentInfoIntoDatabase();
            }
        });
        return view;
    }

    private void saveStudentInfoIntoDatabase() {
        String id = et_std_id.getText().toString();
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();

        if (id.isEmpty() || name.isEmpty()) {
            Toast.makeText(getActivity(), "Name and ID could not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        // Completed 3: Add the record to the database
        StudentDbHelper dbHelper = new StudentDbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(StudentInfoContract.Students.STUDENT_NAME, name);
        cv.put(StudentInfoContract.Students.STUDENT_ID, id);
        cv.put(StudentInfoContract.Students.STUDENT_EMAIL, email);
        db.insert(StudentInfoContract.Students.TABLE_NAME, null, cv);
        db.close();
        Toast.makeText(getActivity(), "Student " + name + " add to the database successfully", Toast.LENGTH_SHORT).show();
        et_email.setText("");
        et_name.setText("");
        et_std_id.setText("");
    }

}
