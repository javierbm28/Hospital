package org.example;

public class Patient {
    private int id;
    private int severity;

    public Patient(int id, int severity) {
        this.id = id;
        this.severity = severity;
    }

    public int getId() {
        return id;
    }

    public int getSeverity() {
        return severity;
    }
}

