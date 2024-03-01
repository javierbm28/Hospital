package org.example;

public class EmergencyService extends Thread {
    private static final int SIMULATION_DURATION_SECONDS = 48 * 3600; // 48 hores en segons
    private Hospital hospital;

    public EmergencyService(Hospital hospital) {
        this.hospital = hospital;
        // Inicialització d'altres atributs i mètodes...
    }

    @Override
    public void run() {
        long simulationStartTime = System.currentTimeMillis();
        long elapsedTime;
        while ((elapsedTime = System.currentTimeMillis() - simulationStartTime) <= SIMULATION_DURATION_SECONDS * 1000) {
            // Lògica per a l'arribada de pacients i assignació a boxes...
            try {
                Thread.sleep(1000); // Espera 1 segon entre iteracions
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

