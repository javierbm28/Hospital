package org.example;

import java.util.Queue;

public class Nurse extends Thread {
    private int id;
    private boolean isActive;
    private Hospital hospital;

    public Nurse(int id, Hospital hospital) {
        this.id = id;
        this.hospital = hospital;
        this.isActive = true;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }


    public void treatPatient(Patient patient) {
        if (patient != null) {
            // Realitza el tractament al pacient específic
            System.out.println("Nurse " + id + " treating patient " + patient.getId());
            // Suposem que el temps de tractament del pacient és de 3 segons
            try {
                Thread.sleep(3000); // Simulem el temps de tractament en milisegons
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Nurse " + id + " treated patient " + patient.getId());
        } else {
            System.out.println("Nurse " + id + " has no patient to treat.");
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // Si el tècnic sanitari està actiu, atendrà pacients
            if (isActive) {
                // Obtenir el pacient següent de la sala d'espera
                Patient patient = hospital.getNextPatientFromWaitingRoom();
                // Tractar el pacient obtingut
                treatPatient(patient);
            }
            try {
                Thread.sleep(5000); // Espera 5 segons entre cada iteració
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


