package com.example.studentmanagement1.ui.activity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanagement1.R;
import com.example.studentmanagement1.model.StudentModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentAddActivity extends AppCompatActivity {
    TextInputEditText etName, etGrade, etRoomNo, etFatherName;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    MaterialButton btAdd;

    int gender = 0;
    String id;
    StudentModel studentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);
        studentModel = getIntent().getParcelableExtra("studentModel");
        initViews();
        setToolbar();
        handleClicks();
    }

    private void setToolbar() {
        ActionBar toolbar = getSupportActionBar();
        if (studentModel != null) {
            toolbar.setTitle("Edit Student");
            setUI();

        } else {
            toolbar.setTitle("Add Student");
        }
        toolbar.setDisplayHomeAsUpEnabled(true);

    }

    private void setUI() {
        id = studentModel.getStudentId();
        etName.setText(studentModel.getStudentName());
        etGrade.setText(studentModel.getGrade());
        etRoomNo.setText(studentModel.getRoomNo());
        etFatherName.setText(studentModel.getFatherName());
        if (studentModel.getGender() == 0) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }
        btAdd.setText("Update Student");
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etGrade = findViewById(R.id.etGrade);
        etRoomNo = findViewById(R.id.etRoomNo);
        etFatherName = findViewById(R.id.etFName);

        rgGender = findViewById(R.id.rgGender);

        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);

        btAdd = findViewById(R.id.btAdd);
    }

    private void handleClicks() {
        rgGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == rbMale.getId()) {
                gender = 0;
            } else {
                gender = 1;
            }
        });
        btAdd.setOnClickListener(view -> {
            addStudent();
        });
    }

    private void addStudent() {
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("student");
            if (id == null) {
                id = myRef.push().getKey();
            }
            String name = etName.getText().toString();
            String grade = etGrade.getText().toString();
            String roomNo = etRoomNo.getText().toString();
            String fName = etFatherName.getText().toString();

            StudentModel studentModel = new StudentModel(id, name, grade, gender, roomNo, fName);
            myRef.child(id).setValue(studentModel).addOnCompleteListener(task ->
                    Toast.makeText(StudentAddActivity.this, "Student Added!!!", Toast.LENGTH_LONG).show());
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Some error occurred when saving student", Toast.LENGTH_SHORT).show();
        }

    }
}