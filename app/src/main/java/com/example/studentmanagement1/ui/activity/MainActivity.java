package com.example.studentmanagement1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagement1.R;
import com.example.studentmanagement1.model.StudentModel;
import com.example.studentmanagement1.ui.adapter.StudentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvStudents;
    FloatingActionButton fbAdd;

    FirebaseDatabase database;
    DatabaseReference myRef;

    List<StudentModel> studentModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("student");

        setContentView(R.layout.activity_main);
        rvStudents = findViewById(R.id.rvStudents);
        fbAdd = findViewById(R.id.fbAdd);


        handleClicks();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getStudents();

    }

    private void setAdapter() {
        StudentAdapter studentAdapter = new StudentAdapter(this, studentModelList);
        rvStudents.setAdapter(studentAdapter);
    }

    private void handleClicks() {
        fbAdd.setOnClickListener(view -> {
            Intent i = new Intent(this, StudentAddActivity.class);
            startActivity(i);
        });

    }

    private void getStudents() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentModelList.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    StudentModel studentModel = item.getValue(StudentModel.class);
                    studentModelList.add(studentModel);
                }
                setAdapter();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Some error occurred !!!, " + databaseError, Toast.LENGTH_SHORT).show();
            }
        });
    }
}