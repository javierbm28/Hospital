package org.example;

public class Doctor extends Thread {
    private boolean isActive;

    public void toggleActivity() {
        isActive = !isActive;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Espera 8 segons (torn de 8 hores)
                Thread.sleep(8 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // En cas d'interrupció, sortim del bucle while
                break;
            }
            // Canvia l'estat del metge (actiu o inactiu) després de cada torn
            toggleActivity();
        }
    }
}

