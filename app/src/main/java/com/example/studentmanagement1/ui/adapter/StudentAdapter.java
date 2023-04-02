package com.example.studentmanagement1.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentmanagement1.R;
import com.example.studentmanagement1.model.StudentModel;
import com.example.studentmanagement1.ui.activity.StudentAddActivity;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    List<StudentModel> studentModelList;

    public StudentAdapter(Context context, List<StudentModel> studentModelList) {
        this.context = context;
        this.studentModelList = studentModelList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student, parent, false);
        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.tvName.setText(studentModelList.get(position).getStudentName());
        holder.tvGrade.setText(studentModelList.get(position).getGrade());

        if (studentModelList.get(position).getGender() == 0) {
            holder.ivGender.setBackgroundResource(R.drawable.baseline_male_24);
        } else {
            holder.ivGender.setBackgroundResource(R.drawable.baseline_female_24);
        }
        holder.ivDelete.setOnClickListener(view -> {
            studentModelList.remove(position);
            notifyDataSetChanged();
        });
        holder.ivEdit.setOnClickListener(view -> {
            Intent i = new Intent(context, StudentAddActivity.class);
            i.putExtra("studentModel", studentModelList.get(position));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return studentModelList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvGrade;
        ImageView ivGender, ivEdit, ivDelete;

        public StudentViewHolder(@NonNull View v) {
            super(v);

            tvName = v.findViewById(R.id.tvName);
            tvGrade = v.findViewById(R.id.tvGrade);

            ivGender = v.findViewById(R.id.ivGender);
            ivEdit = v.findViewById(R.id.ivEdit);
            ivDelete = v.findViewById(R.id.ivDelete);
        }
    }
}
