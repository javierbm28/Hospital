package org.example;

public class Box extends Thread {
    private Patient patient;
    private int boxNumber;
    private Hospital hospital;
    private Nurse nurse;

    public Box(int boxNumber, Hospital hospital) {
        this.boxNumber = boxNumber;
        this.hospital = hospital;
    }

    public void assignPatient(Patient patient) {
        this.patient = patient;
        // Inicia l'atenció al pacient segons la seva gravetat...
        treatPatient();
    }

    public void assignNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    private void treatPatient() {
        if (patient != null) {
            int severity = patient.getSeverity();
            if (nurse != null && nurse.isActive()) {
                // Si hi ha un tècnic sanitari actiu assignat al box, el deleguem a ell
                nurse.treatPatient(patient);
                patient = null; // Allibera el box després de l'atenció al pacient
            } else {
                Doctor doctor = hospital.getAvailableDoctor();
                if (doctor != null) {
                    System.out.println("Box " + boxNumber + ": Treating patient " + patient.getId() + " with severity " + severity + " with Doctor " + doctor.getId());
                    try {
                        // Simula el temps d'atenció basant-se en la gravetat del pacient
                        Thread.sleep(severity * 1000); // Convertim els segons a milisegons
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Box " + boxNumber + ": Patient " + patient.getId() + " treated by Doctor " + doctor.getId());
                    hospital.releaseDoctor(doctor);
                    patient = null; // Allibera el box després de l'atenció al pacient
                } else {
                    System.out.println("Box " + boxNumber + ": No doctor available, patient " + patient.getId() + " waiting.");
                }
            }
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (patient != null) {
                treatPatient();
            } else {
                System.out.println("Box " + boxNumber + ": No patient");
                // Lògica quan no hi ha pacient...
                try {
                    Thread.sleep(1000); // Espera un segon quan no hi ha pacient
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


