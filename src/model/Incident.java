package model;

import java.time.LocalDate;

public class Incident {
    private LocalDate dateReport;
    private String description;
    private boolean solution;
    private int solutionHours;

    public Incident (LocalDate dateReport, String description){
        this.dateReport = dateReport;
        this.description = description;
        this.solution = false;
        this.solutionHours = 0;
    }
    public LocalDate getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    public int getSolutionHours() {
        return solutionHours;
    }

    public void setSolutionHours(int solutionHours) {
        this.solutionHours = solutionHours;
    }

    @Override
    public String toString() {
        return "[" + dateReport + "] " + description +
               " | solucionado= " + solution +
               (solution ? (" (" + solutionHours + "h)") : "");
    }
        
}
