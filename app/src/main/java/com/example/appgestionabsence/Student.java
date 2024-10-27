package com.example.appgestionabsence;

public class Student {
    private String cne;
    private String name;
    private boolean isAbsent;

    public Student(String cne, String name) {
        this.cne = cne;
        this.name = name;
    }

    public String getCNE() {
        return cne;
    }

    public String getName() {
        return name;
    }

    public boolean isAbsent() {
        boolean isAbsent = false;
        return isAbsent;
    }

    public void setAbsent(boolean isChecked) {
        // Mettez à jour le statut d'absence en fonction de la valeur isChecked
        this.isAbsent = isChecked;
    }
}
