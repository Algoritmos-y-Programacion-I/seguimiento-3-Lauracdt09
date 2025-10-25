package model;

import java.util.ArrayList;
public class Computer {

    private String serialNumber;
    private boolean nextWindow;
    private ArrayList<Incident> incidents;

    public Computer(String serialNumber, boolean nextWindow) {
        this.serialNumber = serialNumber;
        this.nextWindow = nextWindow;
        this.incidents = new ArrayList<>();
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isNextWindow() {
        return nextWindow;
    }

    public void setNextWindow(boolean nextWindow) {
        this.nextWindow = nextWindow;
    }

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public void addIncident(Incident incident) {
        incidents.add(incident);
    }

    public int getIncidentSize() {
        return incidents.size();
    }
    
    @Override
    public String toString() {
        return "Computador con serial = " + serialNumber + "', cerca de la ventana= " + nextWindow +
               ", #incidentes= " + incidents.size() + "";
    }

}
