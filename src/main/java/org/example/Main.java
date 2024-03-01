package org.example;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Inicialitza l'hospital amb el nombre de boxes adequat
        Hospital hospital = new Hospital(5); // Exemple amb un hospital petit
        EmergencyService emergencyService = new EmergencyService(hospital);

        // Inicia els threads
        emergencyService.start();
        // Inicia altres threads necessaris...
        Random random = new Random();
        for (int i = 0; i < 48 * 60 * 60; i++) { // 48 hores en segons
            // Simula l'arribada aleatòria de pacients
            int patientsThisSecond = random.nextInt(6); // Nombre aleatori de pacients entre 0 i 5
            for (int j = 0; j < patientsThisSecond; j++) {
                int severity = random.nextInt(6); // Gravetat aleatòria entre 0 i 5
                Patient patient = new Patient(j, severity);
                hospital.addPatientToWaitingRoom(patient);
                System.out.println("New patient arrived: " + patient.getId() + ", Severity: " + severity);
            }

            // Aquí pots actualitzar altres aspectes de la simulació del temps
            // Per exemple, avançar l'estat dels pacients, el torns dels metges, etc.
            // També pots registrar el progrés de la simulació o mostrar-lo per pantalla.
            try {
                TimeUnit.SECONDS.sleep(1); // Espera 1 segon per cada iteració del bucle de simulació
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


