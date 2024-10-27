package com.example.appgestionabsence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> studentList;


    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewCNE;
        public CheckBox checkBoxAbsence;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCNE = itemView.findViewById(R.id.textViewCNE);
            checkBoxAbsence = itemView.findViewById(R.id.checkBoxAbsence);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_student_list, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.textViewName.setText(student.getName());
        holder.textViewCNE.setText(student.getCNE());

        // Gérer l'état de la case à cocher en fonction de l'absence de l'étudiant
        holder.checkBoxAbsence.setOnCheckedChangeListener(null); // Empêche le recyclage des vues de provoquer des problèmes de mise à jour incorrecte
        holder.checkBoxAbsence.setChecked(student.isAbsent());
        holder.checkBoxAbsence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                student.setAbsent(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }}