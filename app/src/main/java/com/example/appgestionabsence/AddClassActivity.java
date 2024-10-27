package com.example.appgestionabsence;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddClassActivity extends AppCompatActivity {

    private EditText classNameEditText;
    private Button uploadFileButton;
    private Button generateListButton;

    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        classNameEditText = findViewById(R.id.editTextClassName);
        uploadFileButton = findViewById(R.id.btnUploadFile);
        generateListButton = findViewById(R.id.btnGenerateList);

        uploadFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilePicker();
            }
        });

        generateListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateStudentList();
                Intent intent = new Intent(AddClassActivity.this, StudentListActivity.class);
               //intent.putExtra("studentList", studentList);
                startActivity(intent);
            }
        });
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        pickFile.launch(intent);
    }

    private final ActivityResultLauncher<Intent> pickFile = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (result.getData() != null) {
                            Intent data = result.getData();
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                                String line;
                                studentList = new ArrayList<>();
                                while ((line = reader.readLine()) != null) {
                                    // Assumer que chaque ligne contient le nom et le CNE de l'étudiant séparés par une virgule
                                    String[] parts = line.split(",");
                                    if (parts.length == 2) {
                                        String cne = parts[0].trim();
                                        String name = parts[1].trim();
                                        studentList.add(new Student(cne, name));
                                    }
                                }
                                Toast.makeText(AddClassActivity.this, "Fichier téléchargé avec succès", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(AddClassActivity.this, "Erreur lors du téléchargement du fichier", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            });

    private void generateStudentList() {
            if (studentList != null && !studentList.isEmpty()) {
                // Parcourir la liste des étudiants et afficher leurs détails
                for (Student student : studentList) {
                    Log.d("Student", "CNE: " + student.getCNE() + ", Name: " + student.getName());
                    // Faites ici ce que vous voulez avec chaque étudiant
                    // Par exemple, vous pouvez l'ajouter à une base de données ou l'afficher dans une liste
                }
            } else {
                Toast.makeText(this, "Aucun étudiant trouvé", Toast.LENGTH_SHORT).show();
            }

    }
}
