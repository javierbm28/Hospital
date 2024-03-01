package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Hospital {
    private List<Doctor> availableDoctors;
    private List<Doctor> busyDoctors;
    private List<Nurse> nurses;
    private int numBoxes;
    private Box[] boxes;
    private Queue<Patient> waitingRoom;
    // Altres atributs i mètodes necessaris...

    public Hospital(int numBoxes) {
        this.availableDoctors = new ArrayList<>();
        this.busyDoctors = new ArrayList<>();
        this.nurses = new ArrayList<>();
        this.numBoxes = numBoxes;
        this.boxes = new Box[numBoxes];
        this.waitingRoom = new LinkedBlockingQueue<>();
        // Inicialització d'altres atributs i mètodes...
    }

    public synchronized Doctor getAvailableDoctor() {
        if (!availableDoctors.isEmpty()) {
            Doctor doctor = availableDoctors.remove(0);
            busyDoctors.add(doctor);
            return doctor;
        }
        return null; // Retorna null si no hi ha cap metge disponible
    }

    public synchronized Nurse getAvailableNurse() {
        for (Nurse nurse : nurses) {
            if (nurse.isActive()) {
                return nurse;
            }
        }
        return null; // Retorna null si no hi ha cap tècnic sanitari disponible
    }

    // Mètodes per gestionar els tècnics sanitaris
    public synchronized void addNurse(Nurse nurse) {
        nurses.add(nurse);
    }

    public synchronized void releaseDoctor(Doctor doctor) {
        busyDoctors.remove(doctor);
        availableDoctors.add(doctor);
    }

    public synchronized void addPatientToWaitingRoom(Patient patient) {
        waitingRoom.add(patient);
        System.out.println("Patient " + patient.getId() + " added to waiting room.");
    }

    public synchronized Patient getNextPatientFromWaitingRoom() {
        return waitingRoom.poll();
    }

}
