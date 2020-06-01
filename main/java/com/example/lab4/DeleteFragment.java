package com.example.lab4;

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

public class DeleteFragment extends Fragment {

    private EditText et_id;
    private Button btn_delete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        btn_delete = view.findViewById(R.id.btn_delete);
        et_id = view.findViewById(R.id.et_delete_id);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });
        return view;
    }

    private void deleteStudent() {
        String id = et_id.getText().toString();
        if (id.isEmpty()) {
            Toast.makeText(getActivity(), "ID could not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        // Completed 6: Delete the student with the input id from the database
        StudentDbHelper dbHelper = new StudentDbHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = StudentInfoContract.Students.STUDENT_ID + " = " + id;
        int count = db.delete(StudentInfoContract.Students.TABLE_NAME, selection, null);
        if (count > 0) {
            Toast.makeText(getActivity(), "Student with ID:  " + id + " is removed from table successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "No Student's id is " + id, Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


}
