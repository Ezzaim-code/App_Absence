package com.example.appgestionabsence;

public class Absence {
    private int id;
    private String studentName;
    private String date;

    public Absence(int id, String studentName, String date) {
        this.id = id;
        this.studentName = studentName;
        this.date = date;
    }

    public int getId() {
        return id;
    }
}

