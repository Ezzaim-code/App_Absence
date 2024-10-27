package com.example.appgestionabsence;

import java.util.Iterator;
import java.util.List;

public class DataManager {
    private List<Absence> absenceList;

    public DataManager(List<Absence> absenceList) {
        this.absenceList = absenceList;
    }

    // Méthode fictive pour supprimer une absence de la liste
    public void deleteAbsence(int absenceId) {
        // Parcourir la liste d'absences
        for (Iterator<Absence> iterator = absenceList.iterator(); iterator.hasNext(); ) {
            Absence absence = iterator.next();
            // Vérifier si l'ID de l'absence correspond à celui passé en paramètre
            if (absence.getId() == absenceId) {
                // Supprimer l'absence de la liste
                iterator.remove();
                break; // Sortir de la boucle une fois que l'absence est supprimée
            }
        }
    }
}
