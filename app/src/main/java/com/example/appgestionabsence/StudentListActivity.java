package com.example.appgestionabsence;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appgestionabsence.databinding.ActivityStudentListBinding;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityStudentListBinding binding;
    private DataManager databaseManager;

    // Déclaration de la liste des absences
    private List<Absence> absenceList;

    // Déclaration de l'adaptateur pour la liste des absences
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStudentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_student_list);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete_absence) {
            // Gérer la suppression d'une absence
            Toast.makeText(this, "Supprimer absence", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_generate_report) {
            // Gérer la génération du rapport
            Toast.makeText(this, "Générer rapport", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_student_list);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void deleteAbsence(MenuItem item) {
        // Obtenez l'ID de l'absence sélectionnée à partir de l'interface utilisateur ou d'une autre source
        int selectedAbsenceId = 0; // Par exemple, obtenir l'ID de l'absence à partir d'un champ de données ou d'un dialogue de sélection

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Voulez-vous vraiment supprimer cette absence ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 1. Supprimer l'absence de la base de données (exemple fictif)
                databaseManager.deleteAbsence(selectedAbsenceId);

                // 2. Mettre à jour la liste des absences en mémoire (exemple fictif)
                for (Absence absence : absenceList) {
                    if (absence.getId() == selectedAbsenceId) {
                        absenceList.remove(absence);
                        break;
                    }
                }

                // 3. Mettre à jour l'interface utilisateur (exemple fictif)
                adapter.notifyDataSetChanged();

                // Afficher un message de confirmation
                Toast.makeText(StudentListActivity.this, "Absence supprimée avec succès", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Non", null);
        builder.show();
    }

}
